package com.cleware;

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

    @Override
    public TrafficLight getObject() throws Exception {
        try {
            return TrafficLightFactory.createNewInstance();
        } catch (TrafficLightException e) {
            LOGGER.error("Traffic light USB device could not be found", e);
            LOGGER.info("Traffic Light Mock is used instate.");
            return new TrafficLightMock();
        }
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
