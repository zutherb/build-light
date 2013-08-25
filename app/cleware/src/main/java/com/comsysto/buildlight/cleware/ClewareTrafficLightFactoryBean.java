package com.comsysto.buildlight.cleware;

import com.comsysto.buildlight.cleware.driver.TrafficLight;
import com.comsysto.buildlight.cleware.driver.TrafficLightException;
import com.comsysto.buildlight.cleware.driver.TrafficLightFactory;
import com.comsysto.buildlight.cleware.driver.TrafficLightMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component("trafficLight")
public class ClewareTrafficLightFactoryBean implements FactoryBean<TrafficLight> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClewareTrafficLightFactoryBean.class);
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
            try {
                LIGHT_INSTANCE = TrafficLightFactory.createNewInstance();
            } catch (TrafficLightException e) {
                LOGGER.error("Traffic Light USB device could not be found", e);
                LOGGER.info("Traffic Light Mock is used instate.");
                LIGHT_INSTANCE = new TrafficLightMock();
            }
        }
        return LIGHT_INSTANCE;
    }
}
