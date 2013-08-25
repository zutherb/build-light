package com.comsysto.buildlight.cleware.daemon;

/**
 * @author zutherb
 */
public interface ClewareDaemon {
    String execute(String[] args);

    void shutdown();
}
