package com.comsysto.buildlight.application.gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author zutherb
 */
public class JenkinsConfigurationBox extends VBox {

    private ConfigurationFile configurationFile;

    public JenkinsConfigurationBox(ConfigurationFile configurationFile) {
        super();
        this.configurationFile = configurationFile;
        getChildren().add(serverUrlBox());
        getChildren().add(buildNameBox());
    }

    private Node buildNameBox() {
        HBox serverUrlBox = new HBox();
        Label buildServerUrlLabel = new Label("Build name");
        serverUrlBox.getChildren().add(buildServerUrlLabel);
        TextField buildServerInput = new TextField();
        buildServerInput.textProperty().bindBidirectional(configurationFile.getJenkinsBuildName());
        serverUrlBox.getChildren().add(buildServerInput);
        return serverUrlBox;
    }

    private Node serverUrlBox() {
        HBox serverUrlBox = new HBox();
        Label buildServerUrlLabel = new Label("Build server url:");
        serverUrlBox.getChildren().add(buildServerUrlLabel);
        TextField buildServerInput = new TextField();
        buildServerInput.textProperty().bindBidirectional(configurationFile.getJenkinsUrl());
        serverUrlBox.getChildren().add(buildServerInput);
        return serverUrlBox;
    }
}
