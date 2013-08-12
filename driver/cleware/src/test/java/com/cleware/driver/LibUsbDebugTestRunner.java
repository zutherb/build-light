package com.cleware.driver;

import de.ailis.usb4java.libusb.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Iterator;

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

//        DeviceList list = new DeviceList();
//        LibUsb.getDeviceList(context, list);
//
//        Iterator<Device> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Device device = iterator.next();
//            DeviceDescriptor descriptor = new DeviceDescriptor();
//            LibUsb.getDeviceDescriptor(device, descriptor);
//           // System.out.println(device);
//           // System.out.println(descriptor);
//        }

        DeviceHandle deviceHandle = LibUsb.openDeviceWithVidPid(context, VENDOR_ID, PRODUCT_ID);
        LibUsb.setConfiguration(deviceHandle, 1);
        LibUsb.claimInterface(deviceHandle, 0);
        ByteBuffer buffer = ByteBuffer.allocateDirect(3);
        buffer.put((byte) 0);
        buffer.put((byte) 16);
        buffer.put((byte) 1);

        IntBuffer intBuffer = IntBuffer.allocate(6);

        int i = LibUsb.bulkTransfer(deviceHandle, LibUsb.ENDPOINT_OUT, buffer, intBuffer, 100);

        LibUsb.releaseInterface(deviceHandle, 0);
        LibUsb.close(deviceHandle);
        LibUsb.exit(context);
    }


}
