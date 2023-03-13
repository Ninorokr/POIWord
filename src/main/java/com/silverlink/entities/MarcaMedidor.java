package com.silverlink.entities;

import static com.silverlink.Main.SETs;
import static com.silverlink.Main.marcaMedidores;

public class MarcaMedidor {

    private short idMarcaMedidor;
    private String codMarcaMedidor;
    private String nomMarcaMedidor;

    public MarcaMedidor(short idMarcaMedidor, String codMarcaMedidor, String nomMarcaMedidor) {
        this.idMarcaMedidor = idMarcaMedidor;
        this.codMarcaMedidor = codMarcaMedidor;
        this.nomMarcaMedidor = nomMarcaMedidor;
    }

    public short getIdMarcaMedidor() {
        return idMarcaMedidor;
    }
    public void setIdMarcaMedidor(byte idMarcaMedidor) {
        this.idMarcaMedidor = idMarcaMedidor;
    }
    public String getCodMarcaMedidor() {
        return codMarcaMedidor;
    }
    public void setCodMarcaMedidor(String codMarcaMedidor) {
        this.codMarcaMedidor = codMarcaMedidor;
    }
    public String getNomMarcaMedidor() {
        return nomMarcaMedidor;
    }
    public void setNomMarcaMedidor(String nomMarcaMedidor) {
        this.nomMarcaMedidor = nomMarcaMedidor;
    }

    public static MarcaMedidor existeMarcaMedidor(String codMarcaMedidor){
        for(MarcaMedidor marca : marcaMedidores){
            if(marca.getCodMarcaMedidor().equals(codMarcaMedidor))
                return marca;
        }
        return null;
    }
}
