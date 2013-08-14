package com.cleware.commandline.command;

import com.cleware.driver.Led;
import com.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public class LedArgumentParser extends AbstractArgumentParser {
    private static final TrafficLight TRAFFIC_LIGHT = TrafficLight.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(LedArgumentParser.class);

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
                TRAFFIC_LIGHT.switchOn(led);
                break;
            case OFF:
                TRAFFIC_LIGHT.switchOff(led);
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
