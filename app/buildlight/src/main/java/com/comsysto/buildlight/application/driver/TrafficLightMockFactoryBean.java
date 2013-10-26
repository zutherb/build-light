package com.comsysto.buildlight.application.driver;

import com.comsysto.buildlight.common.driver.TrafficLight;
import com.comsysto.buildlight.common.driver.TrafficLightMock;

/**
 * @author zutherb
 */
public class TrafficLightMockFactoryBean extends AbstractTrafficLightFactoryBean {

    @Override
    public TrafficLight getObject() throws Exception {
        return TrafficLightMock.getInstance();
    }
}
