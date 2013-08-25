package com.cleware.daemon;

/**
 * @author zutherb
 */
public interface ClewareDaemon {
    void execute(String[] args);

    void shutdown();
}
