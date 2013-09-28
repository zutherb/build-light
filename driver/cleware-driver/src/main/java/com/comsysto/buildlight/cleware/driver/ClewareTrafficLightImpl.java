package com.comsysto.buildlight.cleware.driver;

import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDManager;
import com.comsysto.buildlight.common.driver.AbstractTrafficLight;
import com.comsysto.buildlight.common.driver.Color;
import com.comsysto.buildlight.common.driver.TrafficLightException;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static java.lang.String.format;
import static org.apache.commons.lang.Validate.isTrue;

/**
 * @author zutherb
 */
public class ClewareTrafficLightImpl extends AbstractTrafficLight<Byte> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClewareTrafficLightImpl.class);

    private static final byte ZERO = (byte) 0x0;
    private static final byte ONE = (byte) 0x1;

    private static Map<Color, Byte> LED_MAPPING = ImmutableMap.<Color, Byte>builder()
            .put(Color.RED, new Byte((byte) 0x10))
            .put(Color.YELLOW, new Byte((byte) 0x11))
            .put(Color.GREEN, new Byte((byte) 0x12))
            .build();

    private final HIDManager hidManager;
    private final HIDDevice hidDevice;

    ClewareTrafficLightImpl(HIDManager hidManager, HIDDevice hidDevice) {
        this.hidManager = hidManager;
        this.hidDevice = hidDevice;
    }

    @Override
    public void switchOn(Color color) {
        try {
            byte[] writeBuffer = createSwitchOnSequenceBuffer(color);
            int writtenBytes = hidDevice.write(writeBuffer);
            isTrue(writtenBytes == writeBuffer.length, MESSAGE_NOT_ALL_BYTES_FROM_THE_WAS_WRITTEN_TO_USB);
            LOGGER.debug(format(MESSAGE_SWITCH_ON_LED, color.name()));
        } catch (Exception e) {
            LOGGER.error(MESSAGE_SWITCH_ON_ERROR, color.name());
            throw new TrafficLightException(e);
        }
    }

    @Override
    public void switchOff(Color color) {
        try {
            byte[] writeBuffer = createSwitchOffSequenceBuffer(color);
            int writtenBytes = hidDevice.write(writeBuffer);
            isTrue(writtenBytes == writeBuffer.length, MESSAGE_NOT_ALL_BYTES_FROM_THE_WAS_WRITTEN_TO_USB);
            LOGGER.debug(format(MESSAGE_SWITCH_OFF_LED, color.name()));
        } catch (Exception e) {
            LOGGER.error(MESSAGE_SWITCH_OFF_ERROR, color.name());
            throw new TrafficLightException(e);
        }
    }

    private byte[] createSwitchOnSequenceBuffer(Color color) {
        return new byte[]{ZERO, ZERO, map(color), ONE};
    }

    private byte[] createSwitchOffSequenceBuffer(Color color) {
        return new byte[]{ZERO, ZERO, map(color), ZERO};
    }

    @Override
    public void close() {
        try {
            hidDevice.close();
            hidManager.release();
        } catch (Exception e) {
            LOGGER.error(MESSAGE_USB_DEVICE_COULD_NOT_BE_CLOSED);
            throw new TrafficLightException(e);
        }
    }

    @Override
    protected Byte map(Color color) {
        return LED_MAPPING.get(color);
    }
}
