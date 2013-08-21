package com.comsysto.buildlight.respository.bamboo.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author zutherb
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {
    private String href;
    private String rel;

    public String getHref() {
        return href;
    }

    public String getRel() {
        return rel;
    }
}
