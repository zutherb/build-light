package com.github.zutherb.buildlight.respository.bamboo.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author zutherb
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metrics {
    @JsonProperty("start-index")
    private int startIndex;
    @JsonProperty("max-result")
    private int maxResult;
    private int size;

    public int getStartIndex() {
        return startIndex;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public int getSize() {
        return size;
    }
}
