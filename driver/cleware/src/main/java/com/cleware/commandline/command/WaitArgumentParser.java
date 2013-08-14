package com.cleware.commandline.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public class WaitArgumentParser extends AbstractArgumentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitArgumentParser.class);
    private static final String WAIT = "wait";

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return WAIT.equalsIgnoreCase(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        try {
            Thread.sleep(Long.parseLong(buffer.next()));
        } catch (Exception e) {
            LOGGER.error("'Wait' argument could not be parsed correct or executed", e);
        }
    }
}
