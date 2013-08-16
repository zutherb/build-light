package com.buildlight.respository.bamboo.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;


/**
 * @author zutherb
 */
public class BambooBuildResponseTest {

    private ClassPathResource BUILD_REPONSE = new ClassPathResource("/com/buildlight/respository/bamboo/model/bamboo.json");
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testJsonMapping() throws Exception {
        BambooBuildResponse response = objectMapper.readValue(BUILD_REPONSE.getFile(), BambooBuildResponse.class);

        assertNotNull(response);
        assertNotNull(response.getResults());

        assertEquals(0, response.getResults().getStartIndex());
        assertEquals(17, response.getResults().getMaxResult());
        assertEquals(17, response.getResults().getSize());

        assertFalse(response.getResults().getResults().isEmpty());
        assertEquals(State.Successful, response.getResults().getResults().get(0).getState());
        assertEquals(LifeCycleState.Finished, response.getResults().getResults().get(0).getLifeCycleState());
    }
}
