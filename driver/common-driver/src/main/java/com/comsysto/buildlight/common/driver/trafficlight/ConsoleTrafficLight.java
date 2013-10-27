package com.comsysto.buildlight.common.driver.trafficlight;

import com.comsysto.buildlight.common.driver.core.Color;
import com.comsysto.buildlight.common.driver.core.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public class ConsoleTrafficLight implements TrafficLight {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleTrafficLight.class);
    private static TrafficLight instance;

    private ConsoleTrafficLight() {
        LOGGER.info("Traffic Light Mock was created");
    }

    @Override
    public void switchOn(Color color) {
        LOGGER.info("Switch on Led {}", color.name());
    }

    @Override
    public void switchOff(Color color) {
        LOGGER.info("Switch off Led {}", color.name());
    }

    @Override
    public void switchOnAllLeds() {
        LOGGER.info("Switch on all Led");
    }

    @Override
    public void switchOffAllLeds() {
        LOGGER.info("Switch off all Led");
    }

    @Override
    public void close() {
        LOGGER.info("Close traffic light");
    }

    public static TrafficLight getInstance() {
        if (instance == null) {
            instance = new ConsoleTrafficLight();
        }
        return instance;
    }
}
