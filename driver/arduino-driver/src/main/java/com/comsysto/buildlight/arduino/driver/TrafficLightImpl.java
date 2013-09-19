package com.comsysto.buildlight.arduino.driver;

import com.comsysto.buildlight.common.driver.AbstractTrafficLight;
import com.comsysto.buildlight.common.driver.Color;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.comsysto.buildlight.arduino.driver.Arduino.*;

/**
 * @author zutherb
 */
public class TrafficLightImpl extends AbstractTrafficLight<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficLightImpl.class);

    private static Map<Color, Integer> PIN_MAPPING = ImmutableMap.<Color, Integer>builder()
            .put(Color.RED, DIGITAL_PIN2)
            .put(Color.YELLOW, DIGITAL_PIN4)
            .put(Color.GREEN, DIGITAL_PIN7)
            .build();

    private Arduino arduino;


    TrafficLightImpl(Arduino arduino) {
        this.arduino = arduino;
        initArduino();
    }

    private void initArduino() {
        for (Map.Entry<Color, Integer> entry : PIN_MAPPING.entrySet()) {
            arduino.pinMode(entry.getValue(), Arduino.OUTPUT);
        }
    }

    @Override
    public void switchOn(Color color) {
        arduino.digitalWrite(map(color), HIGH);

    }

    @Override
    public void switchOff(Color color) {
        arduino.digitalWrite(map(color), LOW);

    }

    @Override
    public void close() {
        arduino.dispose();
    }

    @Override
    protected Integer map(Color color) {
        return PIN_MAPPING.get(color);
    }
}
