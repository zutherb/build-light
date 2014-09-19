package com.github.zutherb.buildlight.blinkstick.driver.trafficlight;

import com.codeminders.hidapi.ClassPathLibraryLoader;
import com.github.zutherb.buildlight.blinkstick.driver.core.BlinkStick;
import com.github.zutherb.buildlight.common.driver.core.TrafficLight;

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
