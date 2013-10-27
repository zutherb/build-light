package com.comsysto.buildlight.application.driver;

import com.comsysto.buildlight.arduino.driver.trafficlight.ArduinoTrafficLightFactory;
import com.comsysto.buildlight.common.driver.core.TrafficLight;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zutherb
 */
public class ArduinoDriverFactoryBean extends AbstractTrafficLightFactoryBean {

    @Value("${traffic.light.arduino.port}")
    private String trafficLightPort;

    @Value("${traffic.light.switch.on.level}")
    private String switchOnLevel;

    @Override
    public TrafficLight getObject() throws Exception {
        return ArduinoTrafficLightFactory.createNewInstance(trafficLightPort, switchOnLevel);
    }
}
