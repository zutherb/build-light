package com.comsysto.buildlight.respository.jenkins.api;

import com.comsysto.buildlight.respository.common.BuildServerRepository;
import com.comsysto.buildlight.respository.jenkins.model.JenkinsBuildResponse;

/**
 * @author zutherb
 */
public interface JenkinsRepository extends BuildServerRepository {
    java.util.List<JenkinsBuildResponse> getBuildResponse();
}
