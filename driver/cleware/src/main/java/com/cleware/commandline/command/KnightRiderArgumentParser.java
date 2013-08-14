package com.cleware.commandline.command;

import com.cleware.driver.Led;
import com.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public class KnightRiderArgumentParser extends AbstractArgumentParser {
    private static final TrafficLight TRAFFIC_LIGHT = TrafficLight.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(KnightRiderArgumentParser.class);
    private static final String KNIGHTRIDER = "--knightrider";

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return KNIGHTRIDER.equalsIgnoreCase(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        LOGGER.info("Knight Rider sequence can be exit by pressing Ctrl+C");
        try {
            Led[] leds = Led.values();
            int moveCounter = 0;
            boolean moveForward = true;
            while (true) {
                TRAFFIC_LIGHT.switchOffAllLeds();
                if (moveForward) {
                    TRAFFIC_LIGHT.switchOn(leds[moveCounter++]);
                } else {
                    TRAFFIC_LIGHT.switchOn(leds[--moveCounter]);
                }
                if (moveCounter == 0) {
                    moveCounter++;
                    moveForward = true;
                }
                if (moveCounter == leds.length) {
                    moveForward = false;
                    --moveCounter;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            LOGGER.error("Knight Rider sequence could not be executed", e);
        }
    }
}
