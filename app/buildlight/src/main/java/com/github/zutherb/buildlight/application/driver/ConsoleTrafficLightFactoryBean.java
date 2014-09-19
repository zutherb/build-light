package com.github.zutherb.buildlight.application.driver;

import com.github.zutherb.buildlight.common.driver.core.TrafficLight;
import com.github.zutherb.buildlight.common.driver.trafficlight.ConsoleTrafficLight;

/**
 * @author zutherb
 */
public class ConsoleTrafficLightFactoryBean extends AbstractTrafficLightFactoryBean {

    @Override
    public TrafficLight getObject() throws Exception {
        return ConsoleTrafficLight.getInstance();
    }
}
