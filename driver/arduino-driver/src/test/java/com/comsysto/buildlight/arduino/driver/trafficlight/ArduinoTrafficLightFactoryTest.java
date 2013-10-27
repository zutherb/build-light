package com.comsysto.buildlight.arduino.driver.trafficlight;

import com.comsysto.buildlight.common.driver.core.TrafficLight;

/**
 * @author zutherb
 */
public class ArduinoTrafficLightFactoryTest {
    public static void main(String[] args) {
        TrafficLight light = ArduinoTrafficLightFactory.createNewInstance("test", "HIGH");
        light.switchOnAllLeds();
        light.switchOffAllLeds();
        light.close();
    }
}
