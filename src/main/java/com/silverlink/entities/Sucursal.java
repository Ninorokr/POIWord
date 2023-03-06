package com.silverlink.entities;

//TODO Crear tabla sucursal en la BD
public class Sucursal {
    private int idSucursal;
    private short numSucursal;

    public Sucursal(int idSucursal, short numSucursal) {
        this.idSucursal = idSucursal;
        this.numSucursal = numSucursal;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public short getNumSucursal() {
        return numSucursal;
    }

    public void setNumSucursal(short numSucursal) {
        this.numSucursal = numSucursal;
    }
}
