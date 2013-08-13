package com.cleware.commandline.command;

import com.cleware.commandline.ArgumentBuffer;
import com.cleware.driver.Led;
import com.cleware.driver.TrafficLight;
import com.cleware.driver.TrafficLightFactory;

/**
 * @author zutherb
 */
public class KnightRiderCommand implements Command {
    private static final TrafficLight TRAFFIC_LIGHT = TrafficLightFactory.instance();

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return "--knightrider".equalsIgnoreCase(buffer.peek());
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
