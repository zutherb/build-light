package com.github.zutherb.buildlight.application.driver;

import com.github.zutherb.buildlight.arduino.driver.trafficlight.ArduinoTrafficLightFactory;
import com.github.zutherb.buildlight.common.driver.core.TrafficLight;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zutherb
 */
public class ArduinoDriverFactoryBean extends AbstractTrafficLightFactoryBean {

    @Value("${buildlight.arduino.port}")
    private String trafficLightPort;

    @Value("${buildlight.arduino.baudrate}")
    private int baudRate;

    @Value("${buildlight.arduino.switch.on.level}")
    private String switchOnLevel;

    @Override
    public TrafficLight getObject() throws Exception {
        return ArduinoTrafficLightFactory.create(trafficLightPort, baudRate, switchOnLevel);
    }
}
