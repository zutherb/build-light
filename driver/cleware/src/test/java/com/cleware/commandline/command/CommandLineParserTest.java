package com.cleware.commandline.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author zutherb
 */
public class CommandLineParserTest {

    @Mock
    private ArgumentParser helpParser;

    @Mock
    private ArgumentParser otherParser;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void testEmptyArgumentBuffer() throws Exception {
        TestCommandLineParser commandLineParser = new TestCommandLineParser(new ArgumentBuffer(new String[]{}));
        commandLineParser.execute();
        verifyZeroInteractions(otherParser);
        verify(helpParser, times(1)).execute(any(ArgumentBuffer.class));
    }

    @Test
    public void testChainExecution() throws Exception {
        TestCommandLineParser commandLineParser = new TestCommandLineParser(new ArgumentBuffer(new String[]{"--argument"}));
        commandLineParser.execute();
        verifyZeroInteractions(helpParser);
        verify(otherParser, times(1)).execute(any(ArgumentBuffer.class));
    }

    private class TestCommandLineParser extends CommandLineParser {

        public TestCommandLineParser(ArgumentBuffer buffer) {
            super(buffer);
        }

        @Override
        protected ArgumentParser getInstance() {
            return helpParser;
        }

        @Override
        protected List<ArgumentParser> getArgumentParserChain() {
            when(otherParser.isResponsible(any(ArgumentBuffer.class))).thenReturn(true);
            return Arrays.asList(otherParser);
        }
    }
}
