package com.silverlink;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.silverlink.Datasource.conn;
import static com.silverlink.Revisor.revisarListadoDeAvisos;

public class Main {

    public static void main(String[] args){

        Datasource.open();
        Scanner scanner = new Scanner(System.in);
        menuIngresarDatos(scanner);


        ResultSet rs = null;

//        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM tblPlazoEntrega ORDER BY idPlazoEntrega ASC")){
//            rs = ps.executeQuery();
//            System.out.println("idPlazoEntrega | nomPlazoEntrega");
//            while(rs.next()){
//                System.out.println(rs.getInt(1) + " | " + rs.getString(2));
//            }
//
//        } catch (SQLException sqle){
//            sqle.printStackTrace();
//        }


//        NTSCE ntsce = new NTSCE();
//        ntsce.generar();

//        ExcelGatherer eg = new ExcelGatherer();
//        eg.extract("D:\\Apache POI test\\BVERITAS_AV02-23.xlsx");


    }

    public static void menuPrincipal(){
        System.out.println("1 - Ingresar datos\n" +
                           "2 - Exportar ");
    }

    public static void menuIngresarDatos(Scanner scanner){
        ingresarDatosGenerales(scanner);


        revisarListadoDeAvisos(scanner.nextLine());
    }

    public static void ingresarDatosGenerales(Scanner scanner){

        boolean esNTSCE = esNTSCE(scanner);
        boolean soloImpresion = soloImpresion(scanner);
        String descripcionOS = descripcionOS(scanner);
        int numCECO = elegirCECO(scanner);
        int numUsuario = elegirUsuario(scanner);

//        System.out.println("Ingresar ruta de archivo:");

    }

    public static boolean esNTSCE(Scanner scanner){
        boolean esNTSCE = false;
        boolean NTSCEflag = false;
        int rptaNTSCE = 0;

        while(!NTSCEflag){
            System.out.println("¿Es NTSCE? 1 = Sí | 2 = No | 9 = Salir");
            boolean inputFlag = false;
            while(!inputFlag) {
                try{
                    rptaNTSCE = scanner.nextInt(); //Capturar el nro de rptaNTSCE del usuario
                } catch (InputMismatchException ime) {
                    System.out.println("Sólo se aceptan los números 1 y 2");
                }
                inputFlag = true;
            }

            switch(rptaNTSCE){
                case 1 : esNTSCE = true; NTSCEflag = true; break;
                case 2 : /*esNTSCE = false;*/ NTSCEflag = true; break;
                case 9 : System.exit(0);
                default:
                    System.out.println("Valor incorrecto");
            }
        }
        return esNTSCE;
    }

    public static boolean soloImpresion(Scanner scanner){
        boolean soloImpresion = false;
        boolean soloImpresionFlag = false;
        int rptaSoloImpresion = 0;

        while(!soloImpresionFlag){
            System.out.println("¿Es sólo impresión? 1 = Sí | 2 = No | 9 = Salir");
            boolean inputFlag2 = false;
            while(!inputFlag2) {
                try{
                    rptaSoloImpresion = scanner.nextInt(); //Capturar el nro de rptaNTSCE del usuario
                } catch (InputMismatchException ime) {
                    System.out.println("Sólo se aceptan los números 1 y 2");
                }
                inputFlag2 = true;
            }

            switch(rptaSoloImpresion){
                case 1 : soloImpresion = true; soloImpresionFlag = true; break;
                case 2 : /*soloImpresion = false;*/ soloImpresionFlag = true; break;
                case 9 : System.exit(0);
                default:
                    System.out.println("Valor incorrecto");
            }
        }

        return soloImpresion;
    }

    public static String descripcionOS(Scanner scanner){
        System.out.println("Ingrese la descripción del servicio");
        return scanner.nextLine();
    }

    public static void elegirCECO(Scanner scanner){
        queryCECOs();
    }
}
