package com.silverlink.utils;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Oracion {

    private String tipoDeLetra;
    private int tamañoLetra;
    private boolean negrita;
    private boolean cursiva;
    private boolean subrayado;
    private String colorResaltador;
    private String texto;
    
    private XWPFRun oracion;

    //Se asume el formato estándar de texto
    public Oracion(Parrafo parrafo, String texto) {
        oracion = parrafo.getParagraph().createRun();
        oracion.setFontFamily("Arial Narrow");
        oracion.setFontSize(10);
        oracion.setText(texto);
    }

    public Oracion(Parrafo parrafo, int tamañoLetra, String texto) {
        this(parrafo, texto);
//        oracion = parrafo.getParagraph().createRun();
//        oracion.setFontFamily("Arial Narrow");
        oracion.setFontSize(tamañoLetra);
//        oracion.setText(texto);
    }

    public Oracion(Parrafo parrafo, int tamañoLetra, boolean negrita, String texto) {
        this(parrafo, tamañoLetra, texto);
//        oracion = parrafo.getParagraph().createRun();
//        oracion.setFontFamily("Arial Narrow");
//        oracion.setFontSize(tamañoLetra);
        oracion.setBold(negrita);
//        oracion.setText(texto);
    }

    public Oracion(Parrafo parrafo, int tamañoLetra, boolean negrita, String colorResaltador, String texto) {
        this(parrafo, tamañoLetra, negrita, texto);
//        oracion = parrafo.getParagraph().createRun();
//        oracion.setFontFamily("Arial Narrow");
//        oracion.setFontSize(tamañoLetra);
//        oracion.setBold(negrita);
        oracion.setTextHighlightColor(colorResaltador);
//        oracion.setText(texto);
    }

    public Oracion getOracion(){
        return this;
    }

    public XWPFRun getRun(){
        return oracion;
    }

    public void escribir(String texto){
        oracion.setText(texto);
    }

    public void tabular(){
            oracion.addTab();
    }
    public void tabular(int veces){
        for (int i = 0; i < veces; i++) {
            oracion.addTab();
        }
    }

    public void retornarCarro(){
            oracion.addCarriageReturn();
    }
    public void retornarCarro(int veces){
        for (int i = 0; i < veces; i++) {
            oracion.addCarriageReturn();
        }
    }


}
