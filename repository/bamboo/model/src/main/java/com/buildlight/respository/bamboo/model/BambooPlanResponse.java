package com.buildlight.respository.bamboo.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author zutherb
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BambooPlanResponse extends Plan {
    private String expand;
    @JsonProperty("isFavourite")
    private boolean isFavourite;
    @JsonProperty("isActive")
    private boolean isActive;
    @JsonProperty("isBuilding")
    private boolean isBuilding;
    private int averageBuildTimeInSeconds;
    private Metrics actions;
    private Metrics stages;
    private Metrics branches;
    private Metrics variableContext;

    public String getExpand() {
        return expand;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isBuilding() {
        return isBuilding;
    }

    public int getAverageBuildTimeInSeconds() {
        return averageBuildTimeInSeconds;
    }

    public Metrics getActions() {
        return actions;
    }

    public Metrics getStages() {
        return stages;
    }

    public Metrics getBranches() {
        return branches;
    }

    public Metrics getVariableContext() {
        return variableContext;
    }
}
