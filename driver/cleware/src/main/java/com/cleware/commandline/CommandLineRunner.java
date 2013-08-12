package com.cleware.commandline;

import com.cleware.commandline.command.Command;
import com.cleware.commandline.command.GuiCommand;
import com.cleware.commandline.command.LedCommand;
import com.cleware.commandline.command.WaitCommand;
import com.cleware.driver.TrafficLight;
import com.cleware.driver.TrafficLightFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


/**
 * @author zutherb
 */
public final class CommandLineRunner {

    private CommandLineRunner() { /*NOOP*/ }

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineRunner.class);
    private static final TrafficLight TRAFFIC_LIGHT = TrafficLightFactory.instance();
    private static final List<Command> COMMAND_CHAIN = Arrays.asList(new GuiCommand(), new LedCommand(), new WaitCommand());

    private static final String ON = "on";
    private static final String OFF = "off";

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
        } catch (Exception e) { /* NOOP */
        } finally {
            TRAFFIC_LIGHT.close();
            System.exit(0);
        }
    }

}
