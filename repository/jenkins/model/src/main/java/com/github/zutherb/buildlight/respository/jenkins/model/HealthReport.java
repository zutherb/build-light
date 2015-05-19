package com.github.zutherb.buildlight.respository.jenkins.model;

/**
 * @author zutherb
 */

public class HealthReport {
    private String description;
    private String iconClassName;
    private String iconUrl;
    private int score;

    public String getDescription() {
        return description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public int getScore() {
        return score;
    }

    public String getIconClassName() {
        return iconClassName;
    }
}
