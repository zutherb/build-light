package com.cleware.commandline.command;

import com.cleware.driver.TrafficLight;

/**
 * @author zutherb
 */
public abstract class AbstractArgumentParser implements ArgumentParser {
    @Override
    public TrafficLight trafficLight() {
        return TrafficLight.INSTANCE;
    }
}
