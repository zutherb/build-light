package com.cleware.commandline;

import com.cleware.commandline.command.ArgumentBuffer;
import com.cleware.commandline.command.CommandLineParser;
import com.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zutherb
 */
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private Runner() { /*NOOP*/ }

    private static final TrafficLight TRAFFIC_LIGHT = TrafficLight.INSTANCE;

    public static void main(String[] args) {
        try {
            ArgumentBuffer buffer = new ArgumentBuffer(args);
            new CommandLineParser(buffer).execute();
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
        } finally {
            TRAFFIC_LIGHT.close();
            System.exit(0);
        }
    }
}
