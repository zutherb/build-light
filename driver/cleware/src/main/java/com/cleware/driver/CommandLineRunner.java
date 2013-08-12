package com.cleware.driver;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zutherb
 */
public final class CommandLineRunner {

    private CommandLineRunner () { /*NOOP*/ }

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineRunner.class);

    private static final TrafficLight TRAFFIC_LIGHT = TrafficLightFactory.createNewInstance();

    private static final String ON = "on";
    private static final String OFF = "off";

    public static void main(String[] args) {
        try {
            Validate.isTrue(args.length > 0, "Program arguments must not be null");
            Validate.isTrue(args.length % 2 == 0, "Program arguments must have the structure <red|yellow|green> <on|off>");
            for (int counter = 0; counter < args.length - 1; counter++) {
                if (ON.equalsIgnoreCase(args[counter + 1])) {
                    TRAFFIC_LIGHT.switchOn(Led.valueOfIgnoreCase(args[counter]));
                }
                if (OFF.equalsIgnoreCase(args[counter + 1])) {
                    TRAFFIC_LIGHT.switchOn(Led.valueOfIgnoreCase(args[counter]));
                }
            }
        } catch (Exception e) {
            TRAFFIC_LIGHT.close();
            LOGGER.error(e.getMessage());
            LOGGER.debug("Command line runner could not execute request", e);
        } finally {
            TRAFFIC_LIGHT.close();
        }
    }

}
