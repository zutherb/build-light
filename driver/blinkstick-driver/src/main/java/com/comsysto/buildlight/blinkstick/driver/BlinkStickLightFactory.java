package com.comsysto.buildlight.blinkstick.driver;

import com.codeminders.hidapi.ClassPathLibraryLoader;
import com.comsysto.buildlight.common.driver.TrafficLight;

/**
 * @author zutherb
 */
public final class BlinkStickLightFactory {

    private BlinkStickLightFactory() { /* NOOP */ }

    static {
        ClassPathLibraryLoader.loadNativeHIDLibrary();
    }

    public static TrafficLight createNewInstance() {
        return new BlinkStickTrafficLightImpl(Blinktick.findFirst());
    }
}
