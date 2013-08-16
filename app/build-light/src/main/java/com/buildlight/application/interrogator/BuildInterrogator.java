package com.buildlight.application.interrogator;

/**
 * @author zutherb
 */
public interface BuildInterrogator {
    boolean isResponsible();

    BuildState getCurrentBuildState();
}
