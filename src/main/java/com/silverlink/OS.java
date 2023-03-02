package com.silverlink;

import java.sql.Date;

public class OS {

    public int idServicio;
    public int anio;
    public int nroOS;
    public Date fechaOS;
    public String descripcion;
    public String idCECO;
    public int idUsuario;
    public boolean esNTSCE;
    public boolean soloImpresion;

    public OS(String descripcion, String idCECO, int idUsuario, boolean esNTSCE, boolean soloImpresion) {
        this.descripcion = descripcion;
        this.idCECO = idCECO;
        this.idUsuario = idUsuario;
        this.esNTSCE = esNTSCE;
        this.soloImpresion = soloImpresion;
    }
}
