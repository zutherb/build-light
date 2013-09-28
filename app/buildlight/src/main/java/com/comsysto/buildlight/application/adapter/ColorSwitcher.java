package com.comsysto.buildlight.application.adapter;

import com.comsysto.buildlight.common.driver.TrafficLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zutherb
 */
@Component
public class ColorSwitcher {

    private TrafficLight light;
    private BuildState lastChangedBuildState;
    private List<BuildServerAdapter> buildServerAdapters;

    @Autowired
    public ColorSwitcher(TrafficLight light, List<BuildServerAdapter> buildServerAdapters) {
        this.light = light;
        this.buildServerAdapters = buildServerAdapters;
    }

    @Scheduled(fixedDelay = 1000)
    public void changeTrafficLightLed() {
        for (BuildServerAdapter buildServerAdapter : buildServerAdapters) {
            if (buildServerAdapter.isResponsible()) {
                BuildState currentBuildState = buildServerAdapter.getCurrentBuildState();
                changeLedIfNessary(currentBuildState);
            }
        }
    }

    private void changeLedIfNessary(BuildState currentBuildState) {
        if (!currentBuildState.equals(lastChangedBuildState)) {
            lastChangedBuildState = currentBuildState;
            light.switchOffAllLeds();
            light.switchOn(currentBuildState.getColor());
        }
    }


}
