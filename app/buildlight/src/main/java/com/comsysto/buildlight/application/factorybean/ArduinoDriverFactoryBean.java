package com.comsysto.buildlight.application.factorybean;

import com.comsysto.buildlight.arduino.driver.TrafficLightFactory;
import com.comsysto.buildlight.common.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zutherb
 */
public class ArduinoDriverFactoryBean implements FactoryBean<TrafficLight> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoDriverFactoryBean.class);

    @Value("${traffic.light.port}")
    private String trafficLightPort;

    @Override
    public TrafficLight getObject() throws Exception {
        return TrafficLightFactory.createNewInstance(trafficLightPort);
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
