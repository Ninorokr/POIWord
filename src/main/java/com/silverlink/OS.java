package com.silverlink;

import java.sql.Date;

public class OS {

    private int idServicio;
    private int anio;
    private int nroOS;
    private Date fechaOS;
    private String descripcion;
    private String idCECO;
    private int idUsuario;
    private boolean esNTSCE;
    private boolean soloImpresion;

    public OS(int idServicio, String descripcion, String idCECO, int idUsuario, boolean esNTSCE, boolean soloImpresion) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.idCECO = idCECO;
        this.idUsuario = idUsuario;
        this.esNTSCE = esNTSCE;
        this.soloImpresion = soloImpresion;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public int getAnio() {
        return anio;
    }

    public int getNroOS() {
        return nroOS;
    }

    public Date getFechaOS() {
        return fechaOS;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIdCECO() {
        return idCECO;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public boolean isEsNTSCE() {
        return esNTSCE;
    }

    public boolean isSoloImpresion() {
        return soloImpresion;
    }
}
