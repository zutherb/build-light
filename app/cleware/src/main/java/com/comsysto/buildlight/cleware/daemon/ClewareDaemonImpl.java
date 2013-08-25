package com.comsysto.buildlight.cleware.daemon;

import com.comsysto.buildlight.cleware.commandline.parser.ArgumentBuffer;
import com.comsysto.buildlight.cleware.commandline.parser.CommandLineParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component("clewareDaemon")
@ManagedResource(objectName = "com.comsysto.buildlight.cleware.daemon@:name=ClewareDaemon")
public class ClewareDaemonImpl implements ClewareDaemon {

    private static boolean run = true;

    private CommandLineParser commandLineParser;

    @Autowired
    public ClewareDaemonImpl(CommandLineParser commandLineParser) {
        this.commandLineParser = commandLineParser;
    }

    @Override
    @ManagedOperation
    public String execute(String[] args) {
        return commandLineParser.execute(new ArgumentBuffer(args));
    }

    @Override
    @ManagedOperation
    public void shutdown() {
        run = false;
    }

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/comsysto/buildlight/cleware/spring-daemon-context.xml");
        while (run) {
            Thread.sleep(1000);
        }
        applicationContext.destroy();
    }
}
