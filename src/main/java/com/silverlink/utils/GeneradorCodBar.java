package com.silverlink.utils;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import java.awt.image.BufferedImage;

public class BarCodeGenerator {

    

    public static BufferedImage generateCode128BarcodeImage(String barcodeText) throws Exception {
//        File font = new File("C:\\Windows\\Fonts\\Arial.ttf");
//        Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
        Barcode barcode = BarcodeFactory.createCode128(barcodeText);
//        barcode.setFont(Font.createFont(TRUETYPE_FONT, font));

        return BarcodeImageHandler.getImage(barcode);
    }

    private String createCode128(String text) {
        return (char) 136 + text + checksum(text) + (char) 138;
    }

    char checksum(String text) {
        int result = 104; // Code 128B start code
        for (int i = 0; i < text.length(); i++) {
            result += ((int) text.charAt(i) - 32) * (i + 1);
        }
        return (char) (result % 103 + 32); // Return the character value of the checksum.
    }

}
