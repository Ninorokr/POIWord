package com.silverlink.queriers;

import com.silverlink.OS;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.silverlink.Datasource.conn;

public class Commander {

    //PENSADERO: Corregir la manera de generar numeros de OS
    //si cuenta la cantidad de OS creadas, cuando se elimine una, se repetira el último nro de OS
    //En cuyo caso, obtener el ultimo nro de OS y sumar 1
    //Nunca se eliminará una OS

    public static void insertOS(OS os){

        //TODO terminar este query
        String insertOS = "INSERT INTO tblOSs (idServicio, nroOS, descripcion, idCECO, idUsuario, esNTSCE, soloImpresion)" +
                            " VALUES (?, )";

        try(PreparedStatement ps = conn.prepareStatement()){

        } catch(SQLException sqle){
            System.out.println("No se pudo insertar la OS");
        }
    }
}
