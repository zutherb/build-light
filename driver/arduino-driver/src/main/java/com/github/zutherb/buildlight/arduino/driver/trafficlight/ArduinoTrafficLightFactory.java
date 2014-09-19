package com.github.zutherb.buildlight.arduino.driver.trafficlight;

import com.github.zutherb.buildlight.arduino.driver.core.Arduino;
import com.github.zutherb.buildlight.common.driver.core.TrafficLight;

/**
 * @author zutherb
 */
public final class ArduinoTrafficLightFactory {

    public static final int DEFAULT_BAUD_RATE = 57600;

    private ArduinoTrafficLightFactory() { /* NOOP */ }

    public static TrafficLight create(String port, String levelName) {
        Arduino arduino = new Arduino(port, DEFAULT_BAUD_RATE);
        return new ArduinoTrafficLightImpl(arduino, SwitchOnLevel.fromString(levelName));
    }

    public static TrafficLight create(String port, int baudRate, String levelName) {
        Arduino arduino = new Arduino(port, baudRate);
        return new ArduinoTrafficLightImpl(arduino, SwitchOnLevel.fromString(levelName));
    }
}
