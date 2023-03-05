package com.silverlink.queriers;

import com.silverlink.CECO;
import com.silverlink.Usuario;
import org.apache.logging.log4j.core.util.KeyValuePair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.silverlink.Datasource.conn;

public class Querier {

    public static ArrayList<Usuario> queryUsuariosPorCECO(String CECO){
        String UsuariosQuery = "SELECT tblCECOsUsuarios.idUsuario, tblUsuarios.nomUsuario FROM tblCECOsUsuarios\n" +
                                "INNER JOIN tblUsuarios ON tblCECOsUsuarios.idUsuario = tblUsuarios.idUsuario\n" +
                                "WHERE idCECO = ?";
//        HashMap<Integer, String> usuarios = new HashMap<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try(PreparedStatement ps = conn.prepareStatement(UsuariosQuery)){
            ps.setString(1, CECO);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usuarios.add(new Usuario(rs.getInt(1), rs.getString(2)));
//                usuarios.put(rs.getInt(1), rs.getString(2));
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

    public static int cuentaOSporAnio() {
        String countOSporAnioQuery = "SELECT COUNT(anio) AS cuentaAnio FROM tblOSsContrastes WHERE anio = 23"; //La cantidad de anios 23
        //SELECT TOP 1 nroOS FROM tblOSs ORDER BY nroOS DESC // El último NroOS
        int OScount = 0;
        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(countOSporAnioQuery);
            while(rs.next())
                OScount = rs.getInt(1);
        } catch (SQLException sqle){
            System.out.println("No se pudo obtener la cuenta de OS por año");
            sqle.printStackTrace();
        }
        return OScount;
        //TODO REVISAR, revisar, revisar
    }
}
