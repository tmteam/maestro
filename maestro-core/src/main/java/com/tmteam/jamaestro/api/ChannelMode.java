package com.tmteam.jamaestro.api;

public enum ChannelMode {
    OFF(-1),
    SERVO(0),
    SERVO_MUTLIPLIED(1),
    OUTPUT(2),
    INPUT(3);

    private final byte code;

    private ChannelMode(int code) {
        this.code = (byte) code;
    }

    public byte getCode() {
        return code;
    }
}
