package com.comsysto.buildlight.blinkstick.driver;

import com.codeminders.hidapi.ClassPathLibraryLoader;
import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDManager;
import com.comsysto.buildlight.common.driver.TrafficLight;
import com.comsysto.buildlight.common.driver.TrafficLightException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zutherb
 */
public final class ClewareLightFactory {

    private ClewareLightFactory() { /* NOOP */ }

    private static final Logger LOGGER = LoggerFactory.getLogger(ClewareLightFactory.class);

    private static final int VENDOR_ID = 0xD50;     //Cleware Vendor Id
    private static final int PRODUCT_ID = 0x8;      //Traffic Light Product Id

    static {
        ClassPathLibraryLoader.loadNativeHIDLibrary();
    }

    public static TrafficLight createNewInstance() {
        try {
            HIDManager hidManager = HIDManager.getInstance();
            HIDDevice hidDevice = hidManager.openById(VENDOR_ID, PRODUCT_ID, null);
            dumpDebugInformation(hidDevice);
            return new ClewareTrafficLightImpl(hidManager, hidDevice);
        } catch (IOException e) {
            throw new TrafficLightException("Traffic Light USB device could not be found.", e);
        }
    }

    private static void dumpDebugInformation(HIDDevice hidDevice) throws IOException {
        LOGGER.debug("Manufacturer:\t{}", hidDevice.getManufacturerString());
        LOGGER.debug("Product:\t\t{}", hidDevice.getProductString());
        LOGGER.debug("SerialNumber:\t{}", hidDevice.getSerialNumberString());
    }
}
