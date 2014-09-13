package com.comsysto.buildlight.application.adapter;

import com.comsysto.buildlight.respository.jenkins.api.JenkinsRepository;
import com.comsysto.buildlight.respository.jenkins.model.Build;
import com.comsysto.buildlight.respository.jenkins.model.JenkinsBuildResponse;

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
    public BuildState getCurrentBuildState() {
        List<JenkinsBuildResponse> buildResponses = jenkinsRepository.getBuildResponse();
        return getCurrentBuildState(buildResponses);
    }

    private BuildState getCurrentBuildState(List<JenkinsBuildResponse> buildResponses) {
        BuildState result = BuildState.Successful;
        for(JenkinsBuildResponse response : buildResponses){
            if (BuildState.Building.equals(getCurrentBuildState(response)) && response.getDisplayName().contains("ui-test")){
                result = BuildState.Building;
            }
            if (BuildState.Failed.equals(getCurrentBuildState(response))){
                result = BuildState.Failed;
            }
        }
        return result;
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
