package com.cleware.commandline.command;

import com.cleware.commandline.ArgumentBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public class WaitCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitCommand.class);
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
            LOGGER.error("Error during execution of wait command", e);
        }
    }
}
