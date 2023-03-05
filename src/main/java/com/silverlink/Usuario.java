package com.silverlink;

public class Usuario {
    private int idUsuario;
    private String nomUsuario;
    private String eMailUsuario;
    private int idSede;
    private String nomCompletoUsuario;

    public Usuario(int idUsuario, String nomUsuario) {
        this.idUsuario = idUsuario;
        this.nomUsuario = nomUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public String geteMailUsuario() {
        return eMailUsuario;
    }

    public int getIdSede() {
        return idSede;
    }

    public String getNomCompletoUsuario() {
        return nomCompletoUsuario;
    }
}
