package com.cleware.driver;

/**
 * @author zutherb
 */
public class DriverTestRunner {

    public static void main(String[] args) throws InterruptedException {
        TrafficLight light = TrafficLightFactory.instance();
        light.switchOnAllLeds();
        Thread.sleep(1000);
        light.switchOffAllLeds();
        light.close();
    }

}
