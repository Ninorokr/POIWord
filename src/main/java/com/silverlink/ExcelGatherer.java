package com.silverlink;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExcelGatherer {

    Scanner scanner = new Scanner(System.in);

    public void intro(){
        System.out.println("Escribe la ruta del archivo a extraer");
    }

    public void extract(String excelFile){
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
            organizeData(sheet, scanner);

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    * En caso de que el excel tenga varias hojas, se preguntará al usuario cual de las hojas contiene la data a extraer
    * */
    public int chooseSheet(XSSFWorkbook wb, int sheetsQty, Scanner scanner){
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

    public ArrayList<Integer> organizeData(XSSFSheet sheet, Scanner scanner){
        //Los campos definidos en la base de datos
//        String[] camposGrales = {"Programado", "Empresa", "Semana"};
        String[] campos = {"Correlativo", "Cliente", "Nombre", "Dirección", "Distrito", "Sucursal", "Sector", "Zona",
                "Corelativo2", "Promedio", "Latitud", "Longitud", "SET", "Programado", "Marca", "Modelo", "Medidor", "Fase",
                "Año Fab", "Empresa", "SED", "Semana", "Fecha", "Hora", "Patrón", "Carga", "DNI", "ApellidoTec", "NombreTec"};

        int columns = sheet.getRow(0).getLastCellNum();

        ArrayList<Integer> choices = new ArrayList<>();
        for (String campo: campos) {
            System.out.println("Elige el campo que corresponde a: " + campo);

            for(int i = 0; i < columns; i++){
                if(choices.contains(i))
                    continue;
                System.out.print(i + ". {");
                for(int j = 0; j < 4; j++)
                    System.out.print(getCellValueAsString(sheet.getRow(j).getCell(i)) + ", ");
                System.out.println("...}");
            }
            choices.add(scanner.nextInt());
            System.out.print("\033[H\033[2J");
        }

        return choices;
    }

    public String getCellValueAsString(XSSFCell cell){
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

}
