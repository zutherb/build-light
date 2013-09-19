package com.comsysto.buildlight.cleware.commandline.parser;

import com.comsysto.buildlight.common.driver.TrafficLight;

/**
 * @author zutherb
 */
public interface ArgumentParser {
    boolean isResponsible(ArgumentBuffer buffer);

    void execute(ArgumentBuffer buffer, StringBuffer outputBuffer);

    TrafficLight trafficLight();
}
