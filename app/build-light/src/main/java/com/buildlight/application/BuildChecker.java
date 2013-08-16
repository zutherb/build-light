package com.buildlight.application;

import com.buildlight.respository.bamboo.api.BambooRepository;
import com.buildlight.respository.bamboo.model.BambooBuildResponse;
import com.buildlight.respository.bamboo.model.Result;
import com.cleware.driver.TrafficLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zutherb
 */
@Component
public class BuildChecker {

    private TrafficLight light;
    private BambooRepository bambooRepository;
    private BuildState lastChangedBuildState;

    @Autowired
    public BuildChecker(TrafficLight light, BambooRepository bambooRepository) {
        this.light = light;
        this.bambooRepository = bambooRepository;
    }

    @Scheduled(fixedDelay = 1000)
    public void changeTrafficLightLed() {
        BambooBuildResponse bambooBuildResponse = bambooRepository.getBambooBuildResponse();
        BuildState currentBuildState = getBuildState(bambooBuildResponse);
        changeLedIfNessary(currentBuildState);
    }

    private void changeLedIfNessary(BuildState currentBuildState) {
        if (!currentBuildState.equals(lastChangedBuildState)) {
            lastChangedBuildState = currentBuildState;
            light.switchOffAllLeds();
            light.switchOn(currentBuildState.getLed());
        }
    }

    private BuildState getBuildState(BambooBuildResponse bambooBuildResponse) {
        List<Result> results = bambooBuildResponse.getResults().getResults();
        Result result = results.get(0);
        switch (result.getState()) {
            case Unkown:
                return BuildState.Building;
            case Successful:
                return BuildState.Successful;
            case Failed:
                return BuildState.Failed;
        }
        throw new IllegalArgumentException();
    }


}
