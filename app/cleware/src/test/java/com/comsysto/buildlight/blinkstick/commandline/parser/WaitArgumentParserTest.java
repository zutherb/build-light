package com.comsysto.buildlight.blinkstick.commandline.parser;

import com.comsysto.buildlight.common.driver.TrafficLight;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.util.StopWatch;

import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author zutherb
 */
public class WaitArgumentParserTest {

    private ArgumentBuffer buffer;
    private StringBuffer outputBuffer;

    @Mock
    private TrafficLight trafficLight;

    @InjectMocks
    private ArgumentParser parser = new WaitArgumentParser(trafficLight);

    @Before
    public void setup() {
        initMocks(this);
        buffer = new ArgumentBuffer(new String[]{"wait", "500"});
        buffer.next();
        outputBuffer = new StringBuffer();
    }

    @Test
    public void testIsResponsible() throws Exception {
        assertTrue(parser.isResponsible(buffer));
    }

    @Test
    public void testExecute() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        parser.execute(buffer, outputBuffer);
        stopWatch.stop();
        assertTrue("Wait seems to be not executed", stopWatch.getLastTaskTimeMillis() > 470);
    }

    @Test
    public void failedNumberParsing() {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"wait", "fail"});
        parser.execute(buffer, outputBuffer);
    }

    @Test
    public void failedArrayBufferLength() {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"wait"});
        parser.execute(buffer, outputBuffer);
    }
}
