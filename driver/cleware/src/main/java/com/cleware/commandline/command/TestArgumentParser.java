package com.cleware.commandline.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestArgumentParser extends AbstractArgumentParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestArgumentParser.class);

    private static final String TEST = "--test";

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return TEST.equalsIgnoreCase(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        try {
            trafficLight().switchOnAllLeds();
            Thread.sleep(1000);
            trafficLight().switchOffAllLeds();
        } catch (Exception e) {
            LOGGER.error("'" + TEST + "' argument could not be parsed correct or executed", e);
        }
    }

}
