package com.silverlink.entities;

import java.text.Normalizer;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.silverlink.queriers.Querier.queryAllDistritos;

public class Distrito implements Comparable<Distrito>{

    private int idPais;
    private int idDepartamento;
    private int idProvincia;
    private int idDistrito;
    private String nomDistrito;

    public Distrito(int idPais, int idDepartamento, int idProvincia, int idDistrito, String nomDistrito) {
        this.idPais = idPais;
        this.idDepartamento = idDepartamento;
        this.idProvincia = idProvincia;
        this.idDistrito = idDistrito;
        this.nomDistrito = nomDistrito;
    }

    public int getIdPais() {
        return idPais;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public String getNomDistrito() {
        return nomDistrito;
    }

    @Override
    public int compareTo(Distrito distrito) {
        return this.nomDistrito.compareTo(distrito.getNomDistrito());
    }

    public static int descifrarDistrito(){
        List<Distrito> distritos = queryAllDistritos();

        for (Distrito distBD : distritos) {
            //Primer intento: Comparar distrito de Excel con distrito de BD
            if (distritoExcel.equalsIgnoreCase(distBD.getNomDistrito()))
                return distBD.getIdDistrito();

            //Segundo intento: Comparar distrito de Excel con distrito de BD sin tildes
            String distBDSinTilde = Normalizer.normalize(distBD.getNomDistrito(), Normalizer.Form.NFD);
            distBDSinTilde = distBDSinTilde.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            if (distritoExcel.equalsIgnoreCase(distBDSinTilde))
                return distBD.getIdDistrito();

            //Tercer intento: Comparar distrito de Excel sin tildes con distrito de BD sin tildes
            String distExcelSinTilde = Normalizer.normalize(distritoExcel, Normalizer.Form.NFD);
            distExcelSinTilde = distExcelSinTilde.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            if (distExcelSinTilde.equalsIgnoreCase(distBDSinTilde))
                return distBD.getIdDistrito();

            //Adicional: Si distrito es "Carmen de la Legua Reynoso"
            if (distritoExcel.equalsIgnoreCase("CARMEN DE LA LEGUA"))
                return 31;
            if (distritoExcel.equalsIgnoreCase("LURIGANCHO CHOSICA"))
                return 47;
        }


        Collections.sort(distritos);
        for (Distrito dist : distritos) {
            System.out.println(dist);
        }

        Scanner scanner = new Scanner(System.in);
        byte numero;

        while (true) {
            System.out.println("¿Qué distrito es?: " + distritoExcel + ". Elige un número");
            if (scanner.hasNextByte()) {
                numero = scanner.nextByte();
                if (numero > 0 || numero < distritos.size())
                    break;
                else
                    System.out.println("Número fuera de rango.");
            } else {
                System.out.println("Entrada no válida");
            }
        }
        return numero;
    }
}
