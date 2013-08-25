package com.comsysto.buildlight.cleware.commandline.parser;

import com.comsysto.buildlight.cleware.driver.Led;
import com.comsysto.buildlight.cleware.driver.TrafficLight;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author zutherb
 */
public class LedArgumentParserTest {

    @Mock
    private TrafficLight light;

    @InjectMocks
    private ArgumentParser parser = new LedArgumentParser(light);

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void isResponsibleTrue() {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"red", "on"});
        buffer.next();
        assertTrue(parser.isResponsible(buffer));
    }

    @Test
    public void isResponsibleFalse() {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"magenta", "on"});
        buffer.next();
        assertFalse(parser.isResponsible(buffer));
    }

    @Test
    public void testLedOn() throws Exception {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"red", "on"});
        buffer.next();
        parser.execute(buffer);
        verify(light, only()).switchOn(Led.RED);
    }

    @Test
    public void testLedOff() throws Exception {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"red", "off"});
        buffer.next();
        parser.execute(buffer);
        verify(light, only()).switchOff(Led.RED);
    }

    @Test(expected = ParserException.class)
    public void testLedError() throws Exception {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"red", "thisisanerror"});
        buffer.next();
        parser.execute(buffer);
    }
}
