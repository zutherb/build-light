package com.buildlight.respository.bamboo.model;

/**
 * @author zutherb
 */
public class BambooBuildResponse {
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

        private BambooBuildResponse response = new BambooBuildResponse();

        public Builder results(Results results) {
            response.results = results;
            return this;
        }

        public BambooBuildResponse build() {
            return response;
        }
    }
}
