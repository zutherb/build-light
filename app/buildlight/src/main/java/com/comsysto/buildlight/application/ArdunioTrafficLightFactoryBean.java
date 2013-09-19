package com.comsysto.buildlight.application;

import com.comsysto.buildlight.arduino.driver.TrafficLightFactory;
import com.comsysto.buildlight.common.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component("trafficLight")
public class ArdunioTrafficLightFactoryBean implements FactoryBean<TrafficLight> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArdunioTrafficLightFactoryBean.class);
    private static TrafficLight LIGHT_INSTANCE;

    @Override
    public TrafficLight getObject() throws Exception {
        if (LIGHT_INSTANCE == null) {
            instance();
        }
        return LIGHT_INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return TrafficLight.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public static TrafficLight instance() {
        if (LIGHT_INSTANCE == null) {
            LIGHT_INSTANCE = TrafficLightFactory.createNewInstance();
        }
        return LIGHT_INSTANCE;
    }
}
