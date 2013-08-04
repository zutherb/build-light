package com.cleware.driver;

/**
 * @author zutherb
 */
public class DriverRunner {

    public static void main(String[] args) {
        TrafficLight light = TrafficLightFactory.createNewInstance();
        light.switchOnAllLeds();
        light.close();

        TrafficLight mockLight = new TrafficLightMock();
        mockLight.switchOnAllLeds();
        mockLight.close();
    }

}
