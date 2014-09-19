package com.github.zutherb.buildlight.respository.bamboo.impl;

import com.github.zutherb.buildlight.respository.bamboo.api.BambooRepository;
import com.github.zutherb.buildlight.respository.bamboo.model.BambooPlanResponse;
import com.github.zutherb.buildlight.respository.bamboo.model.BambooResultResponse;
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
public class BambooRepositoryImpl implements BambooRepository {
    private final String BAMBOO_RESULT_URL = "{serverUrl}/rest/api/latest/result/{buildKey}.json?os_authType=basic&os_username={username}&os_password={password}";
    private final String BAMBOO_PLAN_URL = "{serverUrl}/rest/api/latest/plan/{buildKey}.json?os_authType=basic&os_username={username}&os_password={password}";

    @Value("${bamboo.server.url}")
    private String serverUrl;
    @Value("${bamboo.build.key}")
    private String buildKey;
    @Value("${bamboo.username}")
    private String username;
    @Value("${bamboo.password}")
    private String password;

    private RestTemplate restTemplate;

    @Autowired
    public BambooRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BambooPlanResponse getPlanResponse() {
        UriTemplate buildServerUriTemplate = new UriTemplate(BAMBOO_PLAN_URL);
        URI buildServerUri = buildServerUriTemplate.expand(serverUrl, buildKey, username, password);
        return restTemplate.getForObject(buildServerUri, BambooPlanResponse.class);
    }

    @Override
    public BambooResultResponse getResultResponse() {
        UriTemplate buildServerUriTemplate = new UriTemplate(BAMBOO_RESULT_URL);
        URI buildServerUri = buildServerUriTemplate.expand(serverUrl, buildKey, username, password);
        return restTemplate.getForObject(buildServerUri, BambooResultResponse.class);
    }
}
