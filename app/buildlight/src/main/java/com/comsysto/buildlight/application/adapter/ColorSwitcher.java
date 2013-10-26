package com.comsysto.buildlight.application.adapter;

import com.comsysto.buildlight.common.driver.TrafficLight;
import com.comsysto.buildlight.respository.common.BuildServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component
public class ColorSwitcher {

    private TrafficLight light;
    private BuildServerAdapter buildServerAdapter;

    private BuildState lastChangedBuildState;

    @Autowired
    public ColorSwitcher(@Qualifier("trafficLight") TrafficLight light,
                         BuildServerRepository repository) {
        this.light = light;
        this.buildServerAdapter = BuildServerAdapterFactory.create(repository);
    }

    @Scheduled(fixedDelay = 1000)
    public void changeTrafficLightLed() throws InterruptedException {
        BuildState currentBuildState = buildServerAdapter.getCurrentBuildState();
        changeLedIfNessary(currentBuildState);
    }

    private void changeLedIfNessary(BuildState currentBuildState) throws InterruptedException {
        if (!currentBuildState.equals(lastChangedBuildState)) {
            lastChangedBuildState = currentBuildState;
            light.switchOffAllLeds();
            light.switchOn(currentBuildState.getColor());
        }
    }


}
