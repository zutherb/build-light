package com.buildlight.application.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang.NotImplementedException;

/**
 * @author zutherb
 */
public class ConfigurationFile {
    private BuildServerType serverType;
    private StringProperty jenkinsUrl = new SimpleStringProperty();
    private StringProperty jenkinsBuildName = new SimpleStringProperty();

    public BuildServerType getServerType() {
        return serverType;
    }

    public StringProperty getJenkinsUrl() {
        return jenkinsUrl;
    }

    public StringProperty getJenkinsBuildName() {
        return jenkinsBuildName;
    }

    public void setServerType(BuildServerType serverType) {
        this.serverType = serverType;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        switch (serverType) {
            case Jenkins:
                buffer.append("build.server=" + serverType.name() + "\n");
                buffer.append("jenkins.serverUrl=" + jenkinsUrl.getValue() + "\n");
                buffer.append("jenkins.build.name=" + jenkinsBuildName.getValue() + "\n");
                break;
            case Bamboo:
            default:
                throw new NotImplementedException("Known build server type");
        }
        return buffer.toString();
    }
}
