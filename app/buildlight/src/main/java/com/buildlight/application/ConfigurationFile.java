package com.buildlight.application;

import com.buildlight.application.gui.BuildServerType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang.NotImplementedException;

import java.io.File;

/**
 * @author zutherb
 */
public class ConfigurationFile {
    public static File CONFIGURATION_FILE = new File("buildlight.properties");

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
                buffer.append("jenkins.server.url=" + jenkinsUrl.getValue() + "\n");
                buffer.append("jenkins.build.name=" + jenkinsBuildName.getValue() + "\n");
                break;
            case Bamboo:
            default:
                throw new NotImplementedException("Known build server type");
        }
        return buffer.toString();
    }
}
