package com.silverlink.utils;

public class Parrafo {

    private String tipoDeLetra;
    private int tamañoLetra;
    private boolean negrita;
    private boolean cursiva;
    private boolean subrayado;
    private String texto;

    //Se asume el formato estándar de texto
    public Parrafo(String texto) {
        tipoDeLetra = "Arial Narrow";
        tamañoLetra = 10;
        this.texto = texto;
    }

    public Parrafo(int tamañoLetra, String texto) {
        tipoDeLetra = "Arial Narrow";
        this.tamañoLetra = tamañoLetra;
        this.texto = texto;
    }

    public Parrafo(int tamañoLetra, boolean negrita, String texto) {
        tipoDeLetra = "Arial Narrow";
        this.tamañoLetra = tamañoLetra;
        this.negrita = negrita;
        this.texto = texto;
    }


}
