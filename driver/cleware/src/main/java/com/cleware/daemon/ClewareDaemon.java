package com.cleware.daemon;

/**
 * @author zutherb
 */
public interface ClewareDaemon {
    void parseCommandLineArgs(String[] args);

    void shutdown();
}
