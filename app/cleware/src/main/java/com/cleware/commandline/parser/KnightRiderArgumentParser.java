package com.cleware.commandline.parser;

import com.comsysto.buildlight.cleware.driver.Led;
import com.comsysto.buildlight.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author zutherb
 */
@Component
public class KnightRiderArgumentParser extends AbstractArgumentParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(KnightRiderArgumentParser.class);
    private static final String KNIGHTRIDER = "--knightrider";

    private long executionTime = 0;
    private long sleepTime = 1000;

    @Autowired
    public KnightRiderArgumentParser(TrafficLight trafficLight) {
        super(trafficLight);
    }

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return KNIGHTRIDER.equalsIgnoreCase(buffer.peek());
    }

    @Async
    @Override
    public void execute(ArgumentBuffer buffer) {
        LOGGER.info("Knight Rider sequence can be exit by pressing Ctrl+C");
        try {
            Led[] leds = Led.values();
            int moveCounter = 0;
            boolean moveForward = true;
            StopWatch stopWatch = new StopWatch();
            while (executionTimeReached(stopWatch)) {
                stopWatch.start();
                trafficLight().switchOffAllLeds();
                if (moveForward) {
                    trafficLight().switchOn(leds[moveCounter++]);
                } else {
                    trafficLight().switchOn(leds[--moveCounter]);
                }
                if (moveCounter == 0) {
                    moveCounter++;
                    moveForward = true;
                }
                if (moveCounter == leds.length) {
                    moveForward = false;
                    --moveCounter;
                }
                Thread.sleep(sleepTime);
                stopWatch.stop();
            }
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }

    private boolean executionTimeReached(StopWatch stopWatch) {
        return executionTime == 0 || stopWatch.getTotalTimeMillis() < executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
}
