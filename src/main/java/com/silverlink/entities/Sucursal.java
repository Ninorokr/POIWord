package com.silverlink.entities;

import static com.silverlink.Main.sucursales;


public class Sucursal {
    private short idSucursal;
    private short numSucursal;

    public Sucursal(short idSucursal, short numSucursal) {
        this.idSucursal = idSucursal;
        this.numSucursal = numSucursal;
    }

    public short getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(short idSucursal) {
        this.idSucursal = idSucursal;
    }

    public short getNumSucursal() {
        return numSucursal;
    }

    public void setNumSucursal(short numSucursal) {
        this.numSucursal = numSucursal;
    }

    public static Sucursal existeSucursal(short numSucursalAsString){
        for(Sucursal sucursal : sucursales){
            if(sucursal.getNumSucursal() == numSucursalAsString)
                return sucursal;
        }
        return null;
    }
}
