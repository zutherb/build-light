package com.cleware.commandline;

import com.cleware.commandline.command.*;
import com.cleware.driver.TrafficLight;

import java.util.Arrays;
import java.util.List;


/**
 * @author zutherb
 */
public final class CommandLineRunner {

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
        } catch (Exception e) { /* NOOP */
        } finally {
            TRAFFIC_LIGHT.close();
            System.exit(0);
        }
    }

}
