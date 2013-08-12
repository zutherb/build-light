package com.cleware.driver;

import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDManager;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static java.lang.String.format;

/**
 * @author zutherb
 */
public class TrafficLightImpl implements TrafficLight {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficLightImpl.class);

    private static final String SWITCH_ON_ERROR = "Led {} could not be switched on";
    private static final String SWITCH_OFF_ERROR = "Led {} could not be switched off";

    private final HIDManager hidManager;
    private final HIDDevice hidDevice;

    TrafficLightImpl(HIDManager hidManager, HIDDevice hidDevice) {
        this.hidManager = hidManager;
        this.hidDevice = hidDevice;
    }

    @Override
    public void switchOn(Led led) {
        try {
            byte[] writeBuffer = createSwitchOnBuffer(led);
            int writtenBytes = hidDevice.sendFeatureReport(writeBuffer);
            Validate.isTrue(writtenBytes == writeBuffer.length, "Not all bytes from the was written to usb");
            LOGGER.info(format("Switch on '%s' Led", led.name()));
        } catch (IOException e) {
            LOGGER.debug(SWITCH_ON_ERROR, led.name());
            throw new TrafficLightException(e);
        }
    }

    @Override
    public void switchOff(Led led) {
        try {
            byte[] writeBuffer = createSwitchOffBuffer(led);
            int writtenBytes = hidDevice.write(writeBuffer);
            Validate.isTrue(writtenBytes == writeBuffer.length, "Not all bytes from the was written to usb");
            LOGGER.info(format("Switch off '%s' Led", led.name()));
        } catch (IOException e) {
            LOGGER.debug(SWITCH_OFF_ERROR, led.name());
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

    private byte[] createSwitchOnBuffer(Led led) {
        return new byte[]{(byte) 0x0, led.getAddress(), (byte) 0x1};
    }

    private byte[] createSwitchOffBuffer(Led led) {
        return new byte[]{(byte) 0x0, led.getAddress(), (byte) 0x0};
    }

    @Override
    public void close() {
        try {
            hidDevice.close();
            hidManager.release();
        } catch (IOException e) {
            LOGGER.error("USB Device could not be closed");
            throw new TrafficLightException(e);
        }
    }
}
