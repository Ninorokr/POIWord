package com.silverlink.queriers;

import com.silverlink.entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static com.silverlink.utils.Datasource.conn;

public class Querier {

    public static ArrayList<CECO> queryCECOsPorServicio(int servicio) {
        String CECOsQuery = "SELECT idCECO, nomCECO FROM tblCECOs\n" +
                "WHERE idServicio = ?";
        CECO ceco = null;
        ArrayList<CECO> CECOs = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(CECOsQuery)) {
            ps.setInt(1, servicio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ceco = new CECO(servicio, rs.getString(1), rs.getString(2));
                CECOs.add(ceco);
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar CECOs");
        }
        return CECOs;
    }
    public static ArrayList<UsuarioENEL> queryUsuariosPorCECO(String CECO) {
        String UsuariosQuery = "SELECT tblCECOsUsuarios.idUsuario, tblUsuarios.nomUsuario FROM tblCECOsUsuarios\n" +
                "INNER JOIN tblUsuarios ON tblCECOsUsuarios.idUsuario = tblUsuarios.idUsuario\n" +
                "WHERE idCECO = ?";
        ArrayList<UsuarioENEL> usuariosENEL = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(UsuariosQuery)) {
            ps.setString(1, CECO);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuariosENEL.add(new UsuarioENEL(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar CECOs");
        }
        return usuariosENEL;
    }
    public static HashMap<Integer, String> queryServicios() {
        String serviciosQuery = "SELECT idServicio, nomServicio FROM tblServicios;";
        HashMap<Integer, String> servicios = new HashMap<>();

        try (PreparedStatement ps = conn.prepareStatement(serviciosQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                servicios.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar servicios");
        }
        return servicios;
    }
    //TODO actualizar anio para que sea automático según el año en que se quiere crear la OS

    public static int cuentaOSporAnio() {
        String countOSporAnioQuery = "SELECT COUNT(anio) AS cuentaAnio FROM tblOSsContrastes WHERE anio = 23"; //La cantidad de anios 23
        //SELECT TOP 1 nroOS FROM tblOSs ORDER BY nroOS DESC // El último NroOS
        int OScount = 0;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(countOSporAnioQuery);
            while (rs.next())
                OScount = rs.getInt(1);
        } catch (SQLException sqle) {
            System.out.println("No se pudo obtener la cuenta de OS por año");
            sqle.printStackTrace();
        }
        return OScount;
        //TODO REVISAR, revisar, revisar
    }

    public static ArrayList<Distrito> queryAllDistritos() {
        String distritosQuery = "SELECT idPais, idDepartamento, idProvincia, idDistrito, nomDistrito FROM tblDistritos";
        ArrayList<Distrito> distritos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(distritosQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                distritos.add(new Distrito(rs.getByte(1), rs.getByte(2), rs.getByte(3),
                        rs.getByte(4), rs.getString(5)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los distritos");
            sqle.printStackTrace();
        }

        return distritos;
    }
    public static ArrayList<Distrito> queryDistritosLimaYCallao() {
        String distritosQuery = "SELECT idPais, idDepartamento, idProvincia, idDistrito, nomDistrito FROM tblDistritos " +
                                "WHERE (idDepartamento = 7 OR idDepartamento = 15) AND idProvincia = 1";
        ArrayList<Distrito> distritos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(distritosQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                distritos.add(new Distrito(rs.getByte(1), rs.getByte(2), rs.getByte(3),
                        rs.getByte(4), rs.getString(5)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los distritos");
            sqle.printStackTrace();
        }
        return distritos;
    }
    public static ArrayList<Sucursal> querySucursales() {
        String sucursalesQuery = "SELECT idSucursal, numSucursal FROM tblSucursales";
        ArrayList<Sucursal> sucursales = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sucursalesQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sucursales.add(new Sucursal(rs.getByte(1), rs.getShort(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar las sucursales");
            sqle.printStackTrace();
        }
        return sucursales;
    }

    public static ArrayList<SET> querySETs() {
        String SETsQuery = "SELECT idSET, codSET, nomSET FROM tblSETs";
        ArrayList<SET> SETs = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(SETsQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SETs.add(new SET(rs.getShort(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los alimentadores (SETs)");
            sqle.printStackTrace();
        }
        return SETs;
    }
    public static ArrayList<MarcaMedidor> queryMarcaMedidores() {
        String MarcaMedidoresQuery = "SELECT [idMarcaMedidor], [codMarcaMedidor], [nomMarcaMedidor] FROM [tblMarcasMedidor]";
        ArrayList<MarcaMedidor> MarcaMedidores = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(MarcaMedidoresQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MarcaMedidores.add(new MarcaMedidor(rs.getShort(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar las marcas de medidores");
            sqle.printStackTrace();
        }
        return MarcaMedidores;
    }
    public static ArrayList<ModeloMedidor> queryModelosMedidor() {
        String ModelosMedidorQuery = "SELECT idMarcaMedidor, idModeloMedidor, nomModeloMedidor FROM tblModelosMedidor";
        ArrayList<ModeloMedidor> ModelosMedidor = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(ModelosMedidorQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ModelosMedidor.add(new ModeloMedidor(rs.getShort(1), rs.getShort(2), rs.getString(3)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los modelos de medidor");
            sqle.printStackTrace();
        }
        return ModelosMedidor;
    }

    public static short queryModelosMedidorPorMarca(short idMarcaMedidor) {
        String cantidadModelosPorMarca = "SELECT COUNT(*) FROM tblModelosMedidor " +
                "WHERE idMarcaMedidor = ?";
        try (PreparedStatement ps = conn.prepareStatement(cantidadModelosPorMarca)) {
            ps.setShort(1, idMarcaMedidor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getShort(1);
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar la cantidad de modelos de medidor por marca");
            sqle.printStackTrace();
        }
        return (short)0;
    }
    public static short queryPersonalContrastadorPorEmpresa(short idEmpresaContrastadora){
        String cantidadPersonalPorEmpresa = "SELECT COUNT(*) FROM tblPersonalContrastador " +
                "WHERE idEmpresaContrastadora = ?";
        try (PreparedStatement ps = conn.prepareStatement(cantidadPersonalPorEmpresa)) {
            ps.setShort(1, idEmpresaContrastadora);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getShort(1);
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar la cantidad de personal contrastador por empresa");
            sqle.printStackTrace();
        }
        return (short)0;
    }

    public static ArrayList<Fase> queryFases() {
        String fasesQuery = "SELECT [idFase], [codFase], [nomFase] FROM tblFases";
        ArrayList<Fase> fases = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(fasesQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fases.add(new Fase(rs.getByte(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar las fases");
            sqle.printStackTrace();
        }
        return fases;
    }
    public static ArrayList<EmpresaContrastadora> queryEmpresasContrastadoras() {
        String empresasContrastadorasQuery = "SELECT idEmpresaContrastadora, nomEmpresaContrastadora, aliasEmpresaContrastadora FROM tblEmpresasContrastadoras";
        ArrayList<EmpresaContrastadora> empresasContrastadoras = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(empresasContrastadorasQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empresasContrastadoras.add(new EmpresaContrastadora(rs.getByte(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar las empresas contrastadoras");
            sqle.printStackTrace();
        }
        return empresasContrastadoras;
    }
    public static ArrayList<PersonalContrastador> queryPersonalContrastador() {
        String personalContrastadorQuery = "SELECT idEmpresaContrastadora, idPersonalContrastador," +
                " dniPersonalContrastador, nomPersonalContrastador, apePersonalContrastador FROM tblPersonalContrastador";
        ArrayList<PersonalContrastador> personalContrastador = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(personalContrastadorQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                personalContrastador.add(new PersonalContrastador(rs.getByte(1), rs.getShort(2),
                        rs.getInt(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar al personal contrastador");
            sqle.printStackTrace();
        }
        return personalContrastador;
    }


}