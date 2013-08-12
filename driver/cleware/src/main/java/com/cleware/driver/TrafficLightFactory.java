package com.cleware.driver;

import com.codeminders.hidapi.ClassPathLibraryLoader;
import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDManager;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zutherb
 */
public final class TrafficLightFactory {

    private TrafficLightFactory() { /*NOOP*/ }

    private static TrafficLight TRAFFIC_LIGHT;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficLightFactory.class);

    private static final int VENDOR_ID = 0xD50;     //Cleware Vendor Id
    private static final int PRODUCT_ID = 0x8;      //Traffic Light Product Id

    public synchronized static TrafficLight instance() {
        if (TRAFFIC_LIGHT == null) {
            TRAFFIC_LIGHT = createNewInstance();
        }
        return TRAFFIC_LIGHT;
    }

    private static TrafficLight createNewInstance() {
        try {
            ClassPathLibraryLoader.loadNativeHIDLibrary();
            HIDManager hidManager = HIDManager.getInstance();
            HIDDevice hidDevice = hidManager.openById(VENDOR_ID, PRODUCT_ID, null);
            dumpDebugInformation(hidDevice);
            return new TrafficLightImpl(hidManager, hidDevice);
        } catch (IOException e) {
            LOGGER.error("Traffic light instance could not be created", e);
            throw new TrafficLightException(e);
        }
    }

    private static void dumpDebugInformation(HIDDevice hidDevice) throws IOException {
        LOGGER.debug("Manufacturer:\t{}", hidDevice.getManufacturerString());
        LOGGER.debug("Product:\t\t{}", hidDevice.getProductString());
        LOGGER.debug("SerialNumber:\t{}", hidDevice.getSerialNumberString());
    }
}
