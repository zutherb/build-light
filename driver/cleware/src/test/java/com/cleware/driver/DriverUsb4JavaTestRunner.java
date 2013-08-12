package com.cleware.driver;

import de.ailis.usb4java.libusb.Context;
import de.ailis.usb4java.libusb.LibUsb;
import org.apache.commons.lang.ArrayUtils;

import javax.usb.*;
import java.util.List;

/**
 * @author zutherb
 */
public class DriverUsb4JavaTestRunner {

    private static final short VENDOR_ID = 0xD50;     //Cleware Vendor Id
    private static final short PRODUCT_ID = 0x8;      //Traffic Light Product Id

    public static void main(String[] args) throws UsbException {
        UsbServices usbServices = UsbHostManager.getUsbServices();
        Context context = new Context();
        LibUsb.init(context);
        LibUsb.setDebug(context, LibUsb.LOG_LEVEL_DEBUG);
        UsbDevice light = findDevice(usbServices.getRootUsbHub(), VENDOR_ID, PRODUCT_ID);

        UsbControlIrp irp = light.createUsbControlIrp(
                (byte) (UsbConst.REQUESTTYPE_DIRECTION_IN
                        | UsbConst.REQUESTTYPE_TYPE_STANDARD
                        | UsbConst.REQUESTTYPE_RECIPIENT_DEVICE),
                UsbConst.REQUEST_GET_CONFIGURATION,
                (short) 0,
                (short) 0
        );
        irp.setData(new byte[]{0, 16, 1});
        light.syncSubmit(irp);
        System.out.println(ArrayUtils.toString(irp.getData()));


    }

    public static UsbDevice findDevice(UsbHub hub, short vendorId, short productId) {
        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if (desc.idVendor() == vendorId && desc.idProduct() == productId) return device;
            if (device.isUsbHub()) {
                device = findDevice((UsbHub) device, vendorId, productId);
                if (device != null) return device;
            }
        }
        return null;
    }

}
