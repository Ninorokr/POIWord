package com.silverlink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.silverlink.queriers.Querier.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static{
        Datasource.open();
    }

    public static void main(String[] args){

        menuPrincipal(scanner);

//        ResultSet rs = null;

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

    public static void menuPrincipal(Scanner scanner){
        System.out.println("1 - Ingresar datos\n" +
                           "2 - Exportar\n" +
                            "9 - Salir");

        int choice = scanner.nextInt();
        switch(choice){
            case 1: menuIngresarDatos(scanner); break;
            case 2: ;break;
            case 9: System.exit(0);
        }
    }

    public static void menuIngresarDatos(Scanner scanner){
        ingresarDatosGenerales(scanner);


//        revisarListadoDeAvisos(scanner.nextLine());
    }

    public static void ingresarDatosGenerales(Scanner scanner){
        int numServicio = elegirServicio(scanner);
        String idCECO = elegirCECO(scanner, numServicio);
        int numUsuario = elegirUsuario(scanner, idCECO);
        String descripcionOS = descripcionOS(scanner);
        boolean esNTSCE = esNTSCE(scanner);
        boolean soloImpresion = soloImpresion(scanner);

        //TODO agrupar en una clase OS (Orden de Servicio) e ingresar a BD
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

    //TODO programar errores y contenciones

    private static int elegirServicio(Scanner scanner){
        HashMap<Integer, String> servicios = queryServicios();
        System.out.println("Elegir el servicio:");
        servicios.forEach((key, value) -> System.out.println(key + ". " + value));
        return scanner.nextInt();
    }

    public static String elegirCECO(Scanner scanner, int servicio){
        ArrayList<CECO> CECOs = queryCECOsPorServicio(servicio);
        System.out.println("Elegir el CECO:");
        for(int i = 0; i < CECOs.size(); i++)
            System.out.println(i+1 + ". " + CECOs.get(i).getIdCECO() + " | " + CECOs.get(i).getNomCECO());
        return CECOs.get(scanner.nextInt()-1).getIdCECO();
    }

    public static int elegirUsuario(Scanner scanner, String numCECO){
        HashMap<Integer, String> servicios = queryUsuariosPorCECO(numCECO);
        System.out.println("Elegir el usuario:");
        servicios.forEach((key, value) -> System.out.println(key + ". " + value));
        return scanner.nextInt();
    }
}
