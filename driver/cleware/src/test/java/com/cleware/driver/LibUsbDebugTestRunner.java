package com.cleware.driver;

import de.ailis.usb4java.libusb.Context;
import de.ailis.usb4java.libusb.DeviceHandle;
import de.ailis.usb4java.libusb.LibUsb;

import java.nio.ByteBuffer;

/**
 * @author zutherb
 */
public class LibUsbDebugTestRunner {

    private static final int VENDOR_ID = 0xD50;     //Cleware Vendor Id
    private static final int PRODUCT_ID = 0x8;      //Traffic Light Product Id

    public static void main(String[] args) {
        Context context = new Context();
        LibUsb.init(context);
        LibUsb.setDebug(context, LibUsb.LOG_LEVEL_DEBUG);
        DeviceHandle deviceHandle = LibUsb.openDeviceWithVidPid(context, VENDOR_ID, PRODUCT_ID);
        LibUsb.setConfiguration(deviceHandle, 1);
        LibUsb.claimInterface(deviceHandle, 0);
        ByteBuffer buffer = ByteBuffer.allocate(3);
        buffer.put((byte) 0);
        buffer.put((byte) 16);
        buffer.put((byte) 1);

        int i = LibUsb.bulkTransfer(deviceHandle, 0x04, buffer, buffer.asIntBuffer(), 2000);

        LibUsb.releaseInterface(deviceHandle, 0);
        LibUsb.close(deviceHandle);
        LibUsb.exit(context);
    }


}
