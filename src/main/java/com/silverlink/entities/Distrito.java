package com.silverlink.entities;

import java.text.Normalizer;
import java.util.Collections;

import static com.silverlink.Main.distritos;
import static com.silverlink.Main.scanner;

public class Distrito implements Comparable<Distrito>{

    private short idPais;
    private short idDepartamento;
    private short idProvincia;
    private short idDistrito;
    private String nomDistrito;

    public Distrito(short idPais, short idDepartamento, short idProvincia, short idDistrito, String nomDistrito) {
        this.idPais = idPais;
        this.idDepartamento = idDepartamento;
        this.idProvincia = idProvincia;
        this.idDistrito = idDistrito;
        this.nomDistrito = nomDistrito;
    }

    public short getIdPais() {
        return idPais;
    }
    public short getIdDepartamento() {
        return idDepartamento;
    }
    public short getIdProvincia() {
        return idProvincia;
    }
    public short getIdDistrito() {
        return idDistrito;
    }
    public String getNomDistrito() {
        return nomDistrito;
    }

    @Override
    public int compareTo(Distrito distrito) {
        return this.nomDistrito.compareTo(distrito.getNomDistrito());
    }

    public static Distrito descifrarDistrito(String distritoExcel){
        for (Distrito distBD : distritos) {
            //Comparar distrito de Excel sin tildes con distrito de BD sin tildes
            String distBDSinTilde = Normalizer.normalize(distBD.getNomDistrito(), Normalizer.Form.NFD);
            String distExcelSinTilde = Normalizer.normalize(distritoExcel, Normalizer.Form.NFD);
            distExcelSinTilde = distExcelSinTilde.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            if (distExcelSinTilde.equalsIgnoreCase(distBDSinTilde))
                return distBD;
        }

        Collections.sort(distritos);
//        for (Distrito dist : distritos) {
//            System.out.println(dist);
//        }
        for (int i = 0; i < distritos.size(); i++) {
            System.out.println(i + ". " + distritos.get(i).getNomDistrito());
        }
//
//        Scanner scanner = new Scanner(System.in);
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
        return distritos.get(numero);
    }

    @Override
    public String toString() {
        return this.getIdDepartamento() + " | " + this.getIdDistrito() + ": " + this.getNomDistrito();
    }
}
