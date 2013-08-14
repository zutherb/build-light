package com.cleware.commandline.command;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

/**
 * @author zutherb
 */
public final class HelpArgumentParser extends AbstractArgumentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpArgumentParser.class);
    private static final String help = "--help";

    private static HelpArgumentParser INSTANCE;

    private HelpArgumentParser() { /* NOOP  */ }

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
            LOGGER.error("Could not open help file", e);
        }
    }

    public synchronized static ArgumentParser instance() {
        if (INSTANCE == null) {
            INSTANCE = new HelpArgumentParser();
        }
        return INSTANCE;
    }
}
