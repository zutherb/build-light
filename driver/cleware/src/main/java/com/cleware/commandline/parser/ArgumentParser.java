package com.cleware.commandline.parser;

import com.cleware.driver.TrafficLight;

/**
 * @author zutherb
 */
public interface ArgumentParser {
    boolean isResponsible(ArgumentBuffer buffer);

    void execute(ArgumentBuffer buffer);

    TrafficLight trafficLight();
}
