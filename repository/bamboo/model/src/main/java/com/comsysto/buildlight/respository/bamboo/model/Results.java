package com.comsysto.buildlight.respository.bamboo.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * @author zutherb
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results extends Metrics {

    @JsonProperty("result")
    private List<Result> results;
    private String expand;

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
                results.results = new ArrayList<>();
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

        public Builder expand(String expand) {
            results.expand = expand;
            return this;
        }
    }
}
