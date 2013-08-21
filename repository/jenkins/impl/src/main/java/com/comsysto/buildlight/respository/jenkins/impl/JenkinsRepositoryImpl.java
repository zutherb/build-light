package com.comsysto.buildlight.respository.jenkins.impl;

import com.comsysto.buildlight.respository.jenkins.api.JenkinsRepository;
import com.comsysto.buildlight.respository.jenkins.model.JenkinsBuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * @author zutherb
 */
@Component
public class JenkinsRepositoryImpl implements JenkinsRepository {
    private final String JENKINS_URL = "{serverUrl}/job/{name}/api/json";

    @Value("${jenkins.server.url}")
    private String serverUrl;
    @Value("${jenkins.build.name}")
    private String name;


    private RestTemplate restTemplate;

    @Autowired
    public JenkinsRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public JenkinsBuildResponse getBuildResponse() {
        UriTemplate buildServerUriTemplate = new UriTemplate(JENKINS_URL);
        URI buildServerUri = buildServerUriTemplate.expand(serverUrl, name);
        return restTemplate.getForObject(buildServerUri, JenkinsBuildResponse.class);
    }
}
