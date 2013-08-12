package com.cleware.commandline.command;

import com.cleware.commandline.ArgumentBuffer;
import com.cleware.gui.TrafficLightApplication;

/**
 * @author zutherb
 */
public class GuiCommand implements Command {

    public static final String GUI = "--gui";

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return buffer.isEmpty() || GUI.equals(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        TrafficLightApplication.main(buffer.arguments());
    }
}
