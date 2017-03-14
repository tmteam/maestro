package com.tmteam.jamaestro.settings;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.tmteam.jamaestro.api.SerialMode;

import java.util.Map;

public class Settings {

    public static final Settings DEFAULT_SETTINGS = Settings.builder().build();

    public static int frequencyToPeriod(double hertz, int servos) {
        return (int) (hertz / (servos * (256D / 12D)));
    }

    public static double periodToFrequency(int period, int servos) {
        return (256D / 12D) * period * servos;
    }

    public byte getServoMultiplier() {
        return servoMultiplier;
    }

    public static class Builder {
        private byte servoMultiplier = 1;
        private int miniMaestroServoPeriod = 80000;

        private int servosAvailable = 6;
        private int servoPeriod = 156;
        private SerialMode serialMode = SerialMode.UART_DETECT_BAUD_RATE;
        private int baudRate = 9600;
        private boolean enableCrc = false;
        private boolean neverSuspend = false;
        private int deviceNumber = 12;
        private int miniSccOffset = 0;
        private int timeout = 0;
        private boolean scriptDone = true;
        private boolean enablePullups = false;
        private final Map<Integer, ChannelSettings> channels = Maps.newTreeMap();

        public Builder setServoMultiplier(byte servoMultiplier){
            this.servoMultiplier = servoMultiplier;
            return  this;
        }

        public Builder setMiniMaestroServoPeriod(byte miniMaestroServoPeriod){
            this.miniMaestroServoPeriod = miniMaestroServoPeriod;
            return  this;
        }

        public Builder setServosAvailable(int servosAvailable) {
            this.servosAvailable = servosAvailable;
            return this;
        }

        public Builder setServoPeriod(int servoPeriod) {
            this.servoPeriod = servoPeriod;
            return this;
        }

        public Builder setSerialMode(SerialMode serialMode) {
            this.serialMode = serialMode;
            return this;
        }

        public Builder setBaudRate(int baudRate) {
            this.baudRate = baudRate;
            return this;
        }

        public Builder setEnableCrc(boolean enableCrc) {
            this.enableCrc = enableCrc;
            return this;
        }

        public Builder setNeverSuspend(boolean neverSuspend) {
            this.neverSuspend = neverSuspend;
            return this;
        }

        public Builder setDeviceNumber(int deviceNumber) {
            this.deviceNumber = deviceNumber;
            return this;
        }

        public Builder setMiniSccOffset(int miniSccOffset) {
            this.miniSccOffset = miniSccOffset;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder setScriptDone(boolean scriptDone) {
            this.scriptDone = scriptDone;
            return this;
        }

        public Builder setEnablePullups(boolean enablePullups) {
            this.enablePullups = enablePullups;
            return this;
        }

        public Builder addChannel(int port, ChannelSettings channel) {
            channels.put(port, channel);
            return this;
        }

        public Settings build() {
            return new Settings(
                    servosAvailable,
                    servoPeriod,
                    serialMode,
                    baudRate,
                    servoMultiplier,
                    miniMaestroServoPeriod,
                    enableCrc,
                    neverSuspend,
                    deviceNumber,
                    miniSccOffset,
                    timeout,
                    scriptDone,
                    enablePullups,
                    ImmutableMap.copyOf(channels));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // The number of servo ports available. This, along with the servoPeriod, determine the "maximum pulse width".
    private final int servosAvailable;

    // The total time allotted to each servo channel, in units of 256/12 = 21.33333 us. The unit for
    // this one are unusual, because that is the way it is stored on the device and its unit is not a
    // multiple of 4, so we would have inevitable rounding errors if we tried to represent it in
    // quarter-microseconds. Default is 156, so with 6 servos available you get ~20ms between pulses
    // on a given channel (50Hz).
    private final int servoPeriod;

    // Determines how serial bytes flow between the two USB COM ports, the TTL port, and the Maestro's
    // serial command processor.
    private final SerialMode serialMode;

    // The fixed baud rate, in units of bits per second. This parameter only applies if serial mode is
    // USB UART Fixed Baud.
    private final int baudRate;


    // This setting only applied to the Mini Maestro.
    // The non-multiplied servos have a period specified by miniMaestroServoPeriod.
    // The multiplied servos have a period specified by miniMaestroServoPeriod*servoMultiplier.
    //
    // Valid values for this parameter are 1 to 256.
    // </summary>
    private final byte servoMultiplier;

    // This setting only applies to the Mini Maestro.
    // For the Micro Maestro, see microMaestroServoPeriod.
    //
    // The length of the time period in which the Mini Maestro sends pulses
    // to all the enabled servos, in units of quarter microseconds.
    //
    // Valid values for this parameter are 0 to 16,777,215.  But
    //
    // Default is 80000, so each servo receives a pulse every 20 ms (50 Hz).
    private final int miniMaestroServoPeriod;

    // If true, then you must send a 7-bit CRC byte at the end of every serial command (except the Mini
    // SSC II command).
    private final boolean enableCrc;

    // If true, then the Maestro will never go to sleep.  This lets you power the processer off of USB
    // even when the computer has gone to sleep and put all of its USB devices in the suspend state.
    private final boolean neverSuspend;

    // The serial device number used to identify this device in Pololu protocol commands.  Valid values
    // are 0-127, default is 12.
    private final int deviceNumber;

    // The offset used to determine which Mini SSC commands this device will respond to. The second byte
    // of the Mini SSC command contains the servo number; the correspondence between servo number and
    // maestro number (0-5) is servo# = miniSSCoffset + channel#.  Valid values are 0-254.
    private final int miniSscOffset;

    // The time it takes for a serial timeout error to occur, in units of 10 ms. A value of 0 means no
    // timeout error will occur.  All values 0-65535 are valid.
    private final int timeout;

    // True if the script should not be started when the device starts up. False if the script should be started.
    private final boolean scriptDone;

    // If true, this setting enables pullups for each channel 18-20 which is configured as an input. This makes
    // the input value be high by default, allowing the user to connect a button or switch without supplying
    // their own pull-up resistor. This setting only applies to the Mini Maestro 24-Channel Servo Controller.
    private final boolean enablePullups;

    // A list of the configurable parameters for each channel, including name, type, home type, home position,
    // range, neutral, min, max.
    private final Map<Integer, ChannelSettings> channels;

    public Settings(int servosAvailable,
                    int servoPeriod,
                    SerialMode serialMode,
                    int baudRate,
                    byte servoMultiplier,
                    int miniMaestroServoPeriod,
                    boolean enableCrc,
                    boolean neverSuspend,
                    int deviceNumber,
                    int miniSscOffset,
                    int timeout,
                    boolean scriptDone,
                    boolean enablePullups,
                    Map<Integer, ChannelSettings> channels)
    {
        this.servosAvailable = servosAvailable;
        this.servoPeriod = servoPeriod;
        this.serialMode = serialMode;
        this.baudRate = baudRate;
        this.servoMultiplier = servoMultiplier;
        this.miniMaestroServoPeriod = miniMaestroServoPeriod;
        this.enableCrc = enableCrc;
        this.neverSuspend = neverSuspend;
        this.deviceNumber = deviceNumber;
        this.miniSscOffset = miniSscOffset;
        this.timeout = timeout;
        this.scriptDone = scriptDone;
        this.enablePullups = enablePullups;
        this.channels = channels;
    }

    public int getServosAvailable() {
        return servosAvailable;
    }

    public int getServoPeriod() {
        return servoPeriod;
    }

    public SerialMode getSerialMode() {
        return serialMode;
    }

    public  int getMiniMaestroServoPeriod(){return  miniMaestroServoPeriod;}

    public int getBaudRate() {
        return baudRate;
    }

    public boolean isEnableCrc() {
        return enableCrc;
    }

    public boolean isNeverSuspend() {
        return neverSuspend;
    }

    public int getDeviceNumber() {
        return deviceNumber;
    }

    public int getMiniSscOffset() {
        return miniSscOffset;
    }

    public int getTimeout() {
        return timeout;
    }

    public boolean isScriptDone() {
        return scriptDone;
    }

    public boolean isEnablePullups() {
        return enablePullups;
    }

    public ChannelSettings getChannel(int port) {
        if (!channels.containsKey(port))
            return ChannelSettings.DEFAULT_SETTINGS;

        return channels.get(port);
    }

    @Override
    public String toString() {
        return "Settings{" +
                "servosAvailable=" + servosAvailable +
                ", servoPeriod=" + servoPeriod +
                ", serialMode=" + serialMode +
                ", baudRate=" + baudRate +
                ", enableCrc=" + enableCrc +
                ", neverSuspend=" + neverSuspend +
                ", deviceNumber=" + deviceNumber +
                ", miniSscOffset=" + miniSscOffset +
                ", timeout=" + timeout +
                ", scriptDone=" + scriptDone +
                ", enablePullups=" + enablePullups +
                ", channels=" + channels +
                '}';
    }
}
