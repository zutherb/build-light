package com.cleware.commandline.command;

import com.cleware.commandline.ArgumentBuffer;
import com.cleware.driver.Led;
import com.cleware.driver.TrafficLight;
import com.cleware.driver.TrafficLightFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public class LedCommand implements Command {
    private static final TrafficLight TRAFFIC_LIGHT = TrafficLightFactory.instance();
    private static final Logger LOGGER = LoggerFactory.getLogger(LedCommand.class);

    @Override
    public boolean isResponsible(ArgumentBuffer buffer) {
        return Led.valueOfIgnoreCaseOrNull(buffer.peek()) != null;
    }

    @Override
    public void execute(ArgumentBuffer buffer) {
        Led led = Led.valueOfIgnoreCaseOrNull(buffer.peek());
        Mode mode = Mode.valueOfIgnoreCaseOrError(buffer.next());
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
