package com.buildlight.respository.bamboo.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * @author zutherb
 */
public class Results {

    @JsonProperty("start-index")
    private int startIndex;
    @JsonProperty("max-result")
    private int maxResult;
    private int size;
    @JsonProperty("result")
    private List<Result> results;
    private String expand;

    public int getStartIndex() {
        return startIndex;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public int getSize() {
        return size;
    }

    public List<Result> getResults() {
        return results;
    }

    public String getExpand() {
        return expand;
    }

    public static class Builder {

        private Results results = new Results();

        public Builder addResult(Result result) {
            if (isEmpty(results.results)) {
                results.results = new ArrayList<Result>();
            }
            results.results.add(0, result);
            return this;
        }

        public Results build() {
            return results;
        }

        public Builder results(List<Result> newResults) {
            results.results = newResults;
            return this;
        }
    }
}
