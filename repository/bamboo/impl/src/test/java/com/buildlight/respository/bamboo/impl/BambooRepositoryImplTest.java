package com.comsysto.buildlight.respository.bamboo.impl;

import com.comsysto.buildlight.respository.bamboo.api.BambooRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static junit.framework.Assert.assertNotNull;


/**
 * @author zutherb
 */
@ActiveProfiles(profiles = {"bamboo", "test"})
@ContextConfiguration("classpath:/com/buildlight/respository/bamboo/spring-context.xml")
public class BambooRepositoryImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private BambooRepository bambooRepository;

    @Test
    public void testContextGeneration() {
        assertNotNull(bambooRepository);
    }
}
