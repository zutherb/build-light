package com.comsysto.buildlight.application.driver;

import com.comsysto.buildlight.common.driver.core.TrafficLight;
import com.comsysto.buildlight.common.driver.trafficlight.ConsoleTrafficLight;

/**
 * @author zutherb
 */
public class ConsoleTrafficLightFactoryBean extends AbstractTrafficLightFactoryBean {

    @Override
    public TrafficLight getObject() throws Exception {
        return ConsoleTrafficLight.getInstance();
    }
}
