package com.cleware.driver;

import com.codeminders.hidapi.ClassPathLibraryLoader;
import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zutherb
 */
public class TrafficLight {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficLight.class);

    private static final int VENDOR_ID = 0xD50;     //Cleware Vendor Id
    private static final int PRODUCT_ID = 0x8;      //Traffic Light Product Id

    private static final String SWITCH_ON_ERROR = "Led {} could not be switched on";
    private static final String SWITCH_OFF_ERROR = "Led {} could not be switched off";

    private final HIDManager hidManager;
    private final HIDDevice hidDevice;

    private TrafficLight(HIDManager hidManager, HIDDevice hidDevice) {
        this.hidManager = hidManager;
        this.hidDevice = hidDevice;
    }


    public void switchOn(Led led) {
        try {
            hidDevice.write(createSwitchOnBuffer(led));
        } catch (IOException e) {
            LOGGER.debug(SWITCH_ON_ERROR, led.name());
            throw new TrafficLightException(e);
        }
    }

    public void switchOff(Led led) {
        try {
            hidDevice.write(createSwitchOffBuffer(led));
            LOGGER.debug(SWITCH_OFF_ERROR, led.name());
        } catch (IOException e) {
            throw new TrafficLightException(e);
        }
    }

    public void switchOnAllLeds() {
        for (Led led : Led.values()) {
            switchOn(led);
        }
    }

    public void switchOffAllLeds() {
        for (Led led : Led.values()) {
            switchOff(led);
        }
    }

    private byte[] createSwitchOnBuffer(Led led) {
        return new byte[]{0, led.getAddress(), 15};
    }

    private byte[] createSwitchOffBuffer(Led led) {
        return new byte[]{0, led.getAddress(), 15};
    }

    public void close() {
        try {
            hidDevice.close();
            hidManager.release();
        } catch (IOException e) {
            LOGGER.error("USB Device could not be closed");
            throw new TrafficLightException(e);
        }
    }

    public static TrafficLight createInstance() {
        try {
            ClassPathLibraryLoader.loadNativeHIDLibrary();
            HIDManager hidManager = HIDManager.getInstance();
            HIDDevice hidDevice = hidManager.openById(VENDOR_ID, PRODUCT_ID, null);
            dumpDebugInformation(hidDevice);
            return new TrafficLight(hidManager, hidDevice);
        } catch (IOException e) {
            LOGGER.error("Traffic light instance could not be created");
            throw new TrafficLightException(e);
        }
    }

    private static void dumpDebugInformation(HIDDevice hidDevice) throws IOException {
        LOGGER.debug("Manufacturer:\t{}", hidDevice.getManufacturerString());
        LOGGER.debug("Product:\t\t{}", hidDevice.getProductString());
        LOGGER.debug("SerialNumber:\t{}", hidDevice.getSerialNumberString());
    }
}
