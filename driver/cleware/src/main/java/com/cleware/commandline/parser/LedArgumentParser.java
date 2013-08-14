package com.cleware.commandline.parser;

import com.cleware.driver.Led;
import com.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component
public class LedArgumentParser extends AbstractArgumentParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(LedArgumentParser.class);

    @Autowired
    public LedArgumentParser(TrafficLight trafficLight) {
        super(trafficLight);
    }

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return Led.valueOfIgnoreCaseOrNull(buffer.peek()) != null;
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        Led led = Led.valueOfIgnoreCaseOrNull(buffer.peek());
        String name = buffer.next();
        Mode mode = Mode.valueOfIgnoreCaseOrError(name);
        switch (mode) {
            case ON:
                trafficLight().switchOn(led);
                break;
            case OFF:
                trafficLight().switchOff(led);
                break;
            default:
                LOGGER.error("Postion {}: Led {} must be following <on | off>", buffer.position(), led);
                break;
        }
    }

    private static enum Mode {
        ON,
        OFF,
        ERROR;

        public static Mode valueOfIgnoreCaseOrError(String name) {
            for (Mode mode : Mode.values()) {
                if (mode.name().equalsIgnoreCase(name)) {
                    return mode;
                }
            }
            return ERROR;
        }
    }
}
