package com.comsysto.buildlight.blinkstick.driver;

import com.comsysto.buildlight.common.driver.AbstractTrafficLight;
import com.comsysto.buildlight.common.driver.Color;

/**
 * @author zutherb
 */
public class BlinkStickTrafficLightImpl extends AbstractTrafficLight<String> {

    private final Blinktick blinktick;

    public BlinkStickTrafficLightImpl(Blinktick blinktick) {
        this.blinktick = blinktick;
    }

    @Override
    public void switchOn(Color color) {
        blinktick.setColor(map(color));
    }

    @Override
    public void switchOff(Color color) {
        blinktick.turnOff();
    }

    @Override
    public void close() {
    }

    @Override
    protected String map(Color color) {
        return color.name().toLowerCase();
    }
}
