package com.silverlink.entities;

import java.util.Date;

public class AvisoContraste {

    //Datos ID
    private short anioOS;
    private short nroOS;
    private short idAvisoContraste;

    //Datos ENEL
    private int numCorrelativo;
    private int numCliente;
    private String nomCliente;
    private String direccion;
    private Distrito distrito;
    private short idSucursal;
    private short numSector;
    private short numZona;
    private short numCorrelativo2;
    private double promedio;
    private double latitud;
    private double longitud;
    private byte idSET;
    private short numSED;
    private char letraSED;
    private short idMarcaMedidor;
    private short idModeloMedidor;
    private int numMedidor;
    private byte idFase;
    private short anioFab;
    private Date fechaContraste;

    //Crear columnas y llaves para estos campos
    //Datos adicionales (empresa contrastadora)
    private byte idEmpresaContrastadora;
    private String patron;
    private String carga;
    private int idPersonalContrastador;

    //Datos operativos
    private byte idPlazoEntrega;
    private byte idTipoEnvio;
    private double precioAsignado;
    private byte idEstEntrega;
    private Date fecIngresado;
    private Date fecAsignado;
    private short idPersonal;
    private Date fecDescargado;

    //se puede enviar datos generales para ayudar con la construccion del objeto
    public AvisoContraste(){

    }

    //Datos ID

    public short getAnioOS() {
        return anioOS;
    }

    public void setAnioOS(short anioOS) {
        this.anioOS = anioOS;
    }

    public short getNroOS() {
        return nroOS;
    }

    public void setNroOS(short nroOS) {
        this.nroOS = nroOS;
    }

    public short getIdAvisoContraste() {
        return idAvisoContraste;
    }

    public void setIdAvisoContraste(short idAvisoContraste) {
        this.idAvisoContraste = idAvisoContraste;
    }

    //Datos ENEL

    public int getNumCorrelativo() {
        return numCorrelativo;
    }

    public void setNumCorrelativo(String numCorrelativoTexto) {
        this.numCorrelativo = Integer.parseInt(numCorrelativoTexto.replaceAll("[^0-9]", ""));
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(String numCliente) {
        this.numCliente = Integer.parseInt(numCliente.replaceAll("[^0-9]", ""));;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public short getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(short idSucursal) {
        this.idSucursal = idSucursal;
    }

    public short getNumSector() {
        return numSector;
    }

    public void setNumSector(short numSector) {
        this.numSector = numSector;
    }

    public short getNumZona() {
        return numZona;
    }

    public void setNumZona(short numZona) {
        this.numZona = numZona;
    }

    public short getNumCorrelativo2() {
        return numCorrelativo2;
    }

    public void setNumCorrelativo2(short numCorrelativo2) {
        this.numCorrelativo2 = numCorrelativo2;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public byte getIdSET() {
        return idSET;
    }

    public void setIdSET(byte idSET) {
        this.idSET = idSET;
    }

    public short getNumSED() {
        return numSED;
    }

    public void setNumSED(short numSED) {
        this.numSED = numSED;
    }

    public char getLetraSED() {
        return letraSED;
    }

    public void setLetraSED(char letraSED) {
        this.letraSED = letraSED;
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

    public int getNumMedidor() {
        return numMedidor;
    }

    public void setNumMedidor(int numMedidor) {
        this.numMedidor = numMedidor;
    }

    public byte getIdFase() {
        return idFase;
    }

    public void setIdFase(byte idFase) {
        this.idFase = idFase;
    }

    public short getAnioFab() {
        return anioFab;
    }

    public void setAnioFab(short anioFab) {
        this.anioFab = anioFab;
    }

    public Date getFechaContraste() {
        return fechaContraste;
    }

    public void setFechaContraste(Date fechaContraste) {
        this.fechaContraste = fechaContraste;
    }

    //Datos adicionales (empresa contrastadora)

    public byte getIdEmpresaContrastadora() {
        return idEmpresaContrastadora;
    }

    public void setIdEmpresaContrastadora(byte idEmpresaContrastadora) {
        this.idEmpresaContrastadora = idEmpresaContrastadora;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public int getIdPersonalContrastador() {
        return idPersonalContrastador;
    }

    public void setIdPersonalContrastador(int idPersonalContrastador) {
        this.idPersonalContrastador = idPersonalContrastador;
    }

    //Datos operativos

    public byte getIdPlazoEntrega() {
        return idPlazoEntrega;
    }

    public void setIdPlazoEntrega(byte idPlazoEntrega) {
        this.idPlazoEntrega = idPlazoEntrega;
    }

    public byte getIdTipoEnvio() {
        return idTipoEnvio;
    }

    public void setIdTipoEnvio(byte idTipoEnvio) {
        this.idTipoEnvio = idTipoEnvio;
    }

    public double getPrecioAsignado() {
        return precioAsignado;
    }

    public void setPrecioAsignado(double precioAsignado) {
        this.precioAsignado = precioAsignado;
    }

    public byte getIdEstEntrega() {
        return idEstEntrega;
    }

    public void setIdEstEntrega(byte idEstEntrega) {
        this.idEstEntrega = idEstEntrega;
    }

    public Date getFecIngresado() {
        return fecIngresado;
    }

    public void setFecIngresado(Date fecIngresado) {
        this.fecIngresado = fecIngresado;
    }

    public Date getFecAsignado() {
        return fecAsignado;
    }

    public void setFecAsignado(Date fecAsignado) {
        this.fecAsignado = fecAsignado;
    }

    public short getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(short idPersonal) {
        this.idPersonal = idPersonal;
    }

    public Date getFecDescargado() {
        return fecDescargado;
    }

    public void setFecDescargado(Date fecDescargado) {
        this.fecDescargado = fecDescargado;
    }



}
