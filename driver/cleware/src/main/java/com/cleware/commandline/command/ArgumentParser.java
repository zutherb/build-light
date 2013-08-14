package com.cleware.commandline.command;

import com.cleware.driver.TrafficLight;

/**
 * @author zutherb
 */
public interface ArgumentParser {
    boolean isResponsible(ArgumentBuffer buffer);

    void execute(ArgumentBuffer buffer);

    TrafficLight trafficLight();
}
