package com.comsysto.buildlight.application.driver;

import com.comsysto.buildlight.common.driver.core.TrafficLight;
import com.comsysto.buildlight.common.driver.trafficlight.TrafficLightMock;

/**
 * @author zutherb
 */
public class TrafficLightMockFactoryBean extends AbstractTrafficLightFactoryBean {

    @Override
    public TrafficLight getObject() throws Exception {
        return TrafficLightMock.getInstance();
    }
}
