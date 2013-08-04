package com.cleware.driver;

/**
 * @author zutherb
 */
public class DriverRunner {

    public static void main(String[] args) {
        TrafficLight light = TrafficLight.createInstance();
        light.switchOnAllLeds();
        light.close();
    }

}
