package com.comsysto.buildlight.blinkstick.driver;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author zutherb
 */
public class BlinkStickTrafficLightImplTest {


    @Mock
    private Blinktick blinktick;

    @InjectMocks
    private BlinkStickTrafficLightImpl light;

    @Before
    public void setup() throws IOException {
        initMocks(this);
    }

    @Test
    public void testSwitchOn() throws Exception {
        light.switchOnAllLeds();
        verify(blinktick, times(1)).setColor("red");
        verify(blinktick, times(1)).setColor("yellow");
        verify(blinktick, times(1)).setColor("green");
    }

    @Test
    public void testSwitchOff() throws Exception {
        light.switchOffAllLeds();
        verify(blinktick, times(3)).turnOff();
    }
}
