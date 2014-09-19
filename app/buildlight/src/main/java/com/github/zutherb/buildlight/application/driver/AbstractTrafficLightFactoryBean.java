package com.github.zutherb.buildlight.application.driver;

import com.github.zutherb.buildlight.common.driver.core.TrafficLight;
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
