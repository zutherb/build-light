package com.cleware.commandline.command;

import com.cleware.commandline.ArgumentBuffer;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author zutherb
 */
public class HelpCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCommand.class);


    public static final String help = "--help";

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return help.equalsIgnoreCase(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        try {
            URL resource = getClass().getResource("help.txt");
            File file = new File(resource.getFile());
            LOGGER.info(FileUtils.readFileToString(file));
        } catch (Exception e) {
            LOGGER.error("Could not open help file");
        }
    }
}
