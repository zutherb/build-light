package com.comsysto.buildlight.application.adapter;

import com.comsysto.buildlight.respository.jenkins.api.JenkinsRepository;
import com.comsysto.buildlight.respository.jenkins.model.JenkinsBuildResponse;

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
        JenkinsBuildResponse buildResponse = jenkinsRepository.getBuildResponse();
        return getCurrentBuildState(buildResponse);
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
