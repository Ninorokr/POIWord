package com.silverlink.queriers;

import com.silverlink.OS;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.silverlink.Datasource.conn;
import static com.silverlink.queriers.Querier.cuentaOSporAnio;

public class Commander {

    //PENSADERO: Corregir la manera de generar numeros de OS
    //si cuenta la cantidad de OS creadas, cuando se elimine una, se repetira el último nro de OS
    //En cuyo caso, obtener el ultimo nro de OS y sumar 1
    //IMPORTANTE: Nunca se eliminará una OS

    public static void crearOS(OS os){

        String insertOS = "INSERT INTO tblOSsContrastes (nroOS, descripcion, idCECO, idUsuario, esNTSCE, soloImpresion, esAlterno)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(insertOS)){
            ps.setInt(1, cuentaOSporAnio() + 1);
            ps.setString(2, os.getDescripcion());
            ps.setString(3, os.getIdCECO());
            ps.setInt(4, os.getIdUsuario());
            ps.setBoolean(5, os.isEsNTSCE());
            ps.setBoolean(6, os.isSoloImpresion());
            ps.setBoolean(7, os.isEsAlterno());
            ps.execute();
        } catch(SQLException sqle){
            System.out.println("No se pudo insertar la OS");
            sqle.printStackTrace();
        }
    }

    public static void crearOSconAnio(OS os){

        String insertOS = "INSERT INTO tblOSsContrastes (anio, nroOS, descripcion, idCECO, idUsuario, esNTSCE, soloImpresion, esAlterno)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(insertOS)){
            ps.setInt(1, 22);
            ps.setInt(2, cuentaOSporAnio() + 1);
            ps.setString(3, os.getDescripcion());
            ps.setString(4, os.getIdCECO());
            ps.setInt(5, os.getIdUsuario());
            ps.setBoolean(6, os.isEsNTSCE());
            ps.setBoolean(7, os.isSoloImpresion());
            ps.setBoolean(8, os.isEsAlterno());
            ps.execute();
        } catch(SQLException sqle){
            System.out.println("No se pudo insertar la OS");
            sqle.printStackTrace();
        }
    }
}
