package com.comsysto.buildlight.respository.jenkins.api;

import com.comsysto.buildlight.respository.jenkins.model.JenkinsBuildResponse;

/**
 * @author zutherb
 */
public interface JenkinsRepository {
    JenkinsBuildResponse getBuildResponse();
}
