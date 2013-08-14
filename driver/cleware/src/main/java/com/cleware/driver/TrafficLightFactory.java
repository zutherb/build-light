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
public final class TrafficLightFactory {

    private TrafficLightFactory() { /*NOOP*/ }

    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficLightFactory.class);
    private static final TrafficLight INSTANCE = createNewInstance();

    private static final int VENDOR_ID = 0xD50;     //Cleware Vendor Id
    private static final int PRODUCT_ID = 0x8;      //Traffic Light Product Id

    /**
     * Methods returns a traffic light implementation.
     * <p/>
     * When an usb device is connect it returns an implemation that can access the usb device.
     * If no device is connected and mock traffic light is return that prints access to the usb device to the console.
     *
     * @return traffic light implementation
     */
    public static TrafficLight instance() {
        return INSTANCE;
    }

    private static TrafficLight createNewInstance() {
        try {
            ClassPathLibraryLoader.loadNativeHIDLibrary();
            HIDManager hidManager = HIDManager.getInstance();
            HIDDevice hidDevice = hidManager.openById(VENDOR_ID, PRODUCT_ID, null);
            dumpDebugInformation(hidDevice);
            return new TrafficLightImpl(hidManager, hidDevice);
        } catch (IOException e) {
            LOGGER.error("Traffic light USB device could not be found. A mock was created instate", e);
            return new TrafficLightMock();
        }
    }

    private static void dumpDebugInformation(HIDDevice hidDevice) throws IOException {
        LOGGER.debug("Manufacturer:\t{}", hidDevice.getManufacturerString());
        LOGGER.debug("Product:\t\t{}", hidDevice.getProductString());
        LOGGER.debug("SerialNumber:\t{}", hidDevice.getSerialNumberString());
    }
}
