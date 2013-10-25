package com.comsysto.buildlight.blinkstick.commandline.parser;

import com.comsysto.buildlight.common.driver.TrafficLight;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author zutherb
 */
public class TestArgumentParserTest {

    @Mock
    private TrafficLight light;

    @InjectMocks
    private ArgumentParser parser = new TestArgumentParser(light);

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void testIsResponsibleTrue() throws Exception {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"--test"});
        buffer.next();
        assertTrue(parser.isResponsible(buffer));
    }

    @Test
    public void testIsResponsibleFalse() throws Exception {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"--gui"});
        buffer.next();
        assertFalse(parser.isResponsible(buffer));
    }

    @Test
    public void testExecute() throws Exception {
        parser.execute(new ArgumentBuffer(new String[]{"--test"}), new StringBuffer());
        verify(light, times(1)).switchOnAllLeds();
        verify(light, times(1)).switchOffAllLeds();
    }
}
