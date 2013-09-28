package com.comsysto.buildlight.application.adapter;


import com.comsysto.buildlight.common.driver.Color;

/**
 * @author zutherb
 */
public enum BuildState {
    Successful(Color.GREEN),
    Building(Color.YELLOW),
    Failed(Color.RED);

    private Color color;

    BuildState(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
