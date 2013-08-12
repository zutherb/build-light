package com.cleware.gui;

import com.cleware.driver.Led;
import com.cleware.driver.TrafficLight;
import com.cleware.driver.TrafficLightFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author zutherb
 */
public class TrafficLightApplication extends Application {

    private static final TrafficLight TRAFFIC_LIGHT = TrafficLightFactory.instance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TRAFFIC_LIGHT.switchOffAllLeds();

        VBox vbox = vBox();
        vbox.getChildren().addAll(buttonForLed(Led.RED), buttonForLed(Led.YELLOW), buttonForLed(Led.GREEN));

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

    private Button buttonForLed(final Led led) {
        Button redButton = new Button();
        redButton.setStyle("-fx-background-color: " + led.name().toLowerCase());
        redButton.setPrefSize(270, 70);
        redButton.setOnAction(new EventHandler<ActionEvent>() {

            private boolean on = false;

            @Override
            public void handle(ActionEvent event) {
                TRAFFIC_LIGHT.switchOffAllLeds();
                if (!on) {
                    TRAFFIC_LIGHT.switchOn(led);
                    on = true;
                } else {
                    TRAFFIC_LIGHT.switchOff(led);
                    on = false;
                }
            }
        });
        return redButton;
    }

    @Override
    public void stop() throws Exception {
        TRAFFIC_LIGHT.close();
        System.exit(0);
    }
}
