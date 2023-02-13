package com.silverlink;

import com.silverlink.utils.Oracion;
import com.silverlink.utils.Parrafo;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;

import static com.silverlink.utils.GeneradorCodBar.generarCodBarCode128;

public class BVeritas {

    public void generar() throws Exception {

        BufferedImage codBar = generarCodBarCode128("0012300019999");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(codBar, "jpeg", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        XWPFDocument documento = new XWPFDocument();

        try (FileOutputStream out = new FileOutputStream(new File("D:\\Apache POI test\\BVeritas.docx"))) {

            CTSectPr sectPr = documento.getDocument().getBody().addNewSectPr();
            CTPageMar pageMar = sectPr.addNewPgMar();
            pageMar.setLeft(BigInteger.valueOf(990L)); //VALOR EXACTO DE MARGEN
            pageMar.setRight(BigInteger.valueOf(990L)); //VALOR EXACTO DE MARGEN
            pageMar.setTop(BigInteger.valueOf(1280L)); //VALOR EXACTO DE MARGEN
            pageMar.setBottom(BigInteger.valueOf(720L)); //VALOR EXACTO DE MARGEN

            CTPageSz pageSize = sectPr.addNewPgSz();
//                    getPgSz(); //A4: 595x842 * 20 = 11900x16840 (W x H)
//            pageSize.setOrient(STPageOrientation.PORTRAIT);
            pageSize.setW(BigInteger.valueOf(11900L));
            pageSize.setH(BigInteger.valueOf(16840L));

            Parrafo p1 = new Parrafo(documento, ParagraphAlignment.CENTER);
            Oracion p1o1 = new Oracion(p1, 11, true, "");
            p1o1.retornarCarro();
            p1o1.escribir("NORMA TECNICA DE CALIDAD DE LOS SERVICIOS ELECTRICOS");
            p1o1.retornarCarro();
            p1o1.escribir("AVISO DE CONTRASTE NTCSE");
        } catch (IOException ioe){
            System.out.println("No se pudo generar el archivo");
            ioe.getMessage();
        }
    }


}
