package com.github.zutherb.buildlight.respository.jenkins.api;

import com.github.zutherb.buildlight.respository.common.BuildServerRepository;
import com.github.zutherb.buildlight.respository.jenkins.model.JenkinsBuildResponse;

/**
 * @author zutherb
 */
public interface JenkinsRepository extends BuildServerRepository {
    java.util.List<JenkinsBuildResponse> getBuildResponse();
}
