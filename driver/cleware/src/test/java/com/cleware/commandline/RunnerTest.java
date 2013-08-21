package com.cleware.commandline;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertNotNull;

/**
 * @author zutherb
 */
@ActiveProfiles("test")
@ContextConfiguration(locations = "classpath:/com/cleware/spring-daemon-context.xml")
public class RunnerTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void testApplicationContext() {
        assertNotNull(applicationContext);
    }
}
