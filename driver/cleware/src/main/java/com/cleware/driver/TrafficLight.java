package com.cleware.driver;

/**
 * @author zutherb
 */
public interface TrafficLight {
    static TrafficLight INSTANCE = TrafficLightFactory.createNewInstance();

    void switchOn(Led led);

    void switchOff(Led led);

    void switchOnAllLeds();

    void switchOffAllLeds();

    void close();
}
