package com.silverlink.entities;

public class ModeloMedidor {
    private short idMarcaMedidor;
    private short idModeloMedidor;
    private String codModeloMedidor;

    public ModeloMedidor(short idMarcaMedidor, short idModeloMedidor, String codModeloMedidor) {
        this.idMarcaMedidor = idMarcaMedidor;
        this.idModeloMedidor = idModeloMedidor;
        this.codModeloMedidor = codModeloMedidor;
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

    public String getCodModeloMedidor() {
        return codModeloMedidor;
    }

    public void setCodModeloMedidor(String codModeloMedidor) {
        this.codModeloMedidor = codModeloMedidor;
    }
}
