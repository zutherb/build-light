package com.buildlight.respository.jenkins.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author zutherb
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JenkinsBuildResponse {
    private String description;
    private String displayName;
    private String displayNameOrNull;
    private String name;
    private String url;
    private boolean buildable;
    private List<Build> builds;
    private Color color;
    private Build firstBuild;
    @JsonProperty("healthReport")
    private List<HealthReport> healthReports;
    private boolean inQueue;
    private boolean keepDependencies;
    private Build lastBuild;
    private Build lastCompletedBuild;
    private Build lastFailedBuild;
    private Build lastStableBuild;
    private Build lastSuccessfulBuild;
    private Build lastUnstableBuild;
    private Build lastUnsuccessfulBuild;
    private int nextBuildNumber;
    private boolean concurrentBuild;

    public String getDescription() {
        return description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayNameOrNull() {
        return displayNameOrNull;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public boolean isBuildable() {
        return buildable;
    }

    public List<Build> getBuilds() {
        return builds;
    }

    public Color getColor() {
        return color;
    }

    public Build getFirstBuild() {
        return firstBuild;
    }

    public List<HealthReport> getHealthReports() {
        return healthReports;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public boolean isKeepDependencies() {
        return keepDependencies;
    }

    public Build getLastBuild() {
        return lastBuild;
    }

    public Build getLastCompletedBuild() {
        return lastCompletedBuild;
    }

    public Build getLastFailedBuild() {
        return lastFailedBuild;
    }

    public Build getLastStableBuild() {
        return lastStableBuild;
    }

    public Build getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    public Build getLastUnstableBuild() {
        return lastUnstableBuild;
    }

    public Build getLastUnsuccessfulBuild() {
        return lastUnsuccessfulBuild;
    }

    public int getNextBuildNumber() {
        return nextBuildNumber;
    }

    public boolean isConcurrentBuild() {
        return concurrentBuild;
    }

    public static class Builder {
        private JenkinsBuildResponse response = new JenkinsBuildResponse();

        public Builder color(Color color) {
            response.color = color;
            return this;
        }

        public JenkinsBuildResponse build() {
            return response;
        }
    }
}
