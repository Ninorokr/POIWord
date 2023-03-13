package com.silverlink.entities;

import static com.silverlink.Main.*;

public class PersonalContrastador {
    private byte idEmpresaContrastadora;
    private short idPersonalContrastador;
    private int dniPersonalContrastador;
    private String nomPersonalContrastador;
    private String apePersonalContrastador;

    public PersonalContrastador(byte idEmpresaContrastadora, short idPersonalContrastador, int dniPersonalContrastador, String nomPersonalContrastador, String apePersonalContrastador) {
        this.idEmpresaContrastadora = idEmpresaContrastadora;
        this.idPersonalContrastador = idPersonalContrastador;
        this.dniPersonalContrastador = dniPersonalContrastador;
        this.nomPersonalContrastador = nomPersonalContrastador;
        this.apePersonalContrastador = apePersonalContrastador;
    }
    public PersonalContrastador(byte idEmpresaContrastadora, int dniPersonalContrastador, String nomPersonalContrastador, String apePersonalContrastador) {
        this.idEmpresaContrastadora = idEmpresaContrastadora;
        this.dniPersonalContrastador = dniPersonalContrastador;
        this.nomPersonalContrastador = nomPersonalContrastador;
        this.apePersonalContrastador = apePersonalContrastador;
    }

    public byte getIdEmpresaContrastadora() {
        return idEmpresaContrastadora;
    }
    public void setIdEmpresaContrastadora(byte idEmpresaContrastadora) {
        this.idEmpresaContrastadora = idEmpresaContrastadora;
    }
    public short getIdPersonalContrastador() {
        return idPersonalContrastador;
    }
    public void setIdPersonalContrastador(short idPersonalContrastador) {
        this.idPersonalContrastador = idPersonalContrastador;
    }
    public int getDniPersonalContrastador() {
        return dniPersonalContrastador;
    }
    public void setDniPersonalContrastador(int dniPersonalContrastador) {
        this.dniPersonalContrastador = dniPersonalContrastador;
    }
    public String getNomPersonalContrastador() {
        return nomPersonalContrastador;
    }
    public void setNomPersonalContrastador(String nomPersonalContrastador) {
        this.nomPersonalContrastador = nomPersonalContrastador;
    }
    public String getApePersonalContrastador() {
        return apePersonalContrastador;
    }
    public void setApePersonalContrastador(String apePersonalContrastador) {
        this.apePersonalContrastador = apePersonalContrastador;
    }

    public static PersonalContrastador existePersonalContrastador(int dniPersonalContrastador){
        for(PersonalContrastador persCont : listaPersonalContrastador){
            if(persCont.getDniPersonalContrastador() == dniPersonalContrastador)
                return persCont;
        }
        return null;
    }

    @Override
    public String toString() {
        return "EmpCont: " + empresasContrastadoras.get(idEmpresaContrastadora) +
                " | DNI: " + dniPersonalContrastador +
                " | Nombre: " + nomPersonalContrastador +
                " | Apellido: " + apePersonalContrastador;
    }
}
