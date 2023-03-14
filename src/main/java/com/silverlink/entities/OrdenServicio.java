package com.silverlink.entities;

import java.sql.Date;

public class OrdenServicio {

    private int idServicio;
    private int anio;
    private short nroOS;
    private Date fechaOS;
    private String descripcion;
    private String idCECO;
    private int idUsuario;
    private boolean esNTSCE;
    private boolean soloImpresion;
    private boolean esAlterno;

    public OrdenServicio(int idServicio, String descripcion, String idCECO, int idUsuario, boolean esNTSCE, boolean soloImpresion, boolean esAlterno) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.idCECO = idCECO;
        this.idUsuario = idUsuario;
        this.esNTSCE = esNTSCE;
        this.soloImpresion = soloImpresion;
        this.esAlterno = esAlterno;
    }

    public int getIdServicio() {
        return idServicio;
    }
    public int getAnio() {
        return anio;
    }
    public short getNroOS() {
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
    public boolean esNTSCE() {
        return esNTSCE;
    }
    public boolean isSoloImpresion() {
        return soloImpresion;
    }
    public boolean esAlterno() {
        return esAlterno;
    }
}
