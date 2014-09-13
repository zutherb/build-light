package com.comsysto.buildlight.respository.jenkins.impl;

import com.comsysto.buildlight.respository.jenkins.api.JenkinsRepository;
import com.comsysto.buildlight.respository.jenkins.model.JenkinsBuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zutherb
 */
@Component
public class JenkinsRepositoryImpl implements JenkinsRepository {
    private final String JENKINS_URL = "{serverUrl}/job/{name}/api/json";

    private String serverUrl;
    private List<String> names;

    private RestTemplate restTemplate;

    @Autowired
    public JenkinsRepositoryImpl(RestTemplate restTemplate,
                                 @Value("${jenkins.server.url}") String serverUrl,
                                 @Value("${jenkins.build.names}") String name) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
        this.names = Collections.unmodifiableList(Arrays.asList(name.split(",")));
    }

    @Override
    public List<JenkinsBuildResponse> getBuildResponse() {
        List<JenkinsBuildResponse> results = new ArrayList<>();
        for(String name : names){
            UriTemplate buildServerUriTemplate = new UriTemplate(JENKINS_URL);
            URI buildServerUri = buildServerUriTemplate.expand(serverUrl, name);
            results.add(restTemplate.getForObject(buildServerUri, JenkinsBuildResponse.class));
        }
        return results;
    }
}
