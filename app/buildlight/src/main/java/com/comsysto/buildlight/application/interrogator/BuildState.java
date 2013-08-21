package com.comsysto.buildlight.application.interrogator;

import com.cleware.driver.Led;

/**
 * @author zutherb
 */
public enum BuildState {
    Successful(Led.GREEN),
    Building(Led.YELLOW),
    Failed(Led.RED);

    private Led led;

    BuildState(Led led) {
        this.led = led;
    }

    public Led getLed() {
        return led;
    }
}
