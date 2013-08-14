package com.cleware.commandline.parser;

import com.cleware.driver.TrafficLight;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component
public final class HelpArgumentParser extends AbstractArgumentParser {

    private static final ClassPathResource HELP_FILE = new ClassPathResource("/com/cleware/commandline/parser/help.txt");
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpArgumentParser.class);
    private static final String help = "--help";

    @Autowired
    public HelpArgumentParser(TrafficLight light) {
        super(light);
    }

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return help.equalsIgnoreCase(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        try {
            LOGGER.info(FileUtils.readFileToString(HELP_FILE.getFile()));
        } catch (Exception e) {
            LOGGER.error("Could not open help file", e);
        }
    }
}
