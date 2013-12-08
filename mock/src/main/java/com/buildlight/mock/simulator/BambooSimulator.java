package com.comsysto.buildlight.mock.simulator;

import com.comsysto.buildlight.respository.bamboo.model.*;
import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * @author zutherb
 */
@Component
public class BambooSimulator {
    private RandomData randomData = new RandomDataImpl();
    private AtomicInteger buildIdGenerator = new AtomicInteger(0);
    private AtomicInteger resultIdGenerator = new AtomicInteger(10000);

    private Results.Builder resultsBuilder = new Results.Builder()
            .expand("result");
    private Result.Builder resultBuilder = new Result.Builder();
    private BambooResultResponse.Builder responseBuilder = new BambooResultResponse.Builder()
            .results(resultsBuilder.build());
    private List<LifeCycleState> currentLifeCycle = Collections.emptyList();

    public BambooResultResponse getBuildResponse() {
        return responseBuilder.build();
    }

    @Scheduled(fixedDelay = 2000)
    public void simulateBuilds() {
        createLifeCycleIfNeeded();
        LifeCycleState currentLifeCyleState = currentLifeCycle.remove(0);
        createNewResultIfNeeded(currentLifeCyleState);
        setStatesForCurrentLifeCycle(currentLifeCyleState);
        resetPropertiesForNewLifeCycle();
    }

    private void createNewResultIfNeeded(LifeCycleState currentLifeCyleState) {
        if (LifeCycleState.Queued.equals(currentLifeCyleState)) {
            resultsBuilder.addResult(resultBuilder.build());
        }
    }

    private void setStatesForCurrentLifeCycle(LifeCycleState currentLifeCyleState) {
        State state = getBuildState(currentLifeCyleState);
        resultBuilder.lifeCycleState(currentLifeCyleState).state(state);
    }

    private void resetPropertiesForNewLifeCycle() {
        if (isEmpty(currentLifeCycle)) {
            resultBuilder = new Result.Builder()
                    .id(resultIdGenerator.incrementAndGet())
                    .number(buildIdGenerator.incrementAndGet());
            List<Result> results = resultsBuilder.build().getResults();
            //truncate results to avoid memory leaks
            if (results.size() > 20) {
                resultsBuilder.results(results.subList(0, 20));
            }
        }
    }

    private State getBuildState(LifeCycleState nextLifeCyleState) {
        switch (nextLifeCyleState) {
            case Finished:
                return State.Successful;
            case NotBuilt:
                return State.Failed;
            default:
                return State.Unkown;
        }
    }

    private void createLifeCycleIfNeeded() {
        if (isEmpty(currentLifeCycle)) {
            int buildLifeCycleId = randomData.nextInt(0, 30);
            if (buildLifeCycleId < 20) {
                currentLifeCycle = new ArrayList<>(LifeCycleState.getSuccessfulBambooLifeCycle());
            }
            if (buildLifeCycleId >= 20) {
                currentLifeCycle = new ArrayList<>(LifeCycleState.getFailedBambooLifeCycle());
            }
        }
    }
}
