package com.comsysto.buildlight.application.driver;

import com.comsysto.buildlight.blinkstick.driver.ClewareLightFactory;
import com.comsysto.buildlight.common.driver.TrafficLight;

/**
 * @author zutherb
 */
public class ClewareDriverFactoryBean extends AbstractTrafficLightFactoryBean {

    @Override
    public TrafficLight getObject() throws Exception {
        return ClewareLightFactory.createNewInstance();
    }
}
