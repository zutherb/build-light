package com.github.zutherb.buildlight.application.adapter;

import com.github.zutherb.buildlight.respository.jenkins.api.JenkinsRepository;
import com.github.zutherb.buildlight.respository.jenkins.model.JenkinsBuildResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zutherb
 */
public class JenkinsAdapter implements BuildServerAdapter {

    private JenkinsRepository jenkinsRepository;

    public JenkinsAdapter(JenkinsRepository jenkinsRepository) {
        this.jenkinsRepository = jenkinsRepository;
    }

    @Override
    public List<BuildState> getCurrentBuildState() {
        List<JenkinsBuildResponse> buildResponses = jenkinsRepository.getBuildResponse();
        return getCurrentBuildState(buildResponses);
    }

    private List<BuildState> getCurrentBuildState(List<JenkinsBuildResponse> buildResponses) {
        List<BuildState> results = new ArrayList<>(Arrays.asList(BuildState.Successful));
        for (JenkinsBuildResponse response : buildResponses) {
            if (BuildState.Failed.equals(getCurrentBuildState(response)) && response.getDisplayName().contains("ui-test")) {
                results.add(BuildState.Building);
            }
            if (BuildState.Failed.equals(getCurrentBuildState(response)) && !response.getDisplayName().contains("ui-test")) {
                results.remove(BuildState.Successful);
                results.add(BuildState.Failed);
            }
        }
        return results;
    }

    private BuildState getCurrentBuildState(JenkinsBuildResponse buildResponse) {
        switch (buildResponse.getColor()) {
            case red:
            case yellow:
            case aborted:
                return BuildState.Failed;
            case blue:
                return BuildState.Successful;
            default:
                return BuildState.Building;
        }
    }
}
