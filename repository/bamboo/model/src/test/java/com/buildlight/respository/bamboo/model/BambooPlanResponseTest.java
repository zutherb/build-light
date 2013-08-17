package com.buildlight.respository.bamboo.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


/**
 * @author zutherb
 */
public class BambooPlanResponseTest {

    private ClassPathResource PLAN_REPONSE = new ClassPathResource("/com/buildlight/respository/bamboo/model/bambooPlanResponse.json");
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testJsonMapping() throws Exception {
        BambooPlanResponse response = objectMapper.readValue(PLAN_REPONSE.getFile(), BambooPlanResponse.class);

        assertNotNull(response);
        assertFalse(response.isBuilding());
    }
}
