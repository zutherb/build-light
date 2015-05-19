package com.github.zutherb.buildlight.application.adapter;

/**
 * @author zutherb
 */
public interface BuildServerAdapter {
    java.util.List<BuildState> getCurrentBuildState();
}
