package com.comsysto.buildlight.arduino.driver.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ArduinoOutputTest extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color on = new Color(4, 79, 111);
    private static final Color off = new Color(84, 145, 158);

    private Arduino arduino = null;

    private int[] values = {
            Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW,
            Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW,
            Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW
    };

    public ArduinoOutputTest() {
        initialize();
    }

    private void initialize() {
        setTitle("arduino_output");
        setSize(470, 280);

        initArduino();

        final MyPanel myPanel = new MyPanel();
        add(myPanel);

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    myPanel.repaint();
                }
            }
        }.start();
    }

    private void initArduino() {
        arduino = new Arduino(Arduino.list()[5], 57600);

        for (int i = 0; i < 13; i++)
            arduino.pinMode(i, Arduino.OUTPUT);
    }

    private class MyPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public MyPanel() {
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int pin = (450 - e.getX()) / 30;
                    if (values[pin] == Arduino.LOW) {
                        arduino.digitalWrite(pin, Arduino.HIGH);
                        values[pin] = Arduino.HIGH;
                    } else {
                        arduino.digitalWrite(pin, Arduino.LOW);
                        values[pin] = Arduino.LOW;
                    }
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());

            for (int i = 0; i <= 13; i++) {
                if (values[i] == Arduino.HIGH)
                    g.setColor(on);
                else
                    g.setColor(off);
                g.fillRect(420 - i * 30, 30, 20, 20);
            }
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final ArduinoOutputTest thisClass = new ArduinoOutputTest();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        thisClass.dispose();
                    }
                });
                thisClass.setVisible(true);
            }
        });
    }
}