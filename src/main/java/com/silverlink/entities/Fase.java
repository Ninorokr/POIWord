package com.silverlink.entities;

import static com.silverlink.Main.fases;

public class Fase {

    private byte idFase;
    private String codFase;
    private String nomFase;

    public Fase(byte idFase, String codFase, String nomFase) {
        this.idFase = idFase;
        this.codFase = codFase;
        this.nomFase = nomFase;
    }

    public byte getIdFase() {
        return idFase;
    }
    public void setIdFase(byte idFase) {
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

    public static Fase existeFase(String codFase){
        for(Fase fase : fases){
            if(fase.getCodFase().equals(codFase))
                return fase;
        }
        return null;
    }

    @Override
    public String toString() {
        return "idFase: " + idFase +
                " | codFase: " + codFase +
                " | nomFase: " + nomFase;
    }
}
