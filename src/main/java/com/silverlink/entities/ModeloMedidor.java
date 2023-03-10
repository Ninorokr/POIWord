package com.silverlink.entities;

import static com.silverlink.Main.marcaMedidores;
import static com.silverlink.Main.modelosMedidor;

public class ModeloMedidor {
    private short idMarcaMedidor;
    private short idModeloMedidor;
    private String nomModeloMedidor;

    public ModeloMedidor(short idMarcaMedidor, short idModeloMedidor, String nomModeloMedidor) {
        this.idMarcaMedidor = idMarcaMedidor;
        this.idModeloMedidor = idModeloMedidor;
        this.nomModeloMedidor = nomModeloMedidor;
    }

    public short getIdMarcaMedidor() {
        return idMarcaMedidor;
    }

    public void setIdMarcaMedidor(short idMarcaMedidor) {
        this.idMarcaMedidor = idMarcaMedidor;
    }

    public short getIdModeloMedidor() {
        return idModeloMedidor;
    }

    public void setIdModeloMedidor(short idModeloMedidor) {
        this.idModeloMedidor = idModeloMedidor;
    }

    public String getNomModeloMedidor() {
        return nomModeloMedidor;
    }

    public void setNomModeloMedidor(String nomModeloMedidor) {
        this.nomModeloMedidor = nomModeloMedidor;
    }

    public static ModeloMedidor existeModeloMedidor(String nomModeloMedidor){
        for(ModeloMedidor modelo : modelosMedidor){
            if(modelo.getNomModeloMedidor().equals(nomModeloMedidor))
                return modelo;
        }
        return null;
    }
}
