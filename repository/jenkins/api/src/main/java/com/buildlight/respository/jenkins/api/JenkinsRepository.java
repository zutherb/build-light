package com.buildlight.respository.jenkins.api;

import com.buildlight.respository.jenkins.model.JenkinsBuildResponse;

/**
 * @author zutherb
 */
public interface JenkinsRepository {
    JenkinsBuildResponse getBuildResponse();
}
