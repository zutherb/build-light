package com.comsysto.buildlight.blinkstick.daemon;

/**
 * @author zutherb
 */
public interface ClewareDaemon {
    String execute(String[] args);

    void shutdown();
}
