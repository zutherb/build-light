package com.cleware.driver;

/**
 * @author zutherb
 */
public enum Led {
    RED((byte) 0x010),
    YELLOW((byte) 0x011),
    GREEN((byte) 0x012);

    private byte address;

    Led(byte address) {
        this.address = address;
    }

    byte getAddress() {
        return address;
    }
}
