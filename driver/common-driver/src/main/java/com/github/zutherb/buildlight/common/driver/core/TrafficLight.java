package com.github.zutherb.buildlight.common.driver.core;

/**
 * @author zutherb
 */
public interface TrafficLight {
    static final String MESSAGE_SWITCH_ON_ERROR = "Led {} could not be switched on";
    static final String MESSAGE_SWITCH_OFF_ERROR = "Led {} could not be switched off";
    static final String MESSAGE_NOT_ALL_BYTES_FROM_THE_WAS_WRITTEN_TO_USB = "Not all bytes from the was written to usb";
    static final String MESSAGE_SWITCH_ON_LED = "Switch on '%s' Led";
    static final String MESSAGE_SWITCH_OFF_LED = "Switch off '%s' Led";
    static final String MESSAGE_USB_DEVICE_COULD_NOT_BE_CLOSED = "USB Device could not be closed";

    void switchOn(Color color);

    void switchOff(Color color);

    void switchOnAllLeds();

    void switchOffAllLeds();

    void close();
}
