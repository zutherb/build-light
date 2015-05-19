package com.github.zutherb.buildlight.application.job;

import com.github.zutherb.buildlight.application.adapter.BuildServerAdapter;
import com.github.zutherb.buildlight.application.adapter.BuildServerAdapterFactory;
import com.github.zutherb.buildlight.application.adapter.BuildState;
import com.github.zutherb.buildlight.common.driver.core.TrafficLight;
import com.github.zutherb.buildlight.respository.common.BuildServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zutherb
 */
@Component
public class ColorSwitcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColorSwitcher.class);

    private TrafficLight light;
    private BuildServerAdapter buildServerAdapter;

    private List<BuildState> lastChangedBuildState;

    @Autowired
    public ColorSwitcher(@Qualifier("trafficLight") TrafficLight light,
                         BuildServerRepository repository) {
        this.light = light;
        this.buildServerAdapter = BuildServerAdapterFactory.create(repository);
    }

    @Scheduled(fixedDelay = 1000)
    public void changeTrafficLightLed() throws InterruptedException {
        try {
            List<BuildState> currentBuildState = buildServerAdapter.getCurrentBuildState();
            changeLedIfNessary(currentBuildState);
        } catch (Exception e) {
            LOGGER.error("An Error occurred: ", e);
        }
    }

    private void changeLedIfNessary(List<BuildState> currentBuildStates) throws InterruptedException {
        if (lastChangedBuildState == null || !lastChangedBuildState.containsAll(currentBuildStates)) {
            lastChangedBuildState = currentBuildStates;
            light.switchOffAllLeds();
            for (BuildState buildState : currentBuildStates) {
                light.switchOn(buildState.getColor());
            }
        }
    }
}
