package com.github.zutherb.buildlight.respository.bamboo.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author zutherb
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BambooResultResponse {
    private Results results;
    private String expand;
    private Link link;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public static class Builder {

        private BambooResultResponse response = new BambooResultResponse();

        public Builder results(Results results) {
            response.results = results;
            return this;
        }

        public BambooResultResponse build() {
            return response;
        }
    }
}
