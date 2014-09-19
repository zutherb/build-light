package com.github.zutherb.buildlight.cleware.driver.trafficlight;

import com.github.zutherb.buildlight.common.driver.core.Color;
import com.github.zutherb.buildlight.common.driver.core.TrafficLight;

/**
 * @author zutherb
 */
public class ClewareLightFactoryTest {
    public static void main(String[] args) {
        TrafficLight light = ClewareLightFactory.createNewInstance();
        light.switchOn(Color.RED);
        light.switchOffAllLeds();
        light.close();
    }
}
