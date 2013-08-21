package com.comsysto.buildlight.respository.bamboo.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;


/**
 * @author zutherb
 */
public class BambooResultResponseTest {

    private ClassPathResource RESULTS_REPONSE = new ClassPathResource("/com/buildlight/respository/bamboo/model/bambooResultsResponse.json");
    private ClassPathResource RESULTS_51_REPONSE = new ClassPathResource("/com/buildlight/respository/bamboo/model/bambooResults51Response.json");
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testJsonMapping() throws Exception {
        BambooResultResponse response = objectMapper.readValue(RESULTS_REPONSE.getFile(), BambooResultResponse.class);

        assertNotNull(response);
        assertNotNull(response.getResults());

        assertEquals(0, response.getResults().getStartIndex());
        assertEquals(17, response.getResults().getMaxResult());
        assertEquals(17, response.getResults().getSize());

        assertFalse(response.getResults().getResults().isEmpty());
        assertEquals(State.Successful, response.getResults().getResults().get(0).getState());
        assertEquals(LifeCycleState.Finished, response.getResults().getResults().get(0).getLifeCycleState());
    }

    @Test
    public void testJsonMappingV51() throws Exception {
        BambooResultResponse response = objectMapper.readValue(RESULTS_51_REPONSE.getFile(), BambooResultResponse.class);

        assertNotNull(response);
        assertNotNull(response.getResults());

        assertEquals(0, response.getResults().getStartIndex());
        assertEquals(25, response.getResults().getMaxResult());
        assertEquals(1, response.getResults().getSize());

        assertFalse(response.getResults().getResults().isEmpty());
        assertEquals(State.Successful, response.getResults().getResults().get(0).getState());
        assertEquals(LifeCycleState.Finished, response.getResults().getResults().get(0).getLifeCycleState());
    }
}
