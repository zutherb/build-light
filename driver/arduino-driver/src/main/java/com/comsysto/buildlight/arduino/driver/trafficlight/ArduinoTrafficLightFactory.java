package com.comsysto.buildlight.arduino.driver.trafficlight;

import com.comsysto.buildlight.arduino.driver.core.Arduino;
import com.comsysto.buildlight.common.driver.core.TrafficLight;

/**
 * @author zutherb
 */
public final class ArduinoTrafficLightFactory {

    public static final int DEFAULT_BAUD_RATE = 57600;

    private ArduinoTrafficLightFactory() { /* NOOP */ }

    public static TrafficLight createNewInstance(String port, String levelName) {
        Arduino arduino = new Arduino(port, DEFAULT_BAUD_RATE);
        return new ArduinoTrafficLightImpl(arduino, SwitchOnLevel.fromString(levelName));
    }

    public static TrafficLight createNewInstance(String port, int baudRate, String levelName) {
        Arduino arduino = new Arduino(port, baudRate);
        return new ArduinoTrafficLightImpl(arduino, SwitchOnLevel.fromString(levelName));
    }
}
