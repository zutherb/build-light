package com.comsysto.buildlight.cleware.driver;

import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;
import static org.apache.commons.lang.Validate.isTrue;

/**
 * @author zutherb
 */
public class TrafficLightImpl implements TrafficLight {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficLightImpl.class);

    private static final String MESSAGE_SWITCH_ON_ERROR = "Led {} could not be switched on";
    private static final String MESSAGE_SWITCH_OFF_ERROR = "Led {} could not be switched off";
    private static final String MESSAGE_NOT_ALL_BYTES_FROM_THE_WAS_WRITTEN_TO_USB = "Not all bytes from the was written to usb";
    private static final String MESSAGE_SWITCH_ON_LED = "Switch on '%s' Led";
    private static final String MESSAGE_SWITCH_OFF_LED = "Switch off '%s' Led";
    private static final String MESSAGE_USB_DEVICE_COULD_NOT_BE_CLOSED = "USB Device could not be closed";

    private static final byte ZERO = (byte) 0x0;
    private static final byte ONE = (byte) 0x1;

    private final HIDManager hidManager;
    private final HIDDevice hidDevice;

    TrafficLightImpl(HIDManager hidManager, HIDDevice hidDevice) {
        this.hidManager = hidManager;
        this.hidDevice = hidDevice;
    }

    @Override
    public void switchOn(Led led) {
        try {
            byte[] writeBuffer = createSwitchOnSequenceBuffer(led);
            int writtenBytes = hidDevice.write(writeBuffer);
            isTrue(writtenBytes == writeBuffer.length, MESSAGE_NOT_ALL_BYTES_FROM_THE_WAS_WRITTEN_TO_USB);
            LOGGER.debug(format(MESSAGE_SWITCH_ON_LED, led.name()));
        } catch (Exception e) {
            LOGGER.error(MESSAGE_SWITCH_ON_ERROR, led.name());
            throw new TrafficLightException(e);
        }
    }

    @Override
    public void switchOff(Led led) {
        try {
            byte[] writeBuffer = createSwitchOffSequenceBuffer(led);
            int writtenBytes = hidDevice.write(writeBuffer);
            isTrue(writtenBytes == writeBuffer.length, MESSAGE_NOT_ALL_BYTES_FROM_THE_WAS_WRITTEN_TO_USB);
            LOGGER.debug(format(MESSAGE_SWITCH_OFF_LED, led.name()));
        } catch (Exception e) {
            LOGGER.error(MESSAGE_SWITCH_OFF_ERROR, led.name());
            throw new TrafficLightException(e);
        }
    }

    @Override
    public void switchOnAllLeds() {
        for (Led led : Led.values()) {
            switchOn(led);
        }
    }

    @Override
    public void switchOffAllLeds() {
        for (Led led : Led.values()) {
            switchOff(led);
        }
    }

    private byte[] createSwitchOnSequenceBuffer(Led led) {
        return new byte[]{ZERO, ZERO, led.getAddress(), ONE};
    }

    private byte[] createSwitchOffSequenceBuffer(Led led) {
        return new byte[]{ZERO, ZERO, led.getAddress(), ZERO};
    }

    @Override
    public void close() {
        try {
            hidDevice.close();
            hidManager.release();
        } catch (Exception e) {
            LOGGER.error(MESSAGE_USB_DEVICE_COULD_NOT_BE_CLOSED);
            throw new TrafficLightException(e);
        }
    }
}
