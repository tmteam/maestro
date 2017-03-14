package com.tmteam.jamaestro.settings;

import com.tmteam.jamaestro.api.ChannelMode;
import com.tmteam.jamaestro.api.HomeMode;

/**
 * Created by Su on 14/03/17.
 */
public class ChannelSettings2 {
    private ChannelMode channelMode = ChannelMode.SERVO;
    private HomeMode homeMode = HomeMode.OFF;
    private int home = 6000;        // 1500us
    private int minimum = 3968;     // 4000;     // 1000us
    private int maximum = 8000;     // 2000us
    private int neutral = 6000;     // 1500us
    private int range = 1905;       // 2000;       // 500us
    private int speed = 0;          // unlimited
    private int acceleration = 0;   // unlimited

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
}
