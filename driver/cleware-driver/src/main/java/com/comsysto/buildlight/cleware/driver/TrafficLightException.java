package com.comsysto.buildlight.cleware.driver;

/**
 * @author zutherb
 */
public class TrafficLightException extends RuntimeException {
    public TrafficLightException() {
    }

    public TrafficLightException(String message) {
        super(message);
    }

    public TrafficLightException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrafficLightException(Throwable cause) {
        super(cause);
    }
}
