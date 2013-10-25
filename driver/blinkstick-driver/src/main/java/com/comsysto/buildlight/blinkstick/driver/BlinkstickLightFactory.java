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
public final class BlinkStickLightFactory {

    private BlinkStickLightFactory() { /* NOOP */ }

    static {
        ClassPathLibraryLoader.loadNativeHIDLibrary();
    }

    public static TrafficLight createNewInstance() {
        return new BlinkStickTrafficLightImpl(BlinkStick.findFirst());
    }
}
