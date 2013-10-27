package com.comsysto.buildlight.blinkstick.driver.trafficlight;

import com.comsysto.buildlight.common.driver.core.Color;
import com.comsysto.buildlight.common.driver.core.TrafficLight;

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
