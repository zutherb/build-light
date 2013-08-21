package com.comsysto.buildlight.application;

import com.comsysto.buildlight.application.gui.BuildServerType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang.NotImplementedException;

import java.io.File;

/**
 * @author zutherb
 */
public class ConfigurationFile {
    public static String JENKINS_DEFAULT_CONFIGURATION = new Builder()
            .serverType(BuildServerType.Jenkins)
            .jenkinsUrl("http://localhost:8080/build-server")
            .jenkinsBuildName("Build-Light-Test-Build")
            .build()
            .toString();
    public static String BAMBOO_DEFAULT_CONFIGURATION = new Builder()
            .serverType(BuildServerType.Bamboo)
            .bambooUrl("http://localhost:8080/build-server")
            .bambooBuildKey("BUILDLIGHT_JOB")
            .bambooUsername("zutherb")
            .bambooPassword("t0ps3cr3t")
            .build()
            .toString();

    public static File CONFIGURATION_FILE = new File("buildlight.properties");

    private BuildServerType serverType;

    private StringProperty jenkinsUrl = new SimpleStringProperty();
    private StringProperty jenkinsBuildName = new SimpleStringProperty();
    private StringProperty bambooUrl = new SimpleStringProperty();
    private StringProperty bambooBuildKey = new SimpleStringProperty();
    private StringProperty bambooUsername = new SimpleStringProperty();
    private StringProperty bambooPassword = new SimpleStringProperty();

    public BuildServerType getServerType() {
        return serverType;
    }

    public StringProperty getJenkinsUrl() {
        return jenkinsUrl;
    }

    public StringProperty getJenkinsBuildName() {
        return jenkinsBuildName;
    }

    public StringProperty jenkinsBuildNameProperty() {
        return jenkinsBuildName;
    }

    public StringProperty getBambooUrl() {
        return bambooUrl;
    }

    public StringProperty bambooBuildKey() {
        return bambooBuildKey;
    }

    public StringProperty bambooUsername() {
        return bambooUsername;
    }

    public StringProperty bambooPassword() {
        return bambooPassword;
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
                buffer.append("build.server=" + serverType.name() + "\n");
                buffer.append("bamboo.server.url=" + bambooUrl.getValue() + "\n");
                buffer.append("bamboo.build.key=" + bambooBuildKey.getValue() + "\n");
                buffer.append("bamboo.username=" + bambooUsername.getValue() + "\n");
                buffer.append("bamboo.password=" + bambooPassword.getValue() + "\n");
                break;
            default:
                throw new NotImplementedException("Known build server type");
        }
        return buffer.toString();
    }

    public static class Builder {

        private ConfigurationFile configurationFile = new ConfigurationFile();

        public Builder serverType(BuildServerType serverType) {
            configurationFile.serverType = serverType;
            return this;
        }

        public Builder jenkinsUrl(String jenkinsUrl) {
            configurationFile.jenkinsUrl.setValue(jenkinsUrl);
            return this;
        }

        public Builder jenkinsBuildName(String jenkinsBuildName) {
            configurationFile.jenkinsBuildName.setValue(jenkinsBuildName);
            return this;
        }

        public Builder bambooUrl(String bambooUrl) {
            configurationFile.bambooUrl.setValue(bambooUrl);
            return this;
        }

        public Builder bambooBuildKey(String bambooBuildKey) {
            configurationFile.bambooBuildKey.setValue(bambooBuildKey);
            return this;
        }

        public Builder bambooUsername(String bambooUsername) {
            configurationFile.bambooUsername.setValue(bambooUsername);
            return this;
        }

        public Builder bambooPassword(String bambooPassword) {
            configurationFile.bambooPassword.setValue(bambooPassword);
            return this;
        }

        public ConfigurationFile build() {
            return configurationFile;
        }

    }
}
