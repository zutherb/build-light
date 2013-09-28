package com.comsysto.buildlight.application.factorybean;

import com.comsysto.buildlight.cleware.driver.ClewareLightFactory;
import com.comsysto.buildlight.common.driver.TrafficLight;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author zutherb
 */
public class ClewareDriverFactoryBean implements FactoryBean<TrafficLight> {

    @Override
    public TrafficLight getObject() throws Exception {
        return ClewareLightFactory.createNewInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return TrafficLight.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
