package com.comsysto.buildlight.arduino.driver;

import com.comsysto.buildlight.common.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public final class TrafficLightFactory {

    private TrafficLightFactory() { /* NOOP */ }

    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficLightFactory.class);

    public static TrafficLight createNewInstance() {
        return new TrafficLightImpl(new Arduino(Arduino.list()[8], 57600));
    }
}
