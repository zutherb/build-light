package com.buildlight.application.interrogator;

import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component
public class JenkinsBuildInterrogator implements BuildInterrogator {

    @Override
    public boolean isResponsible() {
        return false;
    }

    @Override
    public BuildState getCurrentBuildState() {
        return BuildState.Failed;
    }
}
