package com.tmteam.jamaestro.settings;

import com.tmteam.jamaestro.api.ChannelMode;
import com.tmteam.jamaestro.api.HomeMode;

/**
 * Created by Su on 14/03/17.
 */
public class ChannelSettings {

    public static ChannelSettings CreateDefault(){return  new ChannelSettings();}
    public static ChannelSettings CreateForTest(int min, int max){
        return new ChannelSettings(min, max);
    }


    private static byte normalSpeedToExponentialSpeed(int mantissa) {
        byte exponent = 0;

        while (true) {
            if (mantissa < 32) {
                // We have reached the correct representation.
                return (byte) (exponent + (mantissa << 3));
            }

            if (exponent == 7) {
                // The number is too big to express in this format.
                return (byte) 0xFF;
            }

            // Try representing the number with a bigger exponent.
            exponent += 1;
            mantissa >>= 1;
        }
    }
    private ChannelMode channelMode = ChannelMode.OFF;
    private HomeMode homeMode = HomeMode.OFF;
    private int home = 6000;        // 1500us
    private int minimum = 3968;     // 4000;     // 1000us
    private int maximum = 8000;     // 2000us
    private int neutral = 6000;     // 1500us
    private int range = 1905;       // 2000;       // 500us
    private int speed = 0;          // unlimited
    private int acceleration = 0;   // unlimited
    private String name = "unnamed";

    public ChannelSettings(){}
    private ChannelSettings(int min, int max){
        minimum = min;
        maximum = max;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setChannelMode(ChannelMode channelMode) {
        this.channelMode = channelMode;
    }

    public ChannelMode getChannelMode() {
        return channelMode;
    }

    public void setHomeMode(HomeMode homeMode) {
        this.homeMode = homeMode;
    }

    public HomeMode getHomeMode() {
        return homeMode;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getHome() {
        return home;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setNeutral(int neutral) {
        this.neutral = neutral;
    }

    public int getNeutral() {
        return neutral;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public byte getExponentialSpeed() {
        return ChannelSettings.normalSpeedToExponentialSpeed(speed);
    }


    public boolean isSimilarTo(ChannelSettings channelSettings){
            if(this==channelSettings)
                return true;

            if(channelSettings==null)
                return  false;

            if(this.getHomeMode()!= channelSettings.getHomeMode())
                return false;
            if(this.getHome()!= channelSettings.getHome())
                return false;
            if(this.getChannelMode()!= channelSettings.getChannelMode())
                return false;
            if(this.getAcceleration()!= channelSettings.getAcceleration())
                return false;
            if(this.getMaximum()!= channelSettings.getMaximum())
                return false;
            if(this.getMinimum()!= channelSettings.getMinimum())
                return false;
            if(this.getNeutral()!= channelSettings.getNeutral())
                return false;
            if(this.getRange()!= channelSettings.getRange())
                return false;
            if(this.getSpeed()!= channelSettings.getSpeed())
                return false;
            return true;


    }
}
