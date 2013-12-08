package com.comsysto.buildlight.mock.simulator;

import com.comsysto.buildlight.respository.jenkins.model.Color;
import com.comsysto.buildlight.respository.jenkins.model.JenkinsBuildResponse;
import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * @author zutherb
 */
@Component
public class JenkinsSimulator {
    private RandomData randomData = new RandomDataImpl();
    private JenkinsBuildResponse.Builder responseBuilder = new JenkinsBuildResponse.Builder()
            .color(Color.blue);
    private List<Color> currentLifeCycle = Collections.emptyList();

    public JenkinsBuildResponse getBuildResponse() {
        return responseBuilder.build();
    }

    @Scheduled(fixedDelay = 2000)
    public void simulateBuilds() {
        if (isEmpty(currentLifeCycle)) {
            createLifeCycleIfNeeded();
        }
        responseBuilder.color(currentLifeCycle.remove(0));
    }

    private void createLifeCycleIfNeeded() {
        if (isEmpty(currentLifeCycle)) {
            int buildLifeCycleId = randomData.nextInt(0, 30);
            if (buildLifeCycleId < 20) {
                currentLifeCycle = new ArrayList<>(asList(Color.red, Color.red_anime, Color.blue));
            }
            if (buildLifeCycleId >= 20) {
                currentLifeCycle = new ArrayList<>(asList(Color.blue, Color.blue_anime, Color.blue));
            }
        }
    }


}
