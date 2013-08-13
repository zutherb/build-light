package com.cleware.commandline.command;

import com.cleware.commandline.ArgumentBuffer;
import com.cleware.gui.TrafficLightApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public class GuiCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(LedCommand.class);
    private static final String GUI = "--gui";

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return buffer.isEmpty() || GUI.equals(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        try {
            String separator = System.getProperty("file.separator");
            String classpath = System.getProperty("java.class.path");
            String path = System.getProperty("java.home")
                    + separator + "bin" + separator + "java";
            ProcessBuilder processBuilder =
                    new ProcessBuilder(path, "-cp",
                            classpath,
                            TrafficLightApplication.class.getCanonicalName());
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();
            LOGGER.info("GUI started");
        } catch (Exception e) {
            LOGGER.error("Could not start GUI");
        }
    }
}
