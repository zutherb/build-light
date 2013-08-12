package com.cleware.commandline.command;

import com.cleware.commandline.ArgumentBuffer;

/**
 * @author zutherb
 */
public interface Command {
    boolean isResponsible(ArgumentBuffer buffer);

    void execute(ArgumentBuffer buffer);
}
