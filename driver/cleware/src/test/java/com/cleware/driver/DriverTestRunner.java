package com.cleware.driver;

/**
 * @author zutherb
 */
public class DriverTestRunner {

    public static void main(String[] args) {
        TrafficLight light = TrafficLightFactory.createNewInstance();
        light.switchOnAllLeds();
        light.close();
    }

}
