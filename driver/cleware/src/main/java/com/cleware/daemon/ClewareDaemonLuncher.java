package com.cleware.daemon;

/**
 * @author zutherb
 */
public class ClewareDaemonLuncher {
    public static void lunchClewareDaemon() throws Exception {
        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String path = System.getProperty("java.home")
                + separator + "bin" + separator + "java";
        ProcessBuilder processBuilder =
                new ProcessBuilder(path, "-cp",
                        classpath,
                        ClewareDaemonImpl.class.getName());
        Process process = processBuilder.start();
    }
}
