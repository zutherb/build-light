package com.comsysto.buildlight.cleware.commandline.parser;

import com.comsysto.buildlight.cleware.driver.Led;
import com.comsysto.buildlight.cleware.driver.TrafficLight;
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
    public void execute(ArgumentBuffer buffer, StringBuffer outputBuffer) {
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
                String msg = String.format("Postion %d: Led %s must be following <on | off>", buffer.position(), led.name());
                throw new ParserException(msg);
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
