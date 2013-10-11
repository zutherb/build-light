package com.comsysto.buildlight.arduino.driver;

import com.comsysto.buildlight.common.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public final class ArduinoTrafficLightFactory {

    public static final int RATE = 57600;

    private ArduinoTrafficLightFactory() { /* NOOP */ }

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoTrafficLightFactory.class);

    public static TrafficLight createNewInstance(String port) {
        Arduino arduino = new Arduino(port, RATE);
        return new ArduinoTrafficLightImpl(arduino);
    }
}
