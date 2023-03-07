package com.silverlink.entities;

public class EmpresaContrastadora {
    private short idEmpContrastadora;
    private String nomEmpContrastadora;
    private String aliasEmpContrastadora;

    public EmpresaContrastadora(short idEmpContrastadora, String nomEmpContrastadora, String aliasEmpContrastadora) {
        this.idEmpContrastadora = idEmpContrastadora;
        this.nomEmpContrastadora = nomEmpContrastadora;
        this.aliasEmpContrastadora = aliasEmpContrastadora;
    }

    public short getIdEmpContrastadora() {
        return idEmpContrastadora;
    }

    public void setIdEmpContrastadora(short idEmpContrastadora) {
        this.idEmpContrastadora = idEmpContrastadora;
    }

    public String getNomEmpContrastadora() {
        return nomEmpContrastadora;
    }

    public void setNomEmpContrastadora(String nomEmpContrastadora) {
        this.nomEmpContrastadora = nomEmpContrastadora;
    }

    public String getAliasEmpContrastadora() {
        return aliasEmpContrastadora;
    }

    public void setAliasEmpContrastadora(String aliasEmpContrastadora) {
        this.aliasEmpContrastadora = aliasEmpContrastadora;
    }
}
