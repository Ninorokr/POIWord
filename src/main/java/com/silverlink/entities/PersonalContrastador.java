package com.silverlink.entities;

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
}
