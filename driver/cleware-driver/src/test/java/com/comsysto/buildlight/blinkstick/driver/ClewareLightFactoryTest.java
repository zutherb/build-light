package com.comsysto.buildlight.blinkstick.driver;

import com.comsysto.buildlight.common.driver.Color;
import com.comsysto.buildlight.common.driver.TrafficLight;

/**
 * @author zutherb
 */
public class ClewareLightFactoryTest {
    public static void main(String[] args) {
        TrafficLight light = ClewareLightFactory.createNewInstance();
        light.switchOn(Color.RED);
        light.switchOffAllLeds();
        light.close();
    }
}
