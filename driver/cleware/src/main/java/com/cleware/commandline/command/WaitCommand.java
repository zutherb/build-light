package com.cleware.commandline.command;

import com.cleware.commandline.ArgumentBuffer;

/**
 * @author zutherb
 */
public class WaitCommand implements Command {

    public static final String WAIT = "wait";

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return WAIT.equalsIgnoreCase(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        try {
            Thread.sleep(Long.parseLong(buffer.next()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
