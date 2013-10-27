package com.comsysto.buildlight.arduino.driver.core;

public class Arduino {

    public static final int DIGITAL_PIN0 = 0;
    public static final int DIGITAL_PIN1 = 1;
    public static final int DIGITAL_PIN2 = 2;
    public static final int DIGITAL_PIN3 = 3;
    public static final int DIGITAL_PIN4 = 4;
    public static final int DIGITAL_PIN5 = 5;
    public static final int DIGITAL_PIN6 = 6;
    public static final int DIGITAL_PIN7 = 7;
    public static final int DIGITAL_PIN8 = 8;
    public static final int DIGITAL_PIN9 = 9;
    public static final int DIGITAL_PIN10 = 10;
    public static final int DIGITAL_PIN11 = 11;
    public static final int DIGITAL_PIN12 = 12;
    public static final int DIGITAL_PIN13 = 13;

    public static final int ANALOG_PIN0 = 0;
    public static final int ANALOG_PIN1 = 1;
    public static final int ANALOG_PIN2 = 2;
    public static final int ANALOG_PIN3 = 3;
    public static final int ANALOG_PIN4 = 4;
    public static final int ANALOG_PIN5 = 5;

    public static final int INPUT = 0;
    public static final int OUTPUT = 1;
    public static final int PWM = 3;
    public static final int SERVO = 4;
    public static final int SHIFT = 5;
    public static final int I2C = 6;
    public static final int LOW = 0;
    public static final int HIGH = 1;

    private final int MAX_DATA_BYTES = 32;

    private final int DIGITAL_MESSAGE = 0x90;
    private final int ANALOG_MESSAGE = 0xE0;
    private final int REPORT_ANALOG = 0xC0;
    private final int REPORT_DIGITAL = 0xD0;
    private final int SET_PIN_MODE = 0xF4;
    private final int REPORT_VERSION = 0xF9;
    private final int SYSTEM_RESET = 0xFF;
    private final int START_SYSEX = 0xF0;
    private final int END_SYSEX = 0xF7;

    private SerialProxy serialProxy = null;
    private Serial serial = null;

    private int waitForData = 0;
    private int executeMultiByteCommand = 0;
    private int multiByteChannel = 0;
    private int[] storedInputData = new int[MAX_DATA_BYTES];
    private boolean parsingSysex = false;
    private int sysexBytesRead = 0;

    private int[] digitalOutputData = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] digitalInputData = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] analogInputData = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private int majorVersion = 0;
    private int minorVersion = 0;


    public class SerialProxy {
        public void serialEvent(Serial which) {
            while (available() > 0)
                processInput();
        }
    }

    public Arduino(String iname) {
        this(iname, 57600);
    }

    public Arduino(String iname, int irate) {
        serialProxy = new SerialProxy();
        serial = new Serial(serialProxy, iname, irate);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 6; i++) {
            serial.write(REPORT_ANALOG | i);
            serial.write(1);
        }

        for (int i = 0; i < 2; i++) {
            serial.write(REPORT_DIGITAL | i);
            serial.write(1);
        }
    }

    public int digitalRead(int pin) {
        return (digitalInputData[pin >> 3] >> (pin & 0x07)) & 0x01;
    }

    public int analogRead(int pin) {
        return analogInputData[pin];
    }

    public Arduino pinMode(int pin, int mode) {
        serial.write(SET_PIN_MODE);
        serial.write(pin);
        serial.write(mode);
        return this;
    }

    public Arduino digitalWrite(int pin, int value) {
        int portNumber = (pin >> 3) & 0x0F;

        if (value == 0)
            digitalOutputData[portNumber] &= ~(1 << (pin & 0x07));
        else
            digitalOutputData[portNumber] |= (1 << (pin & 0x07));
        serial.write(DIGITAL_MESSAGE | portNumber);
        serial.write(digitalOutputData[portNumber] & 0x7F);
        serial.write(digitalOutputData[portNumber] >> 7);
        return this;
    }

    public Arduino analogWrite(int pin, int value) {
        pinMode(pin, PWM);
        serial.write(ANALOG_MESSAGE | (pin & 0x0F));
        serial.write(value & 0x7F);
        serial.write(value >> 7);
        return this;
    }

    public void setDigitalInputs(int portNumber, int portData) {
        digitalInputData[portNumber] = portData;
    }

    public void setAnalogInput(int pin, int value) {
        analogInputData[pin] = value;
    }


    public void dispose() {
        this.serial.dispose();
    }

    public static String[] list() {
        return Serial.list();
    }


    private void setVersion(int majorVersion, int minorVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }

    private int available() {
        return serial.available();
    }

    private void processInput() {
        int inputData = serial.read();
        int command;

        if (parsingSysex) {
            if (inputData == END_SYSEX) {
                parsingSysex = false;
            } else {
                storedInputData[sysexBytesRead] = inputData;
                sysexBytesRead++;
            }
        } else if (waitForData > 0 && inputData < 128) {
            waitForData--;
            storedInputData[waitForData] = inputData;

            if (executeMultiByteCommand != 0 && waitForData == 0) {
                switch (executeMultiByteCommand) {
                    case DIGITAL_MESSAGE:
                        setDigitalInputs(multiByteChannel, (storedInputData[0] << 7) + storedInputData[1]);
                        break;
                    case ANALOG_MESSAGE:
                        setAnalogInput(multiByteChannel, (storedInputData[0] << 7) + storedInputData[1]);
                        break;
                    case REPORT_VERSION:
                        setVersion(storedInputData[1], storedInputData[0]);
                        break;
                }
            }
        } else {
            if (inputData < 0xF0) {
                command = inputData & 0xF0;
                multiByteChannel = inputData & 0x0F;
            } else {
                command = inputData;
            }
            switch (command) {
                case DIGITAL_MESSAGE:
                case ANALOG_MESSAGE:
                case REPORT_VERSION:
                    waitForData = 2;
                    executeMultiByteCommand = command;
                    break;
            }
        }
    }
}