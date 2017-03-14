package com.tmteam.jamaestro.api;

public enum Parameter {
    INITIALIZED(0, Range.u8),                               // 1 byte - 0 or 0xFF

    SERVOS_AVAILABLE(1, Range.u8),                          // 1 byte - 0-5
    SERVO_PERIOD(2, Range.u8),                              // 1 byte - ticks allocated to each servo/256

    SERIAL_MODE(3, new Range(1, 0, 3)),                     // 1 byte unsigned value.  Valid values are SERIAL_MODE_*.  Init variable.
    SERIAL_FIXED_BAUD_RATE(4, Range.u16),                   // 2-byte unsigned value; 0 means autodetect.  Init parameter.
    SERIAL_TIMEOUT(6, Range.u16),                           // 2-byte unsigned value
    SERIAL_ENABLE_CRC(8, Range.b),                          // 1 byte boolean value
    SERIAL_NEVER_SUSPEND(9, Range.b),                       // 1 byte boolean value
    SERIAL_DEVICE_NUMBER(10, new Range(1, 0, 127)),         // 1 byte unsigned value, 0-127
    SERIAL_BAUD_DETECT_TYPE(11, new Range(1, 0, 1)),        // 1 byte value

    CHANNEL_MODES_0_3   (12, Range.u8),   // 1 byte - channel modes 0-3
    CHANNEL_MODES_4_7   (13, Range.u8),   // 1 byte - channel modes 4-7
    CHANNEL_MODES_8_11  (14, Range.u8),   // 1 byte - channel modes 8-11
    CHANNEL_MODES_12_15 (15, Range.u8),   // 1 byte - channel modes 12-15
    CHANNEL_MODES_16_19 (16, Range.u8),   // 1 byte - channel modes 16-19
    CHANNEL_MODES_20_23 (17, Range.u8),   // 1 byte - channel modes 20-23

    IO_MASK_C(16, Range.u8),                                // 1 byte - pins used for I/O instead of servo
    OUTPUT_MASK_C(17, Range.u8),                            // 1 byte - outputs that are enabled


    MINI_MAESTRO_SERVO_PERIOD_L(18, Range.u24),             // servo period: 3-byte unsigned values, units of quarter microseconds
    MINI_MAESTRO_SERVO_PERIOD_HU(19, Range.u24),


    ENABLE_PULLUPS(21, Range.b),                            // 1 byte: 0 or 1
    SCRIPT_CRC(22, Range.u16), // 2 bytes - stores a checksum of the bytecode program, for comparison

    SCRIPT_DONE(24, Range.b),                               // 1 byte - copied to scriptDone on startup
    SERIAL_MINI_SSC_OFFSET(25, new Range(1, 0, 254)),       // 1 byte (0-254)
    SERVO_MULTIPLIER(26, Range.u8), // 1 byte (0-255)

    SERVO_HOME(30, new Range(2, 0, 32440)),                 // 2 byte home position (0=off; 1=ignore)
    SERVO_MIN(32, Range.u8),                                // 1 byte min allowed value (x2^6)
    SERVO_MAX(33, Range.u8),                                // 1 byte max allowed value (x2^6)
    SERVO_NEUTRAL(34, new Range(2, 0, 32440)),              // 2 byte neutral position
    SERVO_RANGE(36, new Range(1, 1, 50)),                   // 1 byte range
    SERVO_SPEED(37, Range.u8),                              // 1 byte (5 mantissa,3 exponent) us per 10ms
    SERVO_ACCELERATION(38, Range.u8),                       // 1 byte (speed changes that much every 10ms)


    SERVO1_HOME(39, new Range(2, 0, 32440)), // 2 byte home position (0=off; 1=ignore)
    SERVO1_MIN(41, Range.u8),                                          // 1 byte min allowed value (x2^6)
    SERVO1_MAX(42, Range.u8), // 1 byte max allowed value (x2^6)
    SERVO1_NEUTRAL(43, Range.u16), // 2 byte neutral position
    SERVO1_RANGE(45, Range.u8), // 1 byte range
    SERVO1_SPEED(46, Range.u8), // 1 byte (5 mantissa,3 exponent) us per 10ms
    SERVO1_ACCELERATION(47, Range.u8), // 1 byte (speed changes that much every 10ms)
    SERVO2_HOME(48, new Range(2, 0, 32440)),// 2 byte home position (0=off; 1=ignore)
    SERVO2_MIN(50, Range.u8), // 1 byte min allowed value (x2^6)
    SERVO2_MAX(51, Range.u8), // 1 byte max allowed value (x2^6)
    SERVO2_NEUTRAL(52, new Range(2, 0, 32440)), // 2 byte neutral position
    SERVO2_RANGE(54, Range.u8), // 1 byte range
    SERVO2_SPEED(55, Range.u8), // 1 byte (5 mantissa,3 exponent) us per 10ms
    SERVO2_ACCELERATION(56, Range.u8), // 1 byte (speed changes that much every 10ms)
    SERVO3_HOME(57, new Range(2, 0, 32440)), // 2 byte home position (0=off; 1=ignore)
    SERVO3_MIN(59, Range.u8), // 1 byte min allowed value (x2^6)
    SERVO3_MAX(60, Range.u8), // 1 byte max allowed value (x2^6)
    SERVO3_NEUTRAL(61, new Range(2, 0, 32440)), // 2 byte neutral position
    SERVO3_RANGE(63, Range.u8), // 1 byte range
    SERVO3_SPEED(64, Range.u8), // 1 byte (5 mantissa,3 exponent) us per 10ms
    SERVO3_ACCELERATION (65, Range.u8), // 1 byte (speed changes that much every 10ms)
    SERVO4_HOME(66, new Range(2, 0, 32440)), // 2 byte home position (0=off; 1=ignore)
    SERVO4_MIN (68, Range.u8), // 1 byte min allowed value (x2^6)
    SERVO4_MAX (69,  Range.u8), // 1 byte max allowed value (x2^6)
    SERVO4_NEUTRAL (70, new Range(2, 0, 32440)), // 2 byte neutral position
    SERVO4_RANGE (72, Range.u8), // 1 byte range
    SERVO4_SPEED (73,  Range.u8), // 1 byte (5 mantissa,3 exponent) us per 10ms
    SERVO4_ACCELERATION (74, Range.u8), // 1 byte (speed changes that much every 10ms)
    SERVO5_HOME (75, new Range(2, 0, 32440)), // 2 byte home position (0=off; 1=ignore)
    SERVO5_MIN (77, Range.u8), // 1 byte min allowed value (x2^6)
    SERVO5_MAX (78, Range.u8), // 1 byte max allowed value (x2^6)
    SERVO5_NEUTRAL (79, new Range(2, 0, 32440)), // 2 byte neutral position
    SERVO5_RANGE (81, Range.u8), // 1 byte range
    SERVO5_SPEED (82,  Range.u8), // 1 byte (5 mantissa,3 exponent) us per 10ms
    SERVO5_ACCELERATION (83, Range.u8); // 1 byte (speed changes that much every 10ms)


    private final byte code;
    private final Range range;

    private Parameter(int code, Range range) {
        this.code = (byte) code;
        this.range = range;
    }

    public byte getCode() {
        return code;
    }

    public Range getRange() {
        return range;
    }


    public static class Range {

        public static Range u32 = new Range(4, 0, 0x7FFFFFFF);
        public static Range u24 = new Range(3, 0, 0xFFFFFF);
        public static Range u16 = new Range(2, 0, 0xFFFF);
        public static Range u8 = new Range(1, 0, 0xFF);
        public static Range b = new Range(1, 0, 1);

        private final byte bytes;
        private final int minimum;
        private final int maximum;

        public Range(int bytes, int minimum, int maximum) {
            this.bytes = (byte) bytes;
            this.minimum = minimum;
            this.maximum = maximum;
        }

        public byte getBytes() {
            return bytes;
        }

        public int getMinimum() {
            return minimum;
        }

        public int getMaximum() {
            return maximum;
        }
    }

}
