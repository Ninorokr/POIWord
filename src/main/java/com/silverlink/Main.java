package com.silverlink;

import com.silverlink.entities.*;
import com.silverlink.utils.Datasource;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.silverlink.ProcesadorDatos.RecopilarEInsertarDatos;
import static com.silverlink.queriers.Commander.crearNuevaOS;
import static com.silverlink.queriers.Querier.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    static {
        Datasource.open();

    }
    public static ArrayList<Distrito> distritos = queryDistritosLimaYCallao();
    public static ArrayList<Sucursal> sucursales = querySucursales();
    public static ArrayList<SET> SETs = querySETs();
    public static ArrayList<MarcaMedidor> marcaMedidores = queryMarcaMedidores();
    public static ArrayList<ModeloMedidor> modelosMedidor = queryModelosMedidor();
    public static ArrayList<Fase> fases = queryFases();
    public static ArrayList<EmpresaContrastadora> empresasContrastadoras =  queryEmpresasContrastadoras();
    public static ArrayList<PersonalContrastador> listaPersonalContrastador = queryPersonalContrastador();

    public static void main(String[] args){
        menuPrincipal(scanner);
    }

    public static void menuPrincipal(Scanner scanner){
        System.out.println("1 - Ingresar datos\n" +
                           "2 - Exportar\n" +
                            "9 - Salir");

        int choice = scanner.nextInt();
        switch(choice){
            case 1: menuIngresarDatos(scanner); break;
            case 2: //;break;
            case 9: System.exit(0);
        }
    }

    public static void menuIngresarDatos(Scanner scanner){
        System.out.println("Cargando...");
        ingresarDatosGenerales(scanner);
    }

    public static void ingresarDatosGenerales(Scanner scanner){
        int numServicio = 1; //1 - Reparto de avisos de contraste //elegirServicio(scanner);
        String idCECO = elegirCECO(scanner, numServicio);
        int idUsuario = elegirUsuario(scanner, idCECO);
        String descripcionOS = descripcionOS(scanner);
        boolean esNTSCE = esNTSCE(scanner);
        boolean soloImpresion = soloImpresion(scanner);
        boolean esAlterno = esAlterno(scanner);

        OrdenServicio os = new OrdenServicio(numServicio, descripcionOS, idCECO, idUsuario, esNTSCE, soloImpresion, esAlterno);

        crearNuevaOS(os);
        RecopilarEInsertarDatos(os);

//        crearOS(os);
        //CONFLICTO: Qué pasa si se crea la OS y falla el análisis del Excel, no se puede volver a crear la OS
        //Se tendría que utilizar la OS ya creada, sugerencia: crear un nuevo método que use una OS ya creada.
        //Y asegurar que la OS no esté ocupada

        //POSIBLE SOLUCION: INVERTIR el orden de las tareas
        //PRIMERO: Dar alguna info sobre la OS en general (esNTSCE, esAlterno, soloImpresion)
        //SEGUNDO: Analizar los datos del Excel
        //TERCERO: Ingresar a la BD
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

    public static boolean esAlterno(Scanner scanner){
        boolean esAlterno = false;
        boolean esAlternoFlag = false;
        int rptaEsAlterno = 0;

        while(!esAlternoFlag){
            System.out.println("¿Es alterno? 1 = Sí | 2 = No | 9 = Salir");
            boolean inputFlag2 = false;
            while(!inputFlag2) {
                try{
                    rptaEsAlterno = scanner.nextInt(); //Capturar el nro de rptaNTSCE del usuario
                } catch (InputMismatchException ime) {
                    System.out.println("Sólo se aceptan los números 1 y 2");
                }
                inputFlag2 = true;
            }

            switch(rptaEsAlterno){
                case 1 : esAlterno = true; esAlternoFlag = true; break;
                case 2 : /*soloImpresion = false;*/ esAlternoFlag = true; break;
                case 9 : System.exit(0);
                default:
                    System.out.println("Valor incorrecto");
            }
        }
        return esAlterno;
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
        scanner.nextLine(); //Line handler
        return scanner.nextLine();
    }

    /*TODO programar errores y contenciones*/

    public static String elegirCECO(Scanner scanner, int servicio){
        ArrayList<CECO> CECOs = queryCECOsPorServicio(servicio);
        if(CECOs.size() == 1) return CECOs.get(0).getIdCECO();
        System.out.println("Elegir el CECO:");
        for(int i = 0; i < CECOs.size(); i++)
            System.out.println(i+1 + ". " + CECOs.get(i).getIdCECO() + " | " + CECOs.get(i).getNomCECO());
        return CECOs.get(scanner.nextInt()-1).getIdCECO();
    }

    public static int elegirUsuario(Scanner scanner, String numCECO){
        ArrayList<UsuarioENEL> usuariosENEL = queryUsuariosPorCECO(numCECO);
        if(usuariosENEL.size() == 1) return 1;
        System.out.println("Elegir el usuario:");
        for(int i = 0; i < usuariosENEL.size(); i++)
            System.out.println(i+1 + ". " + /*usuariosENEL.get(i).getIdUsuario() + " | " + */usuariosENEL.get(i).getNomUsuarioENEL());
        return usuariosENEL.get(scanner.nextInt()-1).getIdUsuarioENEL();
    }
}
