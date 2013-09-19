package com.comsysto.buildlight.arduino.driver;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

public class Serial implements SerialPortEventListener {

    private static final String DNAME = "COM1";
    private static final int DRATE = 9600;
    private static final char DPARITY = 'N';
    private static final int DDATABITS = 8;
    private static final float DSTOPBITS = 1;

    private SerialPort port = null;

    private int rate = 0;
    private int parity = 0;
    private int databits = 0;
    private int stopbits = 0;

    private InputStream input = null;
    private OutputStream output = null;

    private byte buffer[] = new byte[32768];
    private int bufferIndex = 0;
    private int bufferLast = 0;

    private int bufferSize = 1;
    private boolean bufferUntil = false;
    private int bufferUntilByte = 0;

    private Object proxy = null;
    private Method serialEventMethod = null;

    public Serial(Object proxy) {
        this(proxy, DNAME, DRATE, DPARITY, DDATABITS, DSTOPBITS);
    }

    public Serial(Object proxy, int irate) {
        this(proxy, DNAME, irate, DPARITY, DDATABITS, DSTOPBITS);
    }

    public Serial(Object proxy, String iname) {
        this(proxy, iname, DRATE, DPARITY, DDATABITS, DSTOPBITS);
    }

    public Serial(Object proxy, String iname, int irate) {
        this(proxy, iname, irate, DPARITY, DDATABITS, DSTOPBITS);
    }

    public Serial(Object proxy, String iname, int irate, char iparity, int idatabits, float istopbits) {
        this.proxy = proxy;
        rate = irate;
        if (iparity == 'E')
            parity = SerialPort.PARITY_EVEN;
        else if (iparity == '0')
            parity = SerialPort.PARITY_ODD;
        else
            parity = SerialPort.PARITY_NONE;
        this.databits = idatabits;

        if (istopbits == 1.5f)
            stopbits = SerialPort.STOPBITS_1_5;
        else if (istopbits == 2)
            stopbits = SerialPort.STOPBITS_2;
        else
            stopbits = SerialPort.STOPBITS_1;

        try {
            Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
            while (portList.hasMoreElements()) {
                CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    if (portId.getName().equals(iname)) {
                        port = (SerialPort) portId.open("serial madness", 2000);
                        input = port.getInputStream();
                        output = port.getOutputStream();
                        port.setSerialPortParams(rate, databits, stopbits, parity);
                        port.addEventListener(this);
                        port.notifyOnDataAvailable(true);
                    }
                }
            }
        } catch (PortInUseException e) {
            e.printStackTrace();
            port = null;
            input = null;
            output = null;
        } catch (IOException e) {
            e.printStackTrace();
            port = null;
            input = null;
            output = null;
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }

        try {
            //serialEventMethod=proxy.getClass().getMethod("serialEvent", new Class[]{com.comsysto.buildlight.arduino.driver.Serial.class});
            serialEventMethod = proxy.getClass().getMethod("serialEvent", new Class[]{Serial.class});
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        dispose();
    }

    public void dispose() {
        try {
            if (input != null)
                input.close();
            if (output != null)
                output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        input = null;
        output = null;

        try {
            port.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        port = null;
    }

    public void setDTR(boolean state) {
        port.setDTR(state);
    }

    @Override
    public synchronized void serialEvent(SerialPortEvent serialEvent) {
        if (serialEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                while (input.available() > 0) {
                    synchronized (buffer) {
                        if (bufferLast == buffer.length) {
                            byte temp[] = new byte[bufferLast << 1];
                            System.arraycopy(buffer, 0, temp, 0, bufferLast);
                            buffer = temp;
                        }
                        buffer[bufferLast++] = (byte) input.read();
                        if (serialEventMethod != null) {
                            if ((bufferUntil &&
                                    (buffer[bufferLast - 1] == bufferUntilByte)) ||
                                    (!bufferUntil &&
                                            ((bufferLast - bufferIndex) >= bufferSize))) {
                                try {
                                    serialEventMethod.invoke(proxy, new Object[]{this});
                                } catch (InvocationTargetException e) {
                                    //String msg = "error, disabling serialEvent() for " + port;
                                    //System.err.println(msg);
                                    e.printStackTrace();
                                    serialEventMethod = null;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void buffer(int count) {
        bufferUntil = false;
        bufferSize = count;
    }

    public void bufferUntil(int what) {
        bufferUntil = true;
        bufferUntilByte = what;
    }

    public int available() {
        return bufferLast - bufferIndex;
    }

    public void clear() {
        bufferLast = 0;
        bufferIndex = 0;
    }

    public int read() {
        if (bufferIndex == bufferLast)
            return -1;
        synchronized (buffer) {
            int outgoing = buffer[bufferIndex++] & 0xff;
            if (bufferIndex == bufferLast) {
                bufferIndex = 0;
                bufferLast = 0;
            }
            return outgoing;
        }
    }

    public int last() {
        if (bufferIndex == bufferLast)
            return -1;
        synchronized (buffer) {
            int outgoing = buffer[bufferLast - 1];
            bufferIndex = 0;
            bufferLast = 0;
            return outgoing;
        }
    }

    public char readChar() {
        if (bufferIndex == bufferLast)
            return (char) -1;
        return (char) read();
    }

    public char lastChar() {
        if (bufferIndex == bufferLast)
            return (char) -1;
        return (char) last();
    }

    public byte[] readBytes() {
        if (bufferIndex == bufferLast)
            return null;
        synchronized (buffer) {
            int length = bufferLast - bufferIndex;
            byte outgoing[] = new byte[length];
            System.arraycopy(buffer, bufferIndex, outgoing, 0, length);

            bufferIndex = 0;
            bufferLast = 0;
            return outgoing;
        }
    }

    public int readBytes(byte outgoing[]) {
        if (bufferIndex == bufferLast)
            return 0;
        synchronized (buffer) {
            int length = bufferLast - bufferIndex;
            if (length > outgoing.length)
                length = outgoing.length;
            System.arraycopy(buffer, bufferIndex, outgoing, 0, length);

            bufferIndex += length;
            if (bufferIndex == bufferLast) {
                bufferIndex = 0;
                bufferLast = 0;
            }
            return length;
        }
    }

    public byte[] readBytesUntil(int interesting) {
        if (bufferIndex == bufferLast)
            return null;
        byte what = (byte) interesting;

        synchronized (buffer) {
            int found = -1;
            for (int i = bufferIndex; i < bufferLast; i++) {
                if (buffer[i] == what) {
                    found = i;
                    break;
                }
            }
            if (found == -1)
                return null;

            int length = found - bufferIndex + 1;
            byte outgoing[] = new byte[length];
            System.arraycopy(buffer, bufferIndex, outgoing, 0, length);

            bufferIndex += length;
            if (bufferIndex == bufferLast) {
                bufferIndex = 0;
                bufferLast = 0;
            }
            return outgoing;
        }
    }

    public int readBytesUntil(int interesting, byte outgoing[]) {
        if (bufferIndex == bufferLast)
            return 0;
        byte what = (byte) interesting;

        synchronized (buffer) {
            int found = -1;
            for (int i = bufferIndex; i < bufferLast; i++) {
                if (buffer[i] == what) {
                    found = i;
                    break;
                }
            }
            if (found == -1)
                return 0;
            int length = found - bufferIndex + 1;
            if (length > outgoing.length) {
                System.err.println("readBytesUntil() byte buffer is" +
                        " too small for the " + length +
                        " bytes up to and including char " + interesting);
                return -1;
            }
            System.arraycopy(buffer, bufferIndex, outgoing, 0, length);

            bufferIndex += length;
            if (bufferIndex == bufferLast) {
                bufferIndex = 0;
                bufferLast = 0;
            }
            return length;
        }
    }

    public String readString() {
        if (bufferIndex == bufferLast)
            return null;
        return new String(readBytes());
    }

    public String readStringUntil(int interesting) {
        byte b[] = readBytesUntil(interesting);
        if (b == null)
            return null;
        return new String(b);
    }

    public void write(int what) {
        try {
            output.write(what & 0xff);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(byte bytes[]) {
        try {
            output.write(bytes);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String what) {
        write(what.getBytes());
    }

    public static String[] list() {
        List<String> list = new ArrayList<String>();
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                String name = portId.getName();
                list.add(name);
            }
        }
        String[] outgoing = new String[list.size()];
        for (int i = 0; i < list.size(); i++)
            outgoing[i] = list.get(i);
        return outgoing;
    }
}