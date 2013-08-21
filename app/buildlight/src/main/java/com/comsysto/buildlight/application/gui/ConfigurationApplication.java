package com.comsysto.buildlight.application.gui;

import com.comsysto.buildlight.application.ConfigurationFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;

/**
 * @author zutherb
 */
public class ConfigurationApplication extends Application {

    private ConfigurationFile configurationFile = new ConfigurationFile.Builder()
            .serverType(BuildServerType.Jenkins)
            .build();

    private Pane jenkinsConfigurationBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = vBox();
        vbox.getChildren().addAll(
                buildServerCombobox(),
                jenkinsConfiguration(),
                saveButton()
        );

        primaryStage.setTitle("Build Light Configuration");
        primaryStage.setScene(new Scene(vbox, 290, 250));
        primaryStage.show();
    }

    private Node saveButton() {
        Button saveButton = new Button("Save");
        saveButton.setDefaultButton(true);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {

                    FileUtils.write(ConfigurationFile.CONFIGURATION_FILE, configurationFile.toString());
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        return saveButton;
    }

    private Pane buildServerCombobox() {
        return new BuildServerBox() {

            @Override
            protected void buildServerComboBoxChanged(BuildServerType currentValue) {
                jenkinsConfigurationBox.setVisible(BuildServerType.Jenkins.equals(currentValue));
                //configurationFile.setServerType(currentValue);
            }
        };
    }

    private Pane jenkinsConfiguration() {
        jenkinsConfigurationBox = new JenkinsConfigurationBox(configurationFile);
        return jenkinsConfigurationBox;
    }


    private VBox vBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);
        return vbox;
    }
}
