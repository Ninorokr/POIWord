package com.silverlink.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.silverlink.Main.*;
import static com.silverlink.entities.Distrito.descifrarDistrito;
import static com.silverlink.queriers.Commander.*;

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
    private short idSET;
    private short numSED;
    private char letraSED;
    private short idMarcaMedidor;
    private short idModeloMedidor;
    private int numMedidor;
    private byte idFase;
    private short anioFab;
    private LocalDate fechaContraste1;
    private LocalDate fechaContraste2; //Los NTSCE tienen fecha2
    private LocalTime hora;

    //Crear columnas y llaves para estos campos
    //Datos adicionales (empresa contrastadora)
    private byte idEmpresaContrastadora;
    private String patron;
    private String carga;
    private PersonalContrastador personalContrastador;

    //Datos operativos
    private byte idPlazoEntrega;
    private byte idTipoEnvio;
    private double precioAsignado;
    private byte idEstEntrega;
    private LocalDate fecIngresado;
    private LocalDate fecAsignado;
    private short idPersonal;
    private LocalDate fecDescargado;

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
        this.numCliente = Integer.parseInt(numCliente.replaceAll("[^0-9]", ""));
    }
    public String getNomCliente() {
        return nomCliente.substring(0, 101);
    }
    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }
    public String getDireccion() {
        return direccion.substring(0, 301);
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Distrito getDistrito() {
        return distrito;
    }
    public void setDistrito(String distrito) {
        switch(distrito){
            case "CARMEN DE LA LEGUA": distrito = "Carmen de la Legua Reynoso"; break;
            case "LURIGANCHO CHOSICA": distrito = "Lurigancho"; break;
        }
        this.distrito = descifrarDistrito(distrito);
    }
    public short getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(String numSucursalAsString) {
        short numSucursal = Short.parseShort(numSucursalAsString);
        boolean flag = true;
        Sucursal sucursal = null;
        while(flag){
            sucursal = Sucursal.existeSucursal(numSucursal);
            if(sucursal == null){
                insertSucursalToDB(numSucursal);
            } else {
                flag = false;
            }
        }
        this.idSucursal = sucursal.getIdSucursal();
    }
    public short getNumSector() {
        return numSector;
    }
    public void setNumSector(String numSector) {
        this.numSector = Short.parseShort(numSector.replaceAll("[^0-9]", ""));
    }
    public short getNumZona() {
        return numZona;
    }
    public void setNumZona(String numZona) {
        this.numZona = Short.parseShort(numZona.replaceAll("[^0-9]", ""));
    }
    public short getNumCorrelativo2() {
        return numCorrelativo2;
    }
    public void setNumCorrelativo2(String numCorrelativo2) {
        this.numCorrelativo2 = Short.parseShort(numCorrelativo2.replaceAll("[^0-9]", ""));
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
    public short getIdSET() {
        return idSET;
    }
    public void setIdSET(String codSET) {
        boolean flag = true;
        SET set = null;
        while(flag){
            set = SET.existeSET(codSET);
            if(set == null){
                insertSETToDB(codSET);
            } else {
                flag = false;
            }
        }
        this.idSET = set.getIdSET();
    }
    public short getNumSED() {
        return numSED;
    }
    public void setNumSEDyLetraSED(String numSED) {
        this.numSED = Short.parseShort(numSED.replaceAll("[^0-9]", ""));
        this.letraSED = numSED.charAt(numSED.length()-1);
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
    public void setIdMarcaMedidor(String codMarcaMedidor) {
        boolean flag = true;
        MarcaMedidor marcaMedidor = null;
        while(flag){
            marcaMedidor = MarcaMedidor.existeMarcaMedidor(codMarcaMedidor);
            if(marcaMedidor == null){
                insertMarcaMedidorToDB(codMarcaMedidor);
            } else {
                flag = false;
            }
        }
        this.idMarcaMedidor = marcaMedidor.getIdMarcaMedidor();
    }
    public short getIdModeloMedidor() {
        return idModeloMedidor;
    }
    public void setIdModeloMedidor(String nomModeloMedidor) {
        boolean flag = true;
        ModeloMedidor modeloMedidor = null;
        while(flag){
            modeloMedidor = ModeloMedidor.existeModeloMedidor(idMarcaMedidor, nomModeloMedidor);
            if(modeloMedidor == null){
                insertModeloMedidorToDB(getIdMarcaMedidor(), nomModeloMedidor);
            } else {
                flag = false;
            }
        }
        this.idModeloMedidor = modeloMedidor.getIdModeloMedidor();
    }
    public int getNumMedidor() {
        return numMedidor;
    }
    public void setNumMedidor(String numMedidor) {
        this.numMedidor = Integer.parseInt(numMedidor.replaceAll("[^0-9]", ""));
    }
    public byte getIdFase() {
        return idFase;
    }
    public void setIdFase(String codFase) {
        boolean flag = true;
        Fase fase = null;
        while(flag){
            fase = Fase.existeFase(codFase);
            if(fase == null){
                System.out.println("??Qu?? fase es " + codFase + "?");
                for(Fase fase2 : fases){System.out.println(fase2);}
                byte idFase = scanner.nextByte();
                fases.add(new Fase(idFase, codFase, ""));
            } else {
                flag = false;
            }
        }
        this.idFase = fase.getIdFase();
    }
    public short getAnioFab() {
        return anioFab;
    }
    public void setAnioFab(String anioFab) {
        this.anioFab = Short.parseShort(anioFab.replaceAll("[^0-9]", ""));
    }
    public LocalDate getFechaContraste1() {
        return fechaContraste1;
    }
    public void setFechaContraste1(LocalDate fechaContraste1) {
        this.fechaContraste1 = fechaContraste1; //TODO definir l??mites para la fecha a contrastar
    }

    public LocalDate getFechaContraste2() {
        return fechaContraste2;
    }

    public void setFechaContraste2(LocalDate fechaContraste2) {
        this.fechaContraste2 = fechaContraste2; //TODO definir l??mites para la fecha a contrastar
    }

    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    //Datos adicionales (empresa contrastadora)

    public byte getIdEmpresaContrastadora() {
        return idEmpresaContrastadora;
    }
    public void setIdEmpresaContrastadora(String aliasEmpContrastadora) {
        boolean flag = true;
        EmpresaContrastadora emp = null;
        while(flag){
            emp = EmpresaContrastadora.existeEmpresa(aliasEmpContrastadora);
            if(emp == null){
                System.out.println("??Qu?? empresa contrastadora es " + aliasEmpContrastadora + "?");
                for(EmpresaContrastadora empCon : empresasContrastadoras){System.out.println(empCon);}
                byte numEmpContrastadora = scanner.nextByte();
                empresasContrastadoras.add(new EmpresaContrastadora(numEmpContrastadora, "", aliasEmpContrastadora));
            } else {
                flag = false;
            }
        }
        this.idEmpresaContrastadora = emp.getIdEmpContrastadora();
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
    public PersonalContrastador getPersonalContrastador() {
        return this.personalContrastador;
    }
    public void setPersonalContrastador(String dni, String nombre, String apellido){
        int dniAsInt = Integer.parseInt(dni);
        boolean flag = true;
        PersonalContrastador personal = null;
        while(flag){
            personal = PersonalContrastador.existePersonalContrastador(dniAsInt);
            if(personal == null){
                insertPersonalContrastadorToDB(getIdEmpresaContrastadora(), dniAsInt, nombre, apellido);
            } else {
                flag = false;
            }
        }
        this.personalContrastador = personal;
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

    public LocalDate getFecIngresado() {
        return fecIngresado;
    }

    public void setFecIngresado(LocalDate fecIngresado) {
        this.fecIngresado = fecIngresado;
    }

    public LocalDate getFecAsignado() {
        return fecAsignado;
    }

    public void setFecAsignado(LocalDate fecAsignado) {
        this.fecAsignado = fecAsignado;
    }

    public short getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(short idPersonal) {
        this.idPersonal = idPersonal;
    }

    public LocalDate getFecDescargado() {
        return fecDescargado;
    }

    public void setFecDescargado(LocalDate fecDescargado) {
        this.fecDescargado = fecDescargado;
    }



}
