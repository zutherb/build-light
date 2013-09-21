package com.comsysto.buildlight.arduino.driver;

import com.comsysto.buildlight.common.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public final class TrafficLightFactory {

    public static final int RATE = 57600;

    private TrafficLightFactory() { /* NOOP */ }

    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficLightFactory.class);

    public static TrafficLight createNewInstance(String port) {
        Arduino arduino = new Arduino(port, RATE);
        return new TrafficLightImpl(arduino);
    }
}
