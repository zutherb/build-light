package com.github.zutherb.buildlight.common.driver.core;

/**
 * @author zutherb
 */
public abstract class AbstractTrafficLight<T> implements TrafficLight {

    @Override
    public void switchOnAllLeds() {
        for (Color color : Color.values()) {
            switchOn(color);
        }
    }

    @Override
    public void switchOffAllLeds() {
        for (Color color : Color.values()) {
            switchOff(color);
        }
    }

    protected abstract T map(Color color);
}
