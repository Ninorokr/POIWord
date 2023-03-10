package com.silverlink.entities;

import static com.silverlink.Main.SETs;

public class SET {
    private short idSET;
    private String codSET;
    private String nomSET;

    public SET(short idSET, String codSET, String nomSET) {
        this.idSET = idSET;
        this.codSET = codSET;
        this.nomSET = nomSET;
    }

    public short getIdSET() {
        return idSET;
    }
    public void setIdSET(short idSET) {
        this.idSET = idSET;
    }
    public String getCodSET() {
        return codSET;
    }
    public void setCodSET(String codSET) {
        this.codSET = codSET;
    }
    public String getNomSET() {
        return nomSET;
    }
    public void setNomSET(String nomSET) {
        this.nomSET = nomSET;
    }

    public static SET existeSET(String codSET){
        for(SET set : SETs){
            if(set.getCodSET().equals(codSET))
                return set;
        }
        return null;
    }

}
