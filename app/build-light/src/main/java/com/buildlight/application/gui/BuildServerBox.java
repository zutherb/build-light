package com.buildlight.application.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * @author zutherb
 */
public class BuildServerBox extends HBox {
    public BuildServerBox() {
        super();
        getChildren().add(selectBuildServerLabel());
        getChildren().add(buildServerComboBox());
    }

    private Node buildServerComboBox() {
        ComboBox buildServerComboBox = new ComboBox();
        buildServerComboBox.getItems().addAll(BuildServerType.values());
        buildServerComboBox.valueProperty().setValue(BuildServerType.values()[0]);
        buildServerComboBox.valueProperty().addListener(new ChangeListener<BuildServerType>() {

            @Override
            public void changed(ObservableValue observableValue, BuildServerType oldValue, BuildServerType currentValue) {
                buildServerComboBoxChanged(currentValue);

            }
        });
        return buildServerComboBox;
    }

    protected void buildServerComboBoxChanged(BuildServerType currentValue) {
        //Hock method
    }

    private Node selectBuildServerLabel() {
        return new Label("Select a build server:");
    }

}
