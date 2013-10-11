package com.comsysto.buildlight.arduino.driver;

import com.comsysto.buildlight.common.driver.TrafficLight;

/**
 * @author zutherb
 */
public class ArduinoTrafficLightFactoryTest {
    public static void main(String[] args) {
        TrafficLight light = ArduinoTrafficLightFactory.createNewInstance("test");
        light.switchOnAllLeds();
        light.switchOffAllLeds();
        light.close();
    }
}
