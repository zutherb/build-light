package com.cleware.daemon;

import com.cleware.commandline.parser.ArgumentBuffer;
import com.cleware.commandline.parser.CommandLineParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author zutherb
 */
@Component("clewareDaemon")
@ManagedResource(objectName = "com.cleware:name=ClewareDaemon")
public class ClewareDaemonImpl implements ClewareDaemon {

    private static boolean run = true;

    private CommandLineParser commandLineParser;

    @Autowired
    public ClewareDaemonImpl(CommandLineParser commandLineParser) {
        this.commandLineParser = commandLineParser;
    }

    @Override
    @ManagedOperation
    public void parseCommandLineArgs(String[] args) {
        commandLineParser.execute(new ArgumentBuffer(args));
    }

    @Override
    @ManagedOperation
    public void shutdown() {
        run = false;
    }

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/cleware/spring-daemon-context.xml");
        while (run) {
            Thread.sleep(1000);
        }
        applicationContext.destroy();
    }
}
