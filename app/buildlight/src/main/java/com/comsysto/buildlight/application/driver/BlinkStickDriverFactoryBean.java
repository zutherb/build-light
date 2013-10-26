package com.comsysto.buildlight.application.driver;

import com.comsysto.buildlight.blinkstick.driver.BlinkStickLightFactory;
import com.comsysto.buildlight.common.driver.TrafficLight;

/**
 * @author zutherb
 */
public class BlinkStickDriverFactoryBean extends AbstractTrafficLightFactoryBean {

    @Override
    public TrafficLight getObject() throws Exception {
        return BlinkStickLightFactory.createNewInstance();
    }
}
