package com.comsysto.buildlight.cleware.commandline.gui;

import com.comsysto.buildlight.cleware.ClewareTrafficLightFactoryBean;
import com.comsysto.buildlight.common.driver.Color;
import com.comsysto.buildlight.common.driver.TrafficLight;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zutherb
 */
public class TrafficLightApplication extends Application {

    private static final TrafficLight TRAFFIC_LIGHT = ClewareTrafficLightFactoryBean.instance();

    private static Map<Color, Boolean> LED_STATES;

    static {
        LED_STATES = new HashMap<Color, Boolean>();
        for (Color color : Color.values()) {
            LED_STATES.put(color, Boolean.FALSE);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TRAFFIC_LIGHT.switchOffAllLeds();

        VBox vbox = vBox();
        vbox.getChildren().addAll(buttonForLed(Color.RED), buttonForLed(Color.YELLOW), buttonForLed(Color.GREEN));

        primaryStage.setTitle("Cleware Traffic Light");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(vbox, 290, 250));
        primaryStage.show();
    }

    private VBox vBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);
        return vbox;
    }

    private Button buttonForLed(final Color color) {
        Button redButton = new Button();
        redButton.setStyle("-fx-background-color: " + color.name().toLowerCase());
        redButton.setPrefSize(270, 70);
        redButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!LED_STATES.get(color)) {
                    TRAFFIC_LIGHT.switchOn(color);
                    LED_STATES.put(color, Boolean.TRUE);
                } else {
                    TRAFFIC_LIGHT.switchOff(color);
                    LED_STATES.put(color, Boolean.FALSE);
                }
            }
        });
        return redButton;
    }
}
