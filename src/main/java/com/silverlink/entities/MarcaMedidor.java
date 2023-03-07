package com.silverlink.entities;

public class MarcaMedidor {

    private short idMarcaMedidor;
    private String codMarcaMedidor;
    private String nomMarcaMedidor;

    public MarcaMedidor(short idMarcaMedidor, String codMarcaMedidor, String nomMarcaMedidor) {
        this.idMarcaMedidor = idMarcaMedidor;
        this.codMarcaMedidor = codMarcaMedidor;
        this.nomMarcaMedidor = nomMarcaMedidor;
    }

    public short getIdMarcaMedidor() {
        return idMarcaMedidor;
    }

    public void setIdMarcaMedidor(byte idMarcaMedidor) {
        this.idMarcaMedidor = idMarcaMedidor;
    }

    public String getCodMarcaMedidor() {
        return codMarcaMedidor;
    }

    public void setCodMarcaMedidor(String codMarcaMedidor) {
        this.codMarcaMedidor = codMarcaMedidor;
    }

    public String getNomMarcaMedidor() {
        return nomMarcaMedidor;
    }

    public void setNomMarcaMedidor(String nomMarcaMedidor) {
        this.nomMarcaMedidor = nomMarcaMedidor;
    }
}
