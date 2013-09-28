package com.comsysto.buildlight.application.adapter;

import com.comsysto.buildlight.respository.jenkins.api.JenkinsRepository;
import com.comsysto.buildlight.respository.jenkins.model.JenkinsBuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component
public class JenkinsAdapter implements BuildServerAdapter {

    @Autowired(required = false)
    private JenkinsRepository jenkinsRepository;

    @Override
    public boolean isResponsible() {
        return jenkinsRepository != null;
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
