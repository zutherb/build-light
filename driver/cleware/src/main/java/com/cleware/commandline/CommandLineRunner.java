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
    private static final HelpCommand HELP_COMMAND = new HelpCommand();
    private static final List<Command> COMMAND_CHAIN = Arrays.asList(new GuiCommand(), new LedCommand(),
            new WaitCommand(), new KnightRiderCommand(), HELP_COMMAND, new TestCommand());

    public static void main(String[] args) {
        try {
            ArgumentBuffer buffer = new ArgumentBuffer(args);
            printHelpIfBufferIsEmpty(buffer);
            while (!buffer.isFinished()) {
                buffer.next();
                executeCommandsForBuffer(buffer);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
        } finally {
            TRAFFIC_LIGHT.close();
            System.exit(0);
        }
    }

    private static void executeCommandsForBuffer(ArgumentBuffer buffer) {
        for (Command command : COMMAND_CHAIN) {
            if (command.isResponsible(buffer)) {
                command.execute(buffer);
            }
        }
    }

    private static void printHelpIfBufferIsEmpty(ArgumentBuffer buffer) {
        if (buffer.isFinished()) {
            HELP_COMMAND.execute(buffer);
        }
    }

}
