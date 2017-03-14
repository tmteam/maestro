package com.tmteam.jamaestro;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.tmteam.jamaestro.api.*;
import com.tmteam.jamaestro.binding.DriverBinding;
import com.tmteam.jamaestro.settings.ChannelSettings;
import com.tmteam.jamaestro.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.List;

// Clear copy of origin C# driver: https://github.com/pololu/pololu-usb-sdk/blob/master/Maestro/Usc/Usc.cs

public class MaestroServoController implements Closeable {

    private static final Logger LOG = LoggerFactory.getLogger(MaestroServoController.class);

    private static final double INSTRUCTION_FREQUENCY = 12000000;
    private static final int REQUEST_TIMEOUT = 5000;

    private static int convertBpsToSpbrg(double bps) {
        if (bps == 0)
            return 0;

        return (int) ((INSTRUCTION_FREQUENCY - bps / 2D) / bps);
    }

    private static int channelToPort(int channel) {
        if (channel <= 3)
            return channel;

        if (channel < 6)
            return channel + 2;

        throw new IllegalArgumentException("Invalid channel number " + channel);
    }

    protected final Product product;
    protected final MaestroUsbConnection conn;
    protected final String serialNumber;
    protected final String firmwareVersion;

    protected Settings settings;

    public MaestroServoController(DriverBinding driver) {
        this (driver, Settings.DEFAULT_SETTINGS);
    }

    public MaestroServoController(DriverBinding driver, Settings settings) {
        final Optional<Product> product = Product.fromId(driver.getVendorId(), driver.getProductId());
        if (!product.isPresent())
            throw new RuntimeException("Attempting to connect to unrecognised device");

        this.product = product.get();

        conn = new MaestroUsbConnection(driver, REQUEST_TIMEOUT);
        serialNumber = conn.getSerialNumber();
        firmwareVersion = conn.getFirmwareVersion();

        // Set the initial settings
        this.updateSettings(settings);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public Product getProduct() {
        return product;
    }

    public Settings getSettings() {
        return settings;
    }

    private void setParameter(Parameter parameter, int value) {
        final byte code = parameter.getCode();
        final Parameter.Range range = parameter.getRange();

        this.setParameter(code, range, value);
    }

    private void setParameter(Parameter parameter, int port, int value) {
        final byte code = (byte) (parameter.getCode()+ (port * 9));
        final Parameter.Range range = parameter.getRange();

        this.setParameter(code, range, value);
    }

    private void setParameter(byte code, Parameter.Range range, int value) {
        final int index = (range.getBytes() << 8) + code;

        // Ensure we are within the correct byte range
//        final int mask = (int) Math.pow(2, range.getBytes()) - 1;
//        value &= mask;

        // TODO: Ensure we are within the correct numeric range?

        LOG.debug("Sending parameter: " + index + " = " + value);

        conn.send(Request.SET_PARAMETER, value, index);
    }

    public void updateSettings(Settings settings) {
        this.settings = settings;

        LOG.info("Applying settings: " + settings);

        setParameter(Parameter.SERIAL_MODE, settings.getSerialMode().getCode());
        setParameter(Parameter.SERIAL_FIXED_BAUD_RATE, MaestroServoController.convertBpsToSpbrg(settings.getBaudRate()));
        setParameter(Parameter.SERIAL_ENABLE_CRC, settings.isEnableCrc() ? 1 : 0);
        setParameter(Parameter.SERIAL_NEVER_SUSPEND, settings.isNeverSuspend() ? 1 : 0);
        setParameter(Parameter.SERIAL_DEVICE_NUMBER, settings.getDeviceNumber());

        setParameter(Parameter.SERIAL_MINI_SSC_OFFSET, settings.getMiniSscOffset());
        setParameter(Parameter.SERIAL_TIMEOUT, settings.getTimeout());
        setParameter(Parameter.SCRIPT_DONE, settings.isScriptDone() ? 1 : 0);

        // Maestro Micro 6
        if (product == Product.MICRO6) {
            setParameter(Parameter.SERVOS_AVAILABLE, settings.getServosAvailable());
            setParameter(Parameter.SERVO_PERIOD, settings.getServoPeriod());
        }
        // Maestro Mini 12, 18, 24
        else {
            this.setParameter(Parameter.MINI_MAESTRO_SERVO_PERIOD_L, settings.getMiniMaestroServoPeriod() & 0xFF);
            this.setParameter(Parameter.MINI_MAESTRO_SERVO_PERIOD_HU, settings.getServoPeriod() >> 8);


                byte multiplier;
                if (settings.getServoMultiplier()  <  1)
                    multiplier = 0;
                else if (settings.getServoMultiplier() > 256)
                    multiplier = (byte)(255);
                else
                    multiplier = (byte)(settings.getServoMultiplier() - 1);

                setParameter(Parameter.SERVO_MULTIPLIER, multiplier);
        }

        // Maestro Mini 18, 24
        if (product == Product.MINI18 || product == Product.MINI24)
            this.setParameter(Parameter.ENABLE_PULLUPS, settings.isEnablePullups() ? 1 : 0);

        byte ioMask = 0;
        byte outputMask = 0;
        byte[] channelModeBytes = {0, 0, 0, 0, 0, 0};

        for (int port = 0; port < product.getPorts(); port++) {
            final ChannelSettings channel = settings.getChannel(port);

            if (product == Product.MICRO6) {
                // Set the io/output masks
                switch (channel.getChannelMode()) {
                    case OUTPUT:
                        outputMask |= (byte)(1 << MaestroServoController.channelToPort(port));
                    case INPUT:
                        ioMask |= (byte)(1 << MaestroServoController.channelToPort(port));
                }
            }
            else
                channelModeBytes[port >> 2] |= (byte)((byte) channel.getChannelMode().getCode() << ((port & 3) << 1));

            // Make sure that HomeMode is "Ignore" for inputs.  This is also done in
            // fixUscSettings.
            HomeMode correctedHomeMode = channel.getHomeMode();
            if (channel.getChannelMode() == ChannelMode.INPUT)
                correctedHomeMode = HomeMode.IGNORE;

            // Compute the raw value of the "home" parameter.
            int home;
            if (correctedHomeMode == HomeMode.OFF) home = 0;
            else if (correctedHomeMode == HomeMode.IGNORE) home = 1;
            else home = channel.getHome();

            setParameter(Parameter.SERVO_HOME, port, home);
            setParameter(Parameter.SERVO_MIN, port, channel.getMinimum() / 64);
            setParameter(Parameter.SERVO_MAX, port, channel.getMaximum() / 64);
            setParameter(Parameter.SERVO_NEUTRAL, port, channel.getNeutral());
            setParameter(Parameter.SERVO_RANGE, port, channel.getRange() / 127);
            setParameter(Parameter.SERVO_SPEED, port, channel.getExponentialSpeed());
            setParameter(Parameter.SERVO_ACCELERATION, port, channel.getAcceleration());
        }

        if (product == Product.MICRO6) {
            setParameter(Parameter.IO_MASK_C, ioMask);
            setParameter(Parameter.OUTPUT_MASK_C, outputMask);
        }
        else {
            for (byte port = 0; port < channelModeBytes.length; port++)
                setParameter((byte)(Parameter.CHANNEL_MODES_0_3.getCode() + port), Parameter.CHANNEL_MODES_0_3.getRange(), channelModeBytes[port]);
        }
    }

    // TODO: different for minis
    public List<Status> getStatus() {
        final int skip = 1 + 1 + 2 + 2 + (2 * 3) + (2 * 32) + (2 * 10) + 1 + 1;

        final int length = skip + (product.getPorts() * Status.BYTE_LENGTH); // TODO
        final ByteBuffer payload = conn.request(Request.GET_VARIABLES, length);

        // TODO: Read the first ? bytes as micro variables
        payload.position(payload.position() + skip);

        final ImmutableList.Builder<Status> channels = ImmutableList.builder();

        for (int servo = 0; servo < product.getPorts(); servo++) {
            final Status status = Status.decode(payload);
            channels.add(status);
        }

        return channels.build();
    }

    public void resetTarget(int servo) {
        final ChannelSettings channel = settings.getChannel(servo);
        this.setTarget(servo, channel.getHome());
    }

    public void setTarget(int servo, int value) {
        final ChannelSettings channel = settings.getChannel(servo);
        if (value < channel.getMinimum())
            value = channel.getMinimum();

        if (value > channel.getMaximum())
            value = channel.getMaximum();

        // value is the pulse width in units of quarter-microseconds
        conn.send(Request.SET_TARGET, value, servo);
    }

    public void setSpeed(int servo, int value) {
        conn.send(Request.SET_VARIABLE, value, servo);
    }

    public void setAcceleration(int servo, int value) {
        // set the high bit of servo to specify acceleration
        conn.send(Request.SET_VARIABLE, value, (servo | 0x80));
    }

    public void clearErrors() {
        conn.send(Request.CLEAR_ERRORS);
    }

    public void close() {
        conn.close();
    }

    @Override
    public String toString() {
        return "MaestroServoController{" +
                "product=" + product +
                ", serialNumber='" + serialNumber + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                '}';
    }
}
