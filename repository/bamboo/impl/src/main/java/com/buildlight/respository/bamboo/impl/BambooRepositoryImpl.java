package com.buildlight.respository.bamboo.impl;

import com.buildlight.respository.bamboo.api.BambooRepository;
import com.buildlight.respository.bamboo.model.BambooBuildResponse;
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
    private final String BAMBOO_URL = "{serverUrl}/rest/api/latest/result/{buildKey}.json?os_authType=basic&os_username={username}&os_password={password}";

    @Value("${bamboo.serverUrl}")
    private String serverUrl;
    @Value("${bamboo.buildKey}")
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
    public BambooBuildResponse getBambooBuildResponse() {
        UriTemplate buildServerUriTemplate = new UriTemplate(BAMBOO_URL);
        URI buildServerUri = buildServerUriTemplate.expand(serverUrl, buildKey, username, password);
        return restTemplate.getForObject(buildServerUri, BambooBuildResponse.class);
    }
}
