package com.github.zutherb.buildlight.blinkstick.driver.trafficlight;

import com.github.zutherb.buildlight.common.driver.core.Color;
import com.github.zutherb.buildlight.common.driver.core.TrafficLight;

/**
 * @author zutherb
 */
public class BlinkStickLightFactoryTest {
    public static void main(String[] args) {
        TrafficLight light = BlinkStickLightFactory.createNewInstance();
        light.switchOn(Color.RED);
        light.switchOffAllLeds();
        light.close();
    }
}
