package com.comsysto.buildlight.blinkstick.daemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zutherb
 */
public class ClewareDaemonLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClewareDaemonLauncher.class);

    public static void main(String[] args) throws Exception {
        lunch();
        LOGGER.info("Cleware Daemon is lunched");
    }

    public static void lunch() throws Exception {
        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String path = System.getProperty("java.home")
                + separator + "bin" + separator + "java";
        ProcessBuilder processBuilder =
                new ProcessBuilder(path, "-cp",
                        classpath,
                        "-Dcom.sun.management.jmxremote.authenticate=false",
                        "-Dcom.sun.management.jmxremote.ssl=false",
                        "-Dcom.sun.management.jmxremote.port=39600",
                        ClewareDaemonImpl.class.getName());
        Process process = processBuilder.start();
    }
}
