package com.cleware.commandline.command;

import com.cleware.commandline.ArgumentBuffer;
import com.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCommand.class);

    private static final String TEST = "--test";
    private static final TrafficLight TRAFFIC_LIGHT = TrafficLight.INSTANCE;

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return TEST.equalsIgnoreCase(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        try {
            TRAFFIC_LIGHT.switchOnAllLeds();
            Thread.sleep(1000);
            TRAFFIC_LIGHT.switchOffAllLeds();
        } catch (Exception e) {
            LOGGER.error("error during execution of wait command", e);
        }

    }
}
