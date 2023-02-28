package com.silverlink.queriers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.silverlink.Datasource.conn;

public class Querier {

    public static void queryCECOs(){
        String CECOsquery= "SELECT idServicio, idCECO, nomCECO FROM tblCECOs\n" +
                "  WHERE idServicio = ?";

        try(PreparedStatement ps = conn.prepareStatement(CECOsquery)){
            ps.setInt(1, 1);

        } catch (SQLException sqle){
            System.out.println("No se pudo consultar CECOs");
        }
    }

    public static Map<Integer, String> queryServicios(){
        String serviciosQuery= "SELECT idServicio, nomServicio FROM tblServicios;";
        ResultSet rs = null;
        HashMap<Integer, String> servicios = new HashMap<>();

        try(PreparedStatement ps = conn.prepareStatement(serviciosQuery)){
            while(rs.next()){
                servicios.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException sqle){
            System.out.println("No se pudo consultar CECOs");
        }
        return servicios;
    }
}
