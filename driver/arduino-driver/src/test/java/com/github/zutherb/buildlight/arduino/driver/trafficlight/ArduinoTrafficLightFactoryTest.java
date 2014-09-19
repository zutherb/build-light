package com.github.zutherb.buildlight.arduino.driver.trafficlight;

import com.github.zutherb.buildlight.common.driver.core.TrafficLight;

/**
 * @author zutherb
 */
public class ArduinoTrafficLightFactoryTest {
    public static void main(String[] args) {
        TrafficLight light = ArduinoTrafficLightFactory.create("test", "HIGH");
        light.switchOnAllLeds();
        light.switchOffAllLeds();
        light.close();
    }
}
