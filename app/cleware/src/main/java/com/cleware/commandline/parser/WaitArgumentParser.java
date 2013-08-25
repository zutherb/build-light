package com.cleware.commandline.parser;

import com.comsysto.buildlight.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component
public class WaitArgumentParser extends AbstractArgumentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitArgumentParser.class);
    private static final String WAIT = "wait";

    @Autowired
    public WaitArgumentParser(TrafficLight trafficLight) {
        super(trafficLight);
    }

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return WAIT.equalsIgnoreCase(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        try {
            Thread.sleep(Long.parseLong(buffer.next()));
        } catch (Exception e) {
            LOGGER.error("'Wait' argument could not be parsed correct or executed", e);
        }
    }
}
