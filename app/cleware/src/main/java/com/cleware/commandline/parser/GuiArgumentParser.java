package com.cleware.commandline.parser;

import com.cleware.commandline.gui.TrafficLightApplication;
import com.comsysto.buildlight.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component
public class GuiArgumentParser extends AbstractArgumentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuiArgumentParser.class);
    private static final String GUI = "--gui";

    @Autowired
    public GuiArgumentParser(TrafficLight trafficLight) {
        super(trafficLight);
    }

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return buffer.isEmpty() || GUI.equals(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        try {
            LOGGER.info("Starting GUI ...");
            TrafficLightApplication.main(buffer.arguments());
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }
}
