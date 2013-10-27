package com.comsysto.buildlight.arduino.driver.trafficlight;

import com.comsysto.buildlight.arduino.driver.core.Arduino;

import static java.lang.String.format;

/**
 * @author zutherb
 */
public enum SwitchOnLevel {
    HIGH(Arduino.HIGH),
    LOW(Arduino.LOW);

    private final int levelValue;

    SwitchOnLevel(int levelValue) {
        this.levelValue = levelValue;
    }

    public int getLevelValue() {
        return levelValue;
    }

    public static SwitchOnLevel fromString(String name) {
        for (SwitchOnLevel level : values()) {
            if (level.name().equalsIgnoreCase(name)) {
                return level;
            }
        }
        throw new IllegalArgumentException(format("Could not found SwitchOnLevel for %s", name));
    }

    public static SwitchOnLevel neg(SwitchOnLevel switchOnLevel) {
        switch (switchOnLevel) {
            case HIGH:
                return LOW;
            case LOW:
                return HIGH;
            default:
                throw new IllegalArgumentException(format("Could not found negative SwitchOnLevel for %s", switchOnLevel.name()));
        }
    }
}
