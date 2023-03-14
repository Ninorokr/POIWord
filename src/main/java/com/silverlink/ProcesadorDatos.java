package com.silverlink;

import com.silverlink.entities.AvisoContraste;
import com.silverlink.entities.OrdenServicio;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static com.silverlink.Main.scanner;
import static com.silverlink.queriers.Commander.insertAvisoContrasteToDB;
import static com.silverlink.queriers.Commander.idAvisoContraste;

public class ProcesadorDatos {

    public static void RecopilarEInsertarDatos(OrdenServicio os){
        String rutaArchivoExcel = ingresarRutaDeArchivo();
        XSSFSheet sheet = obtenerHojaDeExcel(rutaArchivoExcel);
        ArrayList<Integer> elecciones = elegirColumnasConDatos(sheet);
        ArrayList<AvisoContraste> avisos = recorrerDatos(sheet, elecciones);
        insertarAvisosABD(os, avisos);
    }

    public static String ingresarRutaDeArchivo(){
        //TODO ingresar la ruta correctamente la primera vez, sino enviar una linea cualquiera antes de volver a ingresar
        boolean flag = false;
        String rutaArchivoExcel = null;

        while(!flag){
            System.out.println("Indicar ruta de archivo:");
            scanner.nextLine(); //Line handler
            rutaArchivoExcel = scanner.nextLine();
            File file = new File(rutaArchivoExcel);
            flag = file.exists();
            if(!flag){
                System.out.println("Ruta incorrecta");
//                scanner.nextLine(); //Line handler
            }
        }
        return rutaArchivoExcel;
    }

    public static XSSFSheet obtenerHojaDeExcel(String excelFile){
        ZipSecureFile.setMinInflateRatio(0);

        try(XSSFWorkbook wb = new XSSFWorkbook(excelFile)){
            XSSFSheet sheet;

            int sheetsQty = wb.getNumberOfSheets();
            if(sheetsQty == 1){
                sheet = wb.getSheetAt(0);
            }else{
                int choice = elegirHoja(wb, sheetsQty);
                if(choice == 9430) //Si 9431, salir de aplicación
                    System.exit(0);
                sheet = wb.getSheetAt(choice);
            }
            return sheet;
        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static int elegirHoja(XSSFWorkbook wb, int sheetsQty){
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

    public static ArrayList<Integer> elegirColumnasConDatos(XSSFSheet sheet){

        String[] campos = {"Correlativo", "Cliente", "Nombre", "Dirección", "Distrito", "Sucursal", "Sector", "Zona",
                "Corelativo2", "Promedio", "Latitud", "Longitud", "SET", "SED", /*"Programado",*/ "Marca", "Modelo", "Medidor", "Fase",
                "Año Fab", "Empresa", /*"Semana",*/ "Fecha1", "Fecha2", "Hora", "Patrón", "Carga", "DNITec", "ApellidoTec", "NombreTec"};

        boolean[] columnasPresentes = new boolean[28];
        for(boolean columna : columnasPresentes){
            columna = false;
        }

        int columnas = sheet.getRow(0).getLastCellNum();

        ArrayList<Integer> elecciones = new ArrayList<>();

        int k = -1;
        for (String campo : campos) {
            k++;
            System.out.println("Elige el campo que corresponde a: " + campo);

            for(int i = 0; i < columnas; i++){
                if(elecciones.contains(i))
                    continue;
                System.out.print(i+1 + ". {");
                for(int j = 0; j < 4; j++)
                    System.out.print(getCellValueAsString(sheet.getRow(j).getCell(i)) + ", ");
                System.out.println("...}");
            }
            int choice = scanner.nextInt();
            elecciones.add(choice-1);
            if(choice != 0)
                columnasPresentes[k] = true;
            System.out.print("\033[H\033[2J");
        }
//        for (boolean columna : columnasPresentes) {
//            System.out.print(columna + ", ");
//        }
        return elecciones;
    }

    public static ArrayList<AvisoContraste> recorrerDatos(XSSFSheet sheet, ArrayList<Integer> numsColumna){
        AvisoContraste aviso = null;
        ArrayList<AvisoContraste> avisos = new ArrayList<>();

            XSSFRow row;
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
             row = sheet.getRow(i);
             int numColumna;
             aviso = new AvisoContraste();
             for(int j = 0; j < 26; j++){
                 numColumna = numsColumna.get(j);
                 if(numColumna < 0) continue;
                 switch(j){
                     case 0: aviso.setNumCorrelativo(getCellValueAsString(row.getCell(numColumna))); break;
                     case 1: aviso.setNumCliente(getCellValueAsString(row.getCell(numColumna))); break;
                     case 2: aviso.setNomCliente(getCellValueAsString(row.getCell(numColumna))); break;
                     case 3: aviso.setDireccion(getCellValueAsString(row.getCell(numColumna))); break;
                     case 4: aviso.setDistrito(getCellValueAsString(row.getCell(numColumna))); break;
                     case 5: aviso.setIdSucursal(getCellValueAsString(row.getCell(numColumna))); break;
                     case 6: aviso.setNumSector(getCellValueAsString(row.getCell(numColumna))); break;
                     case 7: aviso.setNumZona(getCellValueAsString(row.getCell(numColumna))); break;
                     case 8: aviso.setNumCorrelativo2(getCellValueAsString(row.getCell(numColumna))); break;
                     case 9: aviso.setPromedio(row.getCell(numColumna).getNumericCellValue()); break;
                     case 10: aviso.setLatitud(row.getCell(numColumna).getNumericCellValue()); break;
                     case 11: aviso.setLongitud(row.getCell(numColumna).getNumericCellValue()); break;
                     case 12: aviso.setIdSET(getCellValueAsString(row.getCell(numColumna))); break;
                     case 13: aviso.setNumSEDyLetraSED(getCellValueAsString(row.getCell(numColumna))); break;
                     case 14: aviso.setIdMarcaMedidor(getCellValueAsString(row.getCell(numColumna))); break;
                     case 15: aviso.setIdModeloMedidor(getCellValueAsString(row.getCell(numColumna))); break;
                     case 16: aviso.setNumMedidor(getCellValueAsString(row.getCell(numColumna))); break;
                     case 17: aviso.setIdFase(getCellValueAsString(row.getCell(numColumna))); break;
                     case 18: aviso.setAnioFab(getCellValueAsString(row.getCell(numColumna))); break;
                     case 19: aviso.setIdEmpresaContrastadora(getCellValueAsString(row.getCell(numColumna))); break;
                     //TODO Cannot get a NUMERIC value from a STRING cell FechaContraste1 y 2
                     case 20: aviso.setFechaContraste1(LocalDate.from(row.getCell(numColumna).getLocalDateTimeCellValue())); break;
                     case 21: /*if(os.esAlterno() && !os.esNTSCE())*/
                         aviso.setFechaContraste2(LocalDate.from(row.getCell(numColumna).getLocalDateTimeCellValue()));
                     break;
                     case 22: aviso.setHora(LocalTime.from(row.getCell(numColumna).getLocalDateTimeCellValue())); break;
                     case 23: aviso.setPatron(getCellValueAsString(row.getCell(numColumna))); break;
                     case 24: aviso.setCarga(getCellValueAsString(row.getCell(numColumna))); break;
                     case 25: aviso.setPersonalContrastador(getCellValueAsString(row.getCell(numColumna)),
                                                        getCellValueAsString(row.getCell(numsColumna.get(26))),
                                                        getCellValueAsString(row.getCell(numsColumna.get(27)))); break;
                 }
             }
             avisos.add(aviso);
            System.out.println(i + ". " + aviso.getNumCorrelativo());
        }
        return avisos;
    }

    public static void insertarAvisosABD(OrdenServicio os, ArrayList<AvisoContraste> avisos){
        for(AvisoContraste aviso : avisos){
            idAvisoContraste = 0;
            insertAvisoContrasteToDB(os, aviso);
        }
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


}
