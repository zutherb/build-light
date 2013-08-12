package com.cleware.driver;

/**
 * @author zutherb
 */
public enum Led {
    RED((byte) 0x10),
    YELLOW((byte) 0x11),
    GREEN((byte) 0x12);

    private byte address;

    Led(byte address) {
        this.address = address;
    }

    byte getAddress() {
        return address;
    }

    public static Led valueOfIgnoreCase(String name) {
        for (Led led : Led.values()) {
            if (led.name().equalsIgnoreCase(name)) {
                return led;
            }
        }
        throw new IllegalArgumentException("Not led was found for arg: " + name);
    }
}
