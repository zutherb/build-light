package com.buildlight.application.interrogator;

import com.buildlight.respository.bamboo.api.BambooRepository;
import com.buildlight.respository.bamboo.model.BambooBuildResponse;
import com.buildlight.respository.bamboo.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zutherb
 */
@Component
public class BambooBuildInterrogator implements BuildInterrogator {

    @Autowired(required = false)
    private BambooRepository bambooRepository;

    @Override
    public boolean isResponsible() {
        return bambooRepository != null;
    }

    @Override
    public BuildState getCurrentBuildState() {
        BambooBuildResponse bambooBuildResponse = bambooRepository.getBambooBuildResponse();
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
