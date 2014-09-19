package com.github.zutherb.buildlight.application.driver;

import com.github.zutherb.buildlight.blinkstick.driver.trafficlight.BlinkStickLightFactory;
import com.github.zutherb.buildlight.common.driver.core.TrafficLight;

/**
 * @author zutherb
 */
public class BlinkStickDriverFactoryBean extends AbstractTrafficLightFactoryBean {

    @Override
    public TrafficLight getObject() throws Exception {
        return BlinkStickLightFactory.createNewInstance();
    }
}
