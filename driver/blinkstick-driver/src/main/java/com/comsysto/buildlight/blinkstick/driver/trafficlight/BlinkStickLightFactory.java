package com.comsysto.buildlight.blinkstick.driver.trafficlight;

import com.codeminders.hidapi.ClassPathLibraryLoader;
import com.comsysto.buildlight.blinkstick.driver.core.BlinkStick;
import com.comsysto.buildlight.common.driver.core.TrafficLight;

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
