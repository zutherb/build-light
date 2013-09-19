package com.comsysto.buildlight.application;

import com.comsysto.buildlight.application.interrogator.BuildInterrogator;
import com.comsysto.buildlight.application.interrogator.BuildState;
import com.comsysto.buildlight.common.driver.TrafficLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zutherb
 */
@Component
public class LedSwitcher {

    private TrafficLight light;
    private BuildState lastChangedBuildState;
    private List<BuildInterrogator> buildInterrogators;

    @Autowired
    public LedSwitcher(TrafficLight light, List<BuildInterrogator> buildInterrogators) {
        this.light = light;
        this.buildInterrogators = buildInterrogators;
    }

    @Scheduled(fixedDelay = 1000)
    public void changeTrafficLightLed() {
        for (BuildInterrogator buildInterrogator : buildInterrogators) {
            if (buildInterrogator.isResponsible()) {
                BuildState currentBuildState = buildInterrogator.getCurrentBuildState();
                changeLedIfNessary(currentBuildState);
            }
        }
    }

    private void changeLedIfNessary(BuildState currentBuildState) {
        if (!currentBuildState.equals(lastChangedBuildState) || BuildState.Building.equals(currentBuildState)) {
            lastChangedBuildState = currentBuildState;
            light.switchOffAllLeds();
            light.switchOn(currentBuildState.getColor());
        }
    }


}
