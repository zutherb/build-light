package com.cleware.commandline.parser;

import com.comsysto.buildlight.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestArgumentParser extends AbstractArgumentParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestArgumentParser.class);

    private static final String TEST = "--test";

    @Autowired
    public TestArgumentParser(TrafficLight trafficLight) {
        super(trafficLight);
    }

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
