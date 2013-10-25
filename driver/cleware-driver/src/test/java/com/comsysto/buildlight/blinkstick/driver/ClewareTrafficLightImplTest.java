package com.comsysto.buildlight.blinkstick.driver;

import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDManager;
import com.comsysto.buildlight.common.driver.TrafficLightException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author zutherb
 */
public class ClewareTrafficLightImplTest {

    @Mock
    private HIDManager hidManager;

    @Mock
    private HIDDevice hidDevice;

    @InjectMocks
    private ClewareTrafficLightImpl light;

    @Before
    public void setup() throws IOException {
        initMocks(this);
        when(hidDevice.write(any(byte[].class))).thenReturn(4);
    }

    @Test
    public void testSwitchOn() throws Exception {
        light.switchOnAllLeds();
        verify(hidDevice, times(1)).write(new byte[]{0x0, 0x0, 0x10, 1});
        verify(hidDevice, times(1)).write(new byte[]{0x0, 0x0, 0x11, 1});
        verify(hidDevice, times(1)).write(new byte[]{0x0, 0x0, 0x12, 1});
    }

    @Test(expected = TrafficLightException.class)
    public void testSwitchOnWithException() throws Exception {
        when(hidDevice.write(any(byte[].class))).thenThrow(IOException.class);
        light.switchOnAllLeds();
    }

    @Test
    public void testSwitchOff() throws Exception {
        light.switchOffAllLeds();
        verify(hidDevice, times(1)).write(new byte[]{0x0, 0x0, 0x10, 0});
        verify(hidDevice, times(1)).write(new byte[]{0x0, 0x0, 0x11, 0});
        verify(hidDevice, times(1)).write(new byte[]{0x0, 0x0, 0x12, 0});
    }

    @Test(expected = TrafficLightException.class)
    public void testSwitchOffWithException() throws Exception {
        when(hidDevice.write(any(byte[].class))).thenThrow(IOException.class);
        light.switchOffAllLeds();
    }

    @Test
    public void testClose() throws Exception {
        light.close();
        verify(hidDevice, times(1)).close();
        verify(hidManager, times(1)).release();
    }

    @Test(expected = TrafficLightException.class)
    public void testCloseWithException() throws Exception {
        doThrow(IOException.class).when(hidDevice).close();
        light.close();
    }
}
