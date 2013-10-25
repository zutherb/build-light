package com.comsysto.buildlight.blinkstick.driver;

import com.comsysto.buildlight.common.driver.Color;
import com.comsysto.buildlight.common.driver.TrafficLight;

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
