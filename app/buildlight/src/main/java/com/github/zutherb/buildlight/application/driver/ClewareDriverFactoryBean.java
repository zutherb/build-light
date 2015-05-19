package com.github.zutherb.buildlight.application.driver;

import com.github.zutherb.buildlight.cleware.driver.trafficlight.ClewareLightFactory;
import com.github.zutherb.buildlight.common.driver.core.TrafficLight;

/**
 * @author zutherb
 */
public class ClewareDriverFactoryBean extends AbstractTrafficLightFactoryBean {

    @Override
    public TrafficLight getObject() throws Exception {
        return ClewareLightFactory.createNewInstance();
    }
}
