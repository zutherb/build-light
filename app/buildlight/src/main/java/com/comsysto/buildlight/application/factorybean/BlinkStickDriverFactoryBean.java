package com.comsysto.buildlight.application.factorybean;

import com.comsysto.buildlight.blinkstick.driver.BlinkStickLightFactory;
import com.comsysto.buildlight.common.driver.TrafficLight;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author zutherb
 */
public class BlinkStickDriverFactoryBean implements FactoryBean<TrafficLight> {

    @Override
    public TrafficLight getObject() throws Exception {
        return BlinkStickLightFactory.createNewInstance();
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
