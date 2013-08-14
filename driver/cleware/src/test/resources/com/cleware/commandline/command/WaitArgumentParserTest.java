package com.cleware.commandline.command;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import static org.junit.Assert.assertTrue;

/**
 * @author zutherb
 */
public class WaitArgumentParserTest {

    private ArgumentBuffer buffer;
    private WaitArgumentParser parser;

    @Before
    public void setup() {
        buffer = new ArgumentBuffer(new String[]{"wait", "500"});
        parser = new WaitArgumentParser();
        buffer.next();
    }

    @Test
    public void testIsResponsible() throws Exception {
        assertTrue(parser.isResponsible(buffer));
    }

    @Test
    public void testExecute() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        parser.execute(buffer);
        stopWatch.stop();
        assertTrue("Wait seems to be not executed", stopWatch.getLastTaskTimeMillis() > 470);
    }

    @Test
    public void failedNumberParsing() {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"wait", "fail"});
        ArgumentParser parser = new WaitArgumentParser();
        buffer.next();
        parser.execute(buffer);
    }

    @Test
    public void failedArrayBufferLength() {
        ArgumentBuffer buffer = new ArgumentBuffer(new String[]{"wait"});
        ArgumentParser parser = new WaitArgumentParser();
        buffer.next();
        parser.execute(buffer);
    }
}
