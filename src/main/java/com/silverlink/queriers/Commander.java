package com.silverlink.queriers;

import com.silverlink.OS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;

import static com.silverlink.Datasource.conn;
import static com.silverlink.queriers.Querier.cuentaOSporAnio;

public class Commander {

    //PENSADERO: Corregir la manera de generar numeros de OS
    //si cuenta la cantidad de OS creadas, cuando se elimine una, se repetira el último nro de OS
    //En cuyo caso, obtener el ultimo nro de OS y sumar 1
    //IMPORTANTE: Nunca se eliminará una OS

    public static void insertOS(OS os){

        //TODO terminar este query
        String insertOS = "INSERT INTO tblOSs (idServicio, nroOS, descripcion, idCECO, idUsuario, esNTSCE, soloImpresion)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(insertOS)){
            ps.setInt(1, os.getIdServicio());
            ps.setInt(2, cuentaOSporAnio());
            ps.setString(3, os.getDescripcion());
            ps.setString(4, os.getIdCECO());
            ps.setInt(5, os.getIdUsuario());
            ps.setBoolean(6, os.isEsNTSCE());
            ps.setBoolean(7, os.isSoloImpresion());
            ps.execute();
        } catch(SQLException sqle){
            System.out.println("No se pudo insertar la OS");
            sqle.printStackTrace();
        }
    }
}
