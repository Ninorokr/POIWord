package com.silverlink;

import com.silverlink.entities.AvisoContraste;
import com.silverlink.entities.OrdenServicio;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.*;

import static com.silverlink.ExcelGatherer.getCellValueAsString;
import static com.silverlink.Main.scanner;

public class Directrices {

    //Todos los campos relevantes
    static String[] campos = {"Correlativo", "Cliente", "Nombre", "Dirección", "Distrito", "Sucursal", "Sector", "Zona",
            "Corelativo2", "Promedio", "Latitud", "Longitud", "SET", /*"Programado",*/ "Marca", "Modelo", "Medidor", "Fase",
            "Año Fab", "Empresa", "SED", /*"Semana",*/ "Fecha1", "Fecha2", "Hora", "Patrón", "Carga", "DNITec", "ApellidoTec", "NombreTec"};

    static ArrayList<Integer> numsColumna = new ArrayList<>();
//    static boolean[] colsPresentes = new boolean[28];

    public static void organizeData(XSSFSheet sheet){
        int columnasEnExcel = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i < campos.length; i++) {
            printArray(campos, i);

            for(int j = 0; j < columnasEnExcel; j++) {
                if(numsColumna.contains(j)){
                    continue;
                }
                System.out.print(j + 1 + ". {");
                for (int k = 0; k < 4; k++)
                    System.out.print(getCellValueAsString(sheet.getRow(k).getCell(j)) + ", ");
                System.out.println("...}");
            }

            numsColumna.add(scanner.nextInt()-1);
//            if(numsColumna.get(i) != -1)
//                colsPresentes[i] = true;
//            System.out.println("Campo: " + campos[i] + " | Nro. de columna: " + numsColumna.get(i) + " | Presente: ");
        }

//        for (int j = 0; j < campos.length; j++) {
//            System.out.println("Campo: " + campos[j] + " | Nro. de columna: " + numsColumna.get(j) + " | Presente: ");
//        }
    }

    private static void printArray(String[] array, int i){
        System.out.print("Elige la columna que corresponde a: (0 si no existe)");
        for (int j = i; j < array.length; j++) {
            System.out.print(array[j] + ", ");
        }
        System.out.println();
    }

    public static void campoCorrelativo(XSSFSheet sheet, OrdenServicio os, AvisoContraste aviso){

    }
}
