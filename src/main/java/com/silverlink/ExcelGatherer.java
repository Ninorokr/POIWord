package com.silverlink;

import com.silverlink.entities.AvisoContraste;
import com.silverlink.entities.PersonalContrastador;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static com.silverlink.Main.scanner;

public class ExcelGatherer {

    private static String excelFile;

    public static void start(){
        ingresarRutaDeArchivo();
        extract(excelFile);
    }

    public static boolean ingresarRutaDeArchivo(){
        //TODO ingresar la ruta correctamente la primera vez, sino enviar una linea cualquiera antes de volver a ingresar
        boolean flag = false;
        while(!flag){
            System.out.println("Indicar ruta de archivo:");
            scanner.nextLine(); //Line handler
            excelFile = scanner.nextLine();
            File file = new File(excelFile);
            flag = file.exists();
            if(!flag){
                System.out.println("Ruta incorrecta");
//                scanner.nextLine(); //Line handler
            }
        }
        return flag;
    }

    public static void extract(String excelFile){
        ZipSecureFile.setMinInflateRatio(0);

        try(XSSFWorkbook wb = new XSSFWorkbook(excelFile)){
            XSSFSheet sheet;

            int sheetsQty = wb.getNumberOfSheets();
            if(sheetsQty == 1){
                sheet = wb.getSheetAt(0);
            }else{
                int choice = chooseSheet(wb, sheetsQty, scanner);
                if(choice == 9430) //Si 9431, salir de aplicación
                    System.exit(0);
                sheet = wb.getSheetAt(choice);
            }
//            System.out.println("Hoja: \"" + sheet.getSheetName() + "\" | Filas: " + sheet.getLastRowNum());
            Directrices.organizeData(sheet);
//            organizeData(sheet, scanner);

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    * En caso de que el excel tenga varias hojas, se preguntará al usuario cual de las hojas contiene la data a extraer
    * */
    public static int chooseSheet(XSSFWorkbook wb, int sheetsQty, Scanner scanner){
        //Obtener y mostrar al cliente las hojas de Excel
        XSSFSheet sheet;
        int qty = 0;

        for(int i = 0; i < sheetsQty; i++) {
            sheet = wb.getSheetAt(i);
            qty = sheet.getLastRowNum()+1;
            System.out.println(i + 1 + ". " + sheet.getSheetName() + " | Filas: " +  qty);
        }

        int choice;

        while(true){
            System.out.println("Elige la hoja con data: ");
            choice = scanner.nextInt()-1;
            if(choice > -1 && choice < sheetsQty || choice == 9430)
                break;
        }

        return choice;

    }

    public void getData(XSSFSheet sheet, Scanner scanner){

    }

//      1 - Consultar data de la BD
//      2 - Verificar si existe en la BD
//          Si existe:
//          Si NO existe: Ingresar a la tabla correspondiente
//      3 - Almacenar dato para ingresar junto con todo lo demás

    public static ArrayList<Integer> organizeData(XSSFSheet sheet, Scanner scanner){
        //Los campos definidos en la base de datos
//        String[] camposGrales = {"Programado", "Empresa", "Semana"};
        String[] campos = {"Correlativo", "Cliente", "Nombre", "Dirección", "Distrito", "Sucursal", "Sector", "Zona",
                "Corelativo2", "Promedio", "Latitud", "Longitud", "SET", "SED", /*"Programado",*/ "Marca", "Modelo", "Medidor", "Fase",
                "Año Fab", "Empresa", /*"Semana",*/ "Fecha1", "Fecha2", "Hora", "Patrón", "Carga", "DNITec", "ApellidoTec", "NombreTec"};

        boolean[] columnasPresentes = new boolean[28];
        for(boolean columna : columnasPresentes){
            columna = false;
        }

        int columns = sheet.getRow(0).getLastCellNum();

        ArrayList<Integer> choices = new ArrayList<>();

        int k = -1;
        for (String campo : campos) {
            k++;
            System.out.println("Elige el campo que corresponde a: " + campo);

            for(int i = 0; i < columns; i++){
                if(choices.contains(i))
                    continue;
                System.out.print(i+1 + ". {");
                for(int j = 0; j < 4; j++)
                    System.out.print(getCellValueAsString(sheet.getRow(j).getCell(i)) + ", ");
                System.out.println("...}");
            }
            int choice = scanner.nextInt();
            choices.add(choice-1);
            if(choice != 0)
                columnasPresentes[k] = true;
            System.out.print("\033[H\033[2J");
        }
        for (boolean columna : columnasPresentes) {
            System.out.print(columna + ", ");
        }
        return choices;
    }

    public static String getCellValueAsString(XSSFCell cell){
        CellType type = cell.getCellType();
        try{
            switch(type){
                case BLANK: break;
                case ERROR: return cell.getErrorCellString();
                case STRING: return cell.getStringCellValue();
                case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
                case FORMULA: return cell.getCellFormula();
                case NUMERIC: return String.valueOf((int)(cell.getNumericCellValue()));
            }
        } catch (IllegalStateException ise){
            ise.printStackTrace();
        }
        return null;
    }

    public static void recorrerDatos(XSSFSheet sheet, ArrayList<Integer> numsColumna){
        AvisoContraste aviso = new AvisoContraste();

//        for (int i = 1; i < sheet.getLastRowNum(); i++) {
//            XSSFRow row = sheet.getRow(i);
//            for (int j = 0; j < 28; j++) {
//                switch(j){
//                    case 0: aviso.setNumCorrelativo(getCellValueAsString(row.getCell(numsColumna.get(j)))); break;//CORRELATIVO
//                    case 1:
//                }
//            }
//        }

        String[] campos = {"Hora", "Patrón", "Carga", "DNITec", "ApellidoTec", "NombreTec"};

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            aviso.setNumCorrelativo(getCellValueAsString(row.getCell(numsColumna.get(0))));
            aviso.setNumCliente(getCellValueAsString(row.getCell(numsColumna.get(1))));
            aviso.setNomCliente(getCellValueAsString(row.getCell(numsColumna.get(2))));
            aviso.setDireccion(getCellValueAsString(row.getCell(numsColumna.get(3))));
            aviso.setDistrito(getCellValueAsString(row.getCell(numsColumna.get(4))));
            aviso.setIdSucursal(getCellValueAsString(row.getCell(numsColumna.get(5))));
            aviso.setNumSector(getCellValueAsString(row.getCell(numsColumna.get(6))));
            aviso.setNumZona(getCellValueAsString(row.getCell(numsColumna.get(7))));
            aviso.setNumCorrelativo2(getCellValueAsString(row.getCell(numsColumna.get(8))));
            aviso.setPromedio(getCellValueAsString(row.getCell(numsColumna.get(9))));
            aviso.setLatitud(getCellValueAsString(row.getCell(numsColumna.get(10))));
            aviso.setLongitud(getCellValueAsString(row.getCell(numsColumna.get(11))));
            aviso.setIdSET(getCellValueAsString(row.getCell(numsColumna.get(12))));
            aviso.setNumSEDyLetraSED(getCellValueAsString(row.getCell(numsColumna.get(13))));
            aviso.setIdMarcaMedidor(getCellValueAsString(row.getCell(numsColumna.get(14))));
            aviso.setIdModeloMedidor(getCellValueAsString(row.getCell(numsColumna.get(15))));
            aviso.setNumMedidor(getCellValueAsString(row.getCell(numsColumna.get(16))));
            aviso.setIdFase(getCellValueAsString(row.getCell(numsColumna.get(17))));
            aviso.setAnioFab(getCellValueAsString(row.getCell(numsColumna.get(18))));
            aviso.setIdEmpresaContrastadora(getCellValueAsString(row.getCell(numsColumna.get(19))));
            aviso.setFechaContraste1(LocalDate.from(row.getCell(numsColumna.get(20)).getLocalDateTimeCellValue()));
            aviso.setFechaContraste2(LocalDate.from(row.getCell(numsColumna.get(21)).getLocalDateTimeCellValue()));
            aviso.setHora(getCellValueAsString(row.getCell(numsColumna.get(22))));
            aviso.setPatron(getCellValueAsString(row.getCell(numsColumna.get(23))));
            aviso.setCarga(getCellValueAsString(row.getCell(numsColumna.get(24))));
            aviso.setPersonalContrastador(getCellValueAsString(row.getCell(numsColumna.get(25))),
                            getCellValueAsString(row.getCell(numsColumna.get(26))),
                            getCellValueAsString(row.getCell(numsColumna.get(27))));
            //TODO Si el valor de numsColumna es -1, que el valor sea null y se almacene en la BD como null
        }
    }
}
