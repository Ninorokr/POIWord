package com.silverlink.entities;

public class CECO {

    private int idServicio;
    private String idCECO;
    private String nomCECO;

    public CECO(int idServicio, String idCECO, String nomCECO) {
        this.idServicio = idServicio;
        this.idCECO = idCECO;
        this.nomCECO = nomCECO;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public String getIdCECO() {
        return idCECO;
    }

    public String getNomCECO() {
        return nomCECO;
    }
}
