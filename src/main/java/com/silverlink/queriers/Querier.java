package com.silverlink.queriers;

import com.silverlink.CECO;
import org.apache.logging.log4j.core.util.KeyValuePair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.silverlink.Datasource.conn;

public class Querier {

    public static HashMap<Integer, String> queryUsuariosPorCECO(String CECO){
        String UsuariosQuery = "SELECT tblCECOsUsuarios.idUsuario, tblUsuarios.nomUsuario FROM tblCECOsUsuarios\n" +
                                "INNER JOIN tblUsuarios ON tblCECOsUsuarios.idUsuario = tblUsuarios.idUsuario\n" +
                                "WHERE idCECO = ?";
        HashMap<Integer, String> usuarios = new HashMap<>();

        try(PreparedStatement ps = conn.prepareStatement(UsuariosQuery)){
            ps.setString(1, CECO);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usuarios.put(rs.getInt(1), rs.getString(2));
            }

        } catch (SQLException sqle){
            System.out.println("No se pudo consultar CECOs");
        }
        return usuarios;
    }

    public static ArrayList<CECO> queryCECOsPorServicio(int servicio){
        String CECOsQuery = "SELECT idCECO, nomCECO FROM tblCECOs\n" +
                            "WHERE idServicio = ?";
        CECO ceco = null;
        ArrayList<CECO> CECOs = new ArrayList<>();

        try(PreparedStatement ps = conn.prepareStatement(CECOsQuery)){
            ps.setInt(1, servicio);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ceco = new CECO(servicio, rs.getString(1), rs.getString(2));
                CECOs.add(ceco);
            }
        } catch (SQLException sqle){
            System.out.println("No se pudo consultar CECOs");
        }
        return CECOs;
    }

    public static HashMap<Integer, String> queryServicios(){
        String serviciosQuery = "SELECT idServicio, nomServicio FROM tblServicios;";
        HashMap<Integer, String> servicios = new HashMap<>();

        try(PreparedStatement ps = conn.prepareStatement(serviciosQuery)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                servicios.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException sqle){
            System.out.println("No se pudo consultar servicios");
        }
        return servicios;
    }
}
