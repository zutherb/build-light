package com.comsysto.buildlight.cleware.driver;

/**
 * @author zutherb
 */
public class TrafficLightFactoryTest {
    public static void main(String[] args) {
        TrafficLight light = TrafficLightFactory.createNewInstance();
        light.switchOn(Led.RED);
        light.switchOffAllLeds();
        light.close();
    }
}
