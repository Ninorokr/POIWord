package com.silverlink;

import com.silverlink.utils.Oracion;
import com.silverlink.utils.Parrafo;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.STHighlightColorImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;

import static com.silverlink.utils.GeneradorCodBar.generarCodBarCode128;

public class NTSCE {

    public void generar() throws Exception{

        BufferedImage codBar = generarCodBarCode128("0012300019999");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(codBar, "jpeg", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        XWPFDocument documento = new XWPFDocument();

        //Write the Document in file system
        try(FileOutputStream out = new FileOutputStream(new File("D:\\Apache POI test\\NTCSE.docx"))){

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
            
            Parrafo p2 = new Parrafo(documento, ParagraphAlignment.BOTH);

            Oracion p2o1 = new Oracion(p2, "");
            p2o1.retornarCarro(2);
            p2o1.escribir("Cliente N°");
            p2o1.tabular();
            p2o1.escribir(": ");

            Oracion p2o2 = new Oracion(p2, 10, true, "2391829");
            p2o2.tabular(7);
            p2o2.escribir("        ");
            p2o2.tabular();

            Oracion p2o3 = new Oracion(p2, "Ruta");

            Oracion p2o4 = new Oracion(p2, 10, true, " : 23-311-0122");
            p2o4.retornarCarro();

            Oracion p2o5 = new Oracion(p2, "");
            p2o5.escribir("Nombre ");
            p2o5.tabular(2);
            p2o5.escribir(": ");

            Oracion p2o6 = new Oracion(p2, 10, true, "MUNICIPALIDAD METROPOLITANA DE LIMA");
            p2o6.retornarCarro();
            
            Oracion p2o7 = new Oracion(p2, "");
            p2o7.escribir("Dirección ");
            p2o7.tabular();
            p2o7.escribir(": ");

            Oracion p2o8 = new Oracion(p2, 10, true, "AV. ABANCAY ESQ. JR. UCAYALI");

            Parrafo p3 = new Parrafo(documento, ParagraphAlignment.BOTH, 900, 550);
            Oracion p3o1 = new Oracion(p3, 10, true, "  LIMA CERCADO");

            Parrafo p4 = new Parrafo(documento, ParagraphAlignment.BOTH);
            Oracion p4o1 = new Oracion(p4, "");
            p4o1.retornarCarro();
            p4o1.escribir("Estimado Cliente:");
            p4o1.retornarCarro(2);
            p4o1.escribir("Hacemos de su conocimiento que de acuerdo con lo dispuesto en la Norma Técnica de " +
                    "Calidad de los Servicios Eléctricos – NTCSE, aprobado según D.S. N° 020-97-EM y la Directiva de " +
                    "Contrastación de Medidores de Energía Activa y Reactiva e Indicadores de Máxima Demanda aprobado " +
                    "según R.M. N° 496-2005-MEM/DM; ENEL Distribución Perú S.A.A. viene ejecutando pruebas de " +
                    "contraste a una muestra de su zona de concesión. ");
            p4o1.retornarCarro(2);
            p4o1.escribir("Al salir sorteado su suministro, le agradeceremos brinde las facilidades de acceso a la empresa ");

            Oracion p402 = new Oracion(p4, 10, true, "SERVCON S.A.");
            Oracion p4o3 = new Oracion(p4, " a su medidor el día 11/01/2023, a las 9:00 a.m. aproximadamente, " +
                    "momento en que lo visitaran para efectuar las pruebas mencionadas.");
            p4o3.retornarCarro();
            p4o3.escribir("En caso de que su medidor ");
            Oracion p4o4 = new Oracion(p4, 10, true, "NO cumpla");
            Oracion p4o5 = new Oracion(p4, " con los valores de error indicados en la Directiva de " +
                    "Contraste antes mencionada, estaremos procediendo a cambiar su equipo de medida ");
            Oracion p4o6 = new Oracion(p4, 10, true, "lightGray", "SIN COSTO");

            Oracion p4o7 = new Oracion(p4, " alguno para usted, dentro de las siguientes 48 horas de efectuada la prueba de contraste.");
            p4o7.retornarCarro(2);
            p4o7.escribir("Por cualquier duda o consulta puede llamarnos a nuestro servicio de ");

            Oracion p4o8 = new Oracion(p4, 10, true, "Fonocliente");
            Oracion p4o9 = new Oracion(p4, " al ");
            Oracion p4o10 = new Oracion(p4, 10, true, "517-1717.");
            p4o10.retornarCarro();

            Oracion p4o11 = new Oracion(p4, "Sin otro particular y seguros de contar con su apoyo, nos despedimos.");
            p4o11.retornarCarro(2);
            p4o11.escribir("Atentamente,");
            p4o11.retornarCarro(2);
            Oracion p4o12 = new Oracion(p4, 10, true, "ENEL Distribución Perú S.A.A.");
            p4o12.retornarCarro(4); // 3, para colocar el código de barras dentro de la franja del aviso

            Parrafo parrafoCodBar = new Parrafo(documento);
//            pCBo1.retornarCarro(2);
            Oracion pCBo1 = new Oracion(parrafoCodBar, 6, "LSF");
            pCBo1.tabular(3);
            pCBo1.escribir("2391829");
            pCBo1.retornarCarro();
            pCBo1.escribir("MUNICIPALIDAD METROPOLITANA DE LIMA"); //máximo 1 línea, 40 caracteres aprox. TODO calcular cant de caracteres máx por línea
            pCBo1.retornarCarro();
            pCBo1.escribir("AV. ABANCAY ESQ. JR. UCAYALI"); //máximo 3 líneas, 120 caracteres aprox. TODO calcular cant de caracteres máx por línea
            pCBo1.retornarCarro();
            pCBo1.escribir("LIMA CERCADO");
            pCBo1.retornarCarro();
//            pCBo1.getRun().addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, "codBar", Units.toEMU(128), Units.toEMU(13)); //308 * 60 (ORIGINAL 128x13)
//            pCBo1.retornarCarro();
            pCBo1.tabular();
            pCBo1.escribir("       0012300019999");
            pCBo1.retornarCarro(5); //TODO Condicionar las veces a la cantidad de lineas que ocupa el nombre de cliente y la direccion

//            Parrafo p5 = new Parrafo(documento, ParagraphAlignment.CENTER);
            Parrafo p5 = new Parrafo(documento);

            Oracion p5o1 = new Oracion(parrafoCodBar, 11, true, "");
//            p5o1.retornarCarro();
            p5o1.getRun().addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, "codBar", Units.toEMU(128), Units.toEMU(13)); //308 * 60 (ORIGINAL 128x13)

//            Oracion p5o2 = new Oracion(p5, 11, true, "");
            p5o1.tabular(2);
//            p5o1.retornarCarro();
            p5o1.escribir("AVISO DE CONTRASTE POR NTCSE");
//            p5o1.retornarCarro();
            Oracion p5o3 = new Oracion(p5, 9, true, "");
            p5o3.tabular(6);
            p5o3.escribir("CARGO DE RECEPCION");

            //COLOCAR CODIGO DE BARRAS


            Parrafo p6 = new Parrafo(documento, ParagraphAlignment.BOTH);

            Oracion p6o1 = new Oracion(p6, "");
            p6o1.retornarCarro();
            p6o1.escribir("Cliente N°");
            p6o1.tabular();
            p6o1.escribir(": ");

            Oracion p6o2 = new Oracion(p6, 10, true, "2391829");
            p6o2.tabular(7);
            p6o2.escribir("        ");
            p6o2.tabular();

            Oracion p6o3 = new Oracion(p6, "Ruta");

            Oracion p6o4 = new Oracion(p6, 10, true, " : 23-311-0122");
            p6o4.retornarCarro();

            Oracion p6o5 = new Oracion(p6, "");
            p6o5.escribir("Nombre ");
            p6o5.tabular(2);
            p6o5.escribir(": ");

            Oracion p6o6 = new Oracion(p6, 10, true, "MUNICIPALIDAD METROPOLITANA DE LIMA");
            p6o6.retornarCarro();

            Oracion p6o7 = new Oracion(p6, "");
            p6o7.escribir("Dirección ");
            p6o7.tabular();
            p6o7.escribir(": ");

            Oracion p6o8 = new Oracion(p6, 10, true, "AV. ABANCAY ESQ. JR. UCAYALI");

            Parrafo p7 = new Parrafo(documento, ParagraphAlignment.BOTH, 900, 550);
            Oracion p7o1 = new Oracion(p7, 10, true, "  LIMA CERCADO");

            Parrafo p8 = new Parrafo(documento, ParagraphAlignment.BOTH);
            Oracion p8o1 = new Oracion(p8, "");
            p8o1.retornarCarro();
            p8o1.escribir("Estimado Cliente:");
            p8o1.retornarCarro();

            Oracion p8o2 = new Oracion(p8, 4, "");
            p8o2.retornarCarro();

            Oracion p8o3 = new Oracion(p8, "Hacemos de su conocimiento que de acuerdo con lo dispuesto en la " +
                    "Norma Técnica de Calidad de los Servicios Eléctricos – NTCSE, aprobado según D.S. N° 020-97-EM " +
                    "y la Directiva de Contrastación de Medidores de Energía Activa y Reactiva e Indicadores de " +
                    "Máxima Demanda aprobado según R.M. N° 496-2005-MEM/DM; ENEL Distribución Perú S.A.A. viene " +
                    "ejecutando pruebas de contraste a una muestra de su zona de concesión. ");
            p8o3.retornarCarro();

            Oracion p8o4 = new Oracion(p8, 4, "");
            p8o4.retornarCarro();

            Oracion p8o5 = new Oracion(p8, "Al salir sorteado su suministro, le agradeceremos brinde las " +
                    "facilidades de acceso a la empresa SERVCON S.A. a su medidor el día 11/01/2023, a las 9:00 a.m. " +
                    "aproximadamente, momento en que lo visitaran para efectuar las pruebas mencionadas. En caso de " +
                    "que su medidor no cumpla con los valores de error indicados en la Directiva de Contraste antes " +
                    "mencionada, estaremos procediendo a cambiar su equipo de medida SIN COSTO alguno para usted, "  +
                    "dentro de las siguientes 48 horas de efectuada la prueba de contraste.");
            p8o5.retornarCarro(2);
            p8o5.escribir("Recibido por : ………………..………………………………............................................");
            p8o5.tabular();
            p8o5.escribir("Firma : …………..……….………….");
            p8o5.retornarCarro();

            Oracion p8o6 = new Oracion(p8, 6, "");
            p8o6.retornarCarro();

            Oracion p8o7 = new Oracion(p8, "D.N.I. : ………………..………….……");
            p8o7.tabular();
            p8o7.escribir("    Parentesco : ………………………………… ");
            p8o7.tabular();
            p8o7.escribir("Fecha : ...…………………….………");
            p8o7.retornarCarro();

            Oracion p8o8 = new Oracion(p8, 6, "");
            p8o8.retornarCarro();

            Oracion p8o9 = new Oracion(p8, "Observaciones");
            p8o9.tabular();
            p8o9.escribir(": ………………..…………………………………….....................................…………...………..……………….");
            p8o9.retornarCarro();
            
            Parrafo p9 = new Parrafo(documento);
            Oracion p9o1 = new Oracion(p9, "Medidor : ");
            Oracion p9o2 = new Oracion(p9, 10, true, "33687     ");
            Oracion p9o3 = new Oracion(p9, "Marca : ");
            Oracion p9o4 = new Oracion(p9, 10, true, "SNX     ");
            Oracion p9o5 = new Oracion(p9, "SED : ");
            Oracion p9o6 = new Oracion(p9, 10, true, "00242S     - ");
            Oracion p9o7 = new Oracion(p9, "COORDENADAS : LAT ");
            Oracion p9o8 = new Oracion(p9, 10, true, "-12.04982311 / ");
            Oracion p9o9 = new Oracion(p9, "LONG");
            Oracion p9o10 = new Oracion(p9, 10, true, " -77.02750518");
            p9o10.getRun().addBreak(BreakType.PAGE);

            documento.write(out);
//        out.close();
            System.out.println("NTCSE.docx written successully");

        } catch (IOException ioe) {
            //        out.close();
        }
    }

}
