package com.comsysto.buildlight.application.driver;

import com.comsysto.buildlight.common.driver.TrafficLight;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author zutherb
 */
public abstract class AbstractTrafficLightFactoryBean implements FactoryBean<TrafficLight> {

    @Override
    public Class<?> getObjectType() {
        return TrafficLight.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
