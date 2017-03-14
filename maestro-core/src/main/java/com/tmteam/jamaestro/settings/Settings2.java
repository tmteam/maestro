package com.tmteam.jamaestro.settings;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.tmteam.jamaestro.api.SerialMode;

import java.util.Map;

public class Settings2 {

    public static Settings2 CreateDefault(){return new Settings2();}

    public static int frequencyToPeriod(double hertz, int servos) {
        return (int) (hertz / (servos * (256D / 12D)));
    }

    public static double periodToFrequency(int period, int servos) {
        return (256D / 12D) * period * servos;
    }

    public byte getServoMultiplier() {
        return servoMultiplier;
    }

    // The number of servo ports available. This, along with the servoPeriod, determine the "maximum pulse width".
    private int servosAvailable;

    // The total time allotted to each servo channel, in units of 256/12 = 21.33333 us. The unit for
    // this one are unusual, because that is the way it is stored on the device and its unit is not a
    // multiple of 4, so we would have inevitable rounding errors if we tried to represent it in
    // quarter-microseconds. Default is 156, so with 6 servos available you get ~20ms between pulses
    // on a given channel (50Hz).
    private int servoPeriod;

    // Determines how serial bytes flow between the two USB COM ports, the TTL port, and the Maestro's
    // serial command processor.
    private SerialMode serialMode;

    // The fixed baud rate, in units of bits per second. This parameter only applies if serial mode is
    // USB UART Fixed Baud.
    private int baudRate;


    // This setting only applied to the Mini Maestro.
    // The non-multiplied servos have a period specified by miniMaestroServoPeriod.
    // The multiplied servos have a period specified by miniMaestroServoPeriod*servoMultiplier.
    //
    // Valid values for this parameter are 1 to 256.
    // </summary>
    private byte servoMultiplier;

    // This setting only applies to the Mini Maestro.
    // For the Micro Maestro, see microMaestroServoPeriod.
    //
    // The length of the time period in which the Mini Maestro sends pulses
    // to all the enabled servos, in units of quarter microseconds.
    //
    // Valid values for this parameter are 0 to 16,777,215.  But
    //
    // Default is 80000, so each servo receives a pulse every 20 ms (50 Hz).
    private int miniMaestroServoPeriod;

    // If true, then you must send a 7-bit CRC byte at the end of every serial command (except the Mini
    // SSC II command).
    private boolean enableCrc;

    // If true, then the Maestro will never go to sleep.  This lets you power the processer off of USB
    // even when the computer has gone to sleep and put all of its USB devices in the suspend state.
    private boolean neverSuspend;

    // The serial device number used to identify this device in Pololu protocol commands.  Valid values
    // are 0-127, default is 12.
    private int deviceNumber;

    // The offset used to determine which Mini SSC commands this device will respond to. The second byte
    // of the Mini SSC command contains the servo number; the correspondence between servo number and
    // maestro number (0-5) is servo# = miniSSCoffset + channel#.  Valid values are 0-254.
    private int miniSscOffset;

    // The time it takes for a serial timeout error to occur, in units of 10 ms. A value of 0 means no
    // timeout error will occur.  All values 0-65535 are valid.
    private int timeout;

    // True if the script should not be started when the device starts up. False if the script should be started.
    private boolean scriptDone;

    // If true, this setting enables pullups for each channel 18-20 which is configured as an input. This makes
    // the input value be high by default, allowing the user to connect a button or switch without supplying
    // their own pull-up resistor. This setting only applies to the Mini Maestro 24-Channel Servo Controller.
    private boolean enablePullups;

    // A list of the configurable parameters for each channel, including name, type, home type, home position,
    // range, neutral, min, max.
    private Map<Integer, ChannelSettings2> channels;

    public Settings2()
    {
        byte servoMultiplier = 1;
        int miniMaestroServoPeriod = 80000;
        int servosAvailable = 6;
        int servoPeriod = 156;
        SerialMode serialMode = SerialMode.UART_DETECT_BAUD_RATE;
        int baudRate = 9600;
        boolean enableCrc = false;
        boolean neverSuspend = false;
        int deviceNumber = 12;
        int miniSccOffset = 0;
        int timeout = 0;
        boolean scriptDone = true;
        boolean enablePullups = false;
        channels = Maps.newTreeMap();
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

    public ChannelSettings2 getChannel(int port) {
        if (!channels.containsKey(port))
            return ChannelSettings2.CreateDefault();

        return channels.get(port);
    }
    public void AddChannel(int port, ChannelSettings2 settings){
        if(channels.containsKey(port))
            channels.remove(port);

        channels.put(port, settings);
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

    public void setServosAvailable(int servosAvailable) {
        this.servosAvailable = servosAvailable;
    }

    public void setServoPeriod(int servoPeriod) {
        this.servoPeriod = servoPeriod;
    }

    public void setSerialMode(SerialMode serialMode) {
        this.serialMode = serialMode;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public void setServoMultiplier(byte servoMultiplier) {
        this.servoMultiplier = servoMultiplier;
    }

    public void setMiniMaestroServoPeriod(int miniMaestroServoPeriod) {
        this.miniMaestroServoPeriod = miniMaestroServoPeriod;
    }

    public void setEnableCrc(boolean enableCrc) {
        this.enableCrc = enableCrc;
    }

    public void setNeverSuspend(boolean neverSuspend) {
        this.neverSuspend = neverSuspend;
    }

    public void setDeviceNumber(int deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public void setMiniSscOffset(int miniSscOffset) {
        this.miniSscOffset = miniSscOffset;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setScriptDone(boolean scriptDone) {
        this.scriptDone = scriptDone;
    }

    public void setEnablePullups(boolean enablePullups) {
        this.enablePullups = enablePullups;
    }

    public boolean isSimilarTo(Settings2 settings){
        if(settings.getBaudRate()!= getBaudRate())
            return false;
        if(settings.getDeviceNumber()!= getDeviceNumber())
            return false;
        if(settings.getMiniMaestroServoPeriod()!= getMiniMaestroServoPeriod())
            return false;
        if(settings.getMiniSscOffset()!= getMiniSscOffset())
            return false;
        if(settings.getSerialMode()!= getSerialMode())
            return false;
        if(settings.getServoMultiplier()!= getServoMultiplier())
            return false;
        if(settings.getServoPeriod()!= getServoPeriod())
            return false;
        if(settings.getServosAvailable()!= getServosAvailable())
            return false;
        if(settings.getTimeout()!= getTimeout())
            return false;
        if(settings.isEnableCrc()!= isEnableCrc())
            return false;
        if(settings.isEnablePullups()!= isEnablePullups())
            return false;
        if(settings.isNeverSuspend()!= isNeverSuspend())
            return false;
        if(settings.isScriptDone()!= isScriptDone())
            return false;

        for (Map.Entry<Integer, ChannelSettings2> channel : channels.entrySet())
        {
            if(!settings.getChannel(channel.getKey()).isSimilarTo(channel.getValue()))
                return false;
        }
        return true;
    }

}
