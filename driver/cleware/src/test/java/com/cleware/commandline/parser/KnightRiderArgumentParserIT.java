package com.cleware.commandline.parser;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author zutherb
 */
@ActiveProfiles("test")
@ContextConfiguration(locations = "classpath:/com/cleware/spring-context.xml")
public class KnightRiderArgumentParserIT extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("knightRiderArgumentParser")
    private ArgumentParser parser;

    @Test
    public void testExecute() throws Exception {
        parser.execute(new ArgumentBuffer(new String[]{"--knightrider"}));
    }
}
