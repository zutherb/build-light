package com.github.zutherb.buildlight.blinkstick.driver.trafficlight;

import com.github.zutherb.buildlight.blinkstick.driver.core.BlinkStick;
import com.github.zutherb.buildlight.common.driver.core.AbstractTrafficLight;
import com.github.zutherb.buildlight.common.driver.core.Color;

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
