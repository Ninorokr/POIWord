package com.silverlink.entities;

public class UsuarioENEL {
    private int idUsuarioENEL;
    private String nomUsuarioENEL;
    private String eMailUsuarioENEL;
    private int idSede;
    private String nomCompletoUsuarioENEL;

    public UsuarioENEL(int idUsuarioENEL, String nomUsuarioENEL) {
        this.idUsuarioENEL = idUsuarioENEL;
        this.nomUsuarioENEL = nomUsuarioENEL;
    }

    public int getIdUsuarioENEL() {
        return idUsuarioENEL;
    }
    public String getNomUsuarioENEL() {
        return nomUsuarioENEL;
    }
    public String geteMailUsuarioENEL() {
        return eMailUsuarioENEL;
    }
    public int getIdSede() {
        return idSede;
    }
    public String getNomCompletoUsuarioENEL() {
        return nomCompletoUsuarioENEL;
    }

}
