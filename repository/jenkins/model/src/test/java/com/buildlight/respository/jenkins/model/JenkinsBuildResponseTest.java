package com.buildlight.respository.jenkins.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author zutherb
 */
public class JenkinsBuildResponseTest {

    private ClassPathResource BUILD_REPONSE = new ClassPathResource("/com/buildlight/respository/jenkins/model/jenkinsResponse.json");
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testJsonMapping() throws Exception {
        JenkinsBuildResponse response = objectMapper.readValue(BUILD_REPONSE.getFile(), JenkinsBuildResponse.class);

        assertNotNull(response);
        assertEquals(Color.aborted, response.getColor());
    }
}
