package com.comsysto.buildlight.blinkstick.driver.trafficlight;

import com.comsysto.buildlight.blinkstick.driver.core.BlinkStick;
import com.comsysto.buildlight.common.driver.core.AbstractTrafficLight;
import com.comsysto.buildlight.common.driver.core.Color;

/**
 * @author zutherb
 */
public class BlinkStickTrafficLightImpl extends AbstractTrafficLight<String> {

    private final BlinkStick blinkStick;

    public BlinkStickTrafficLightImpl(BlinkStick blinkStick) {
        this.blinkStick = blinkStick;
    }

    @Override
    public void switchOn(Color color) {
        blinkStick.setColor(map(color));
    }

    @Override
    public void switchOff(Color color) {
        blinkStick.turnOff();
    }

    @Override
    public void close() {
    }

    @Override
    protected String map(Color color) {
        return color.name().toLowerCase();
    }
}
