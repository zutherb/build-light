package com.comsysto.buildlight.application.adapter;

import com.comsysto.buildlight.respository.bamboo.api.BambooRepository;
import com.comsysto.buildlight.respository.bamboo.model.BambooPlanResponse;
import com.comsysto.buildlight.respository.bamboo.model.BambooResultResponse;
import com.comsysto.buildlight.respository.bamboo.model.Result;

import java.util.List;

/**
 * @author zutherb
 */
public class BambooAdapter implements BuildServerAdapter {

    private BambooRepository bambooRepository;

    public BambooAdapter(BambooRepository bambooRepository) {
        this.bambooRepository = bambooRepository;
    }

    @Override
    public BuildState getCurrentBuildState() {
        BambooPlanResponse planResponse = bambooRepository.getPlanResponse();
        if (planResponse.isBuilding()) {
            return BuildState.Building;
        }
        BambooResultResponse bambooResultResponse = bambooRepository.getResultResponse();
        List<Result> results = bambooResultResponse.getResults().getResults();
        Result result = results.get(0);
        switch (result.getState()) {
            case Successful:
                return BuildState.Successful;
            default:
                return BuildState.Failed;
        }
    }
}
