package com.comsysto.buildlight.application.factorybean;

import com.comsysto.buildlight.arduino.driver.ArduinoTrafficLightFactory;
import com.comsysto.buildlight.common.driver.TrafficLight;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zutherb
 */
public class ArduinoDriverFactoryBean implements FactoryBean<TrafficLight> {

    @Value("${traffic.light.arduino.port}")
    private String trafficLightPort;

    @Value("${traffic.light.switch.on.level}")
    private String switchOnLevel;

    @Override
    public TrafficLight getObject() throws Exception {
        return ArduinoTrafficLightFactory.createNewInstance(trafficLightPort, switchOnLevel);
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
