package com.github.zutherb.buildlight.application;

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

    private String jenkinsUrl;
    private String jenkinsBuildName;
    private String bambooUrl;
    private String bambooBuildKey;
    private String bambooUsername;
    private String bambooPassword;

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        switch (serverType) {
            case Jenkins:
                buffer.append("build.server=").append(serverType.name()).append("\n")
                      .append("jenkins.server.url=").append(jenkinsUrl).append("\n")
                      .append("jenkins.build.names=").append(jenkinsBuildName).append("\n");
                break;
            case Bamboo:
                buffer.append("build.server=")
                      .append(serverType.name()).append("\n")
                      .append("bamboo.server.url=").append(bambooUrl).append("\n")
                      .append("bamboo.build.key=").append(bambooBuildKey).append("\n")
                      .append("bamboo.username=").append(bambooUsername).append("\n")
                      .append("bamboo.password=").append(bambooPassword).append("\n");
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
            configurationFile.jenkinsUrl = jenkinsUrl;
            return this;
        }

        public Builder jenkinsBuildName(String jenkinsBuildName) {
            configurationFile.jenkinsBuildName = jenkinsBuildName;
            return this;
        }

        public Builder bambooUrl(String bambooUrl) {
            configurationFile.bambooUrl = bambooUrl;
            return this;
        }

        public Builder bambooBuildKey(String bambooBuildKey) {
            configurationFile.bambooBuildKey = bambooBuildKey;
            return this;
        }

        public Builder bambooUsername(String bambooUsername) {
            configurationFile.bambooUsername = bambooUsername;
            return this;
        }

        public Builder bambooPassword(String bambooPassword) {
            configurationFile.bambooPassword = bambooPassword;
            return this;
        }

        public ConfigurationFile build() {
            return configurationFile;
        }

    }
}
