package com.comsysto.buildlight.arduino.driver;

import com.comsysto.buildlight.common.driver.Color;
import com.comsysto.buildlight.common.driver.TrafficLight;

/**
 * @author zutherb
 */
public class TrafficLightFactoryTest {
    public static void main(String[] args) {
        TrafficLight light = TrafficLightFactory.createNewInstance();
        light.switchOnAllLeds();
        light.switchOffAllLeds();
        light.close();
    }
}
