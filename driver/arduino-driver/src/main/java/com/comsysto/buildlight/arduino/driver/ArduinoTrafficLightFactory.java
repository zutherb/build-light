package com.comsysto.buildlight.arduino.driver;

import com.comsysto.buildlight.common.driver.TrafficLight;

import static com.comsysto.buildlight.arduino.driver.SwitchOnLevel.fromString;

/**
 * @author zutherb
 */
public final class ArduinoTrafficLightFactory {

    public static final int RATE = 57600;

    private ArduinoTrafficLightFactory() { /* NOOP */ }

    public static TrafficLight createNewInstance(String port, String levelName) {
        Arduino arduino = new Arduino(port, RATE);
        return new ArduinoTrafficLightImpl(arduino, fromString(levelName));
    }
}
