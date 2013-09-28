package com.comsysto.buildlight.application.adapter;

/**
 * @author zutherb
 */
public interface BuildServerAdapter {
    boolean isResponsible();

    BuildState getCurrentBuildState();
}
