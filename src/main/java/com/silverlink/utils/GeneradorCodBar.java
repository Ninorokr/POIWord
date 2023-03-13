package com.silverlink.utils;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import java.awt.image.BufferedImage;

public class GeneradorCodBar {

    public static BufferedImage generarCodBarCode128(String texto) throws Exception {
        Barcode barcode = BarcodeFactory.createCode128(texto);
        barcode.setDrawingText(false);
        barcode.setSize(128, 13);
        System.out.println(barcode);

        return BarcodeImageHandler.getImage(barcode);
    }

}
