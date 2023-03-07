package com.silverlink.entities;

public class Fase {

    private short idFase;
    private String codFase;
    private String nomFase;

    public Fase(short idFase, String codFase, String nomFase) {
        this.idFase = idFase;
        this.codFase = codFase;
        this.nomFase = nomFase;
    }

    public short getIdFase() {
        return idFase;
    }

    public void setIdFase(short idFase) {
        this.idFase = idFase;
    }

    public String getCodFase() {
        return codFase;
    }

    public void setCodFase(String codFase) {
        this.codFase = codFase;
    }

    public String getNomFase() {
        return nomFase;
    }

    public void setNomFase(String nomFase) {
        this.nomFase = nomFase;
    }
}
