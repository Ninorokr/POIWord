package com.silverlink.utils;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneradorCodBar {

//    public static void crearImagenCodBar(String codigo) throws Exception {
//        File outputfile = new File("D:\\Apache POI test\\barcode.jpg");
//        ImageIO.write(generarCodBarCode128(codigo), "jpg", outputfile);
//    }

    public static BufferedImage generarCodBarCode128(String texto) throws Exception {
//        File font = new File("C:\\Windows\\Fonts\\Arial.ttf");
//        Barcode barcode = BarcodeFactory.createEAN13(texto);
        Barcode barcode = BarcodeFactory.createCode128(texto);
        barcode.setDrawingText(false);
        barcode.setSize(128, 13);
        System.out.println(barcode);
//        barcode.setFont(Font.createFont(TRUETYPE_FONT, font));

        return BarcodeImageHandler.getImage(barcode);
    }

//    public static String codificar128(String texto) {
//        return (char) 136 + texto + checksum(texto) + (char) 138;
//    }
//
//    public static char checksum(String texto) {
//        int result = 104; // Code 128B start code
//        for (int i = 0; i < texto.length(); i++) {
//            result += ((int) texto.charAt(i) - 32) * (i + 1);
//        }
//        return (char) (result % 103 + 32); // Return the character value of the checksum.
//    }

}
