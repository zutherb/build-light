package com.cleware.commandline;

import com.cleware.commandline.command.*;
import com.cleware.driver.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


/**
 * @author zutherb
 */
public final class CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineRunner.class);

    private CommandLineRunner() { /*NOOP*/ }

    private static final TrafficLight TRAFFIC_LIGHT = TrafficLight.INSTANCE;
    private static final List<Command> COMMAND_CHAIN = Arrays.asList(new GuiCommand(), new LedCommand(), new WaitCommand(), new KnightRiderCommand());

    public static void main(String[] args) {
        try {
            ArgumentBuffer buffer = new ArgumentBuffer(args);
            while (!buffer.isFinished()) {
                for (Command command : COMMAND_CHAIN) {
                    if (command.isResponsible(buffer)) {
                        command.execute(buffer);
                    }
                }
                buffer.next();
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
        } finally {
            TRAFFIC_LIGHT.close();
            System.exit(0);
        }
    }

}
