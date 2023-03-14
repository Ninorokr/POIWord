package com.silverlink.entities;

import static com.silverlink.Main.empresasContrastadoras;
import static com.silverlink.Main.fases;

public class EmpresaContrastadora {
    private byte idEmpContrastadora;
    private String nomEmpContrastadora;
    private String aliasEmpContrastadora;

    public EmpresaContrastadora(byte idEmpContrastadora, String nomEmpContrastadora, String aliasEmpContrastadora) {
        this.idEmpContrastadora = idEmpContrastadora;
        this.nomEmpContrastadora = nomEmpContrastadora;
        this.aliasEmpContrastadora = aliasEmpContrastadora;
    }

    public byte getIdEmpContrastadora() {
        return idEmpContrastadora;
    }
    public void setIdEmpContrastadora(byte idEmpContrastadora) {
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
    public static EmpresaContrastadora existeEmpresa(String aliasEmp){
        for(EmpresaContrastadora emp : empresasContrastadoras){
            if(emp.getAliasEmpContrastadora().equals(aliasEmp))
                return emp;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getIdEmpContrastadora() + " | " + this.getNomEmpContrastadora() + " | " + this.getAliasEmpContrastadora();
    }
}
