package com.comsysto.buildlight.blinkstick.commandline.parser;


import com.comsysto.buildlight.common.driver.TrafficLight;

/**
 * @author zutherb
 */
public abstract class AbstractArgumentParser implements ArgumentParser {

    private TrafficLight trafficLight;

    public AbstractArgumentParser(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    @Override
    public TrafficLight trafficLight() {
        return trafficLight;
    }
}
