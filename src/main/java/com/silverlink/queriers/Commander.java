package com.silverlink.queriers;

import com.silverlink.entities.OrdenServicio;
import com.silverlink.entities.PersonalContrastador;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.silverlink.Main.*;
import static com.silverlink.queriers.Querier.*;
import static com.silverlink.utils.Datasource.conn;

public class Commander {

    //PENSADERO: Corregir la manera de generar numeros de OS
    //si cuenta la cantidad de OS creadas, cuando se elimine una, se repetira el último nro de OS
    //En cuyo caso, obtener el ultimo nro de OS y sumar 1
    //IMPORTANTE: Nunca se eliminará una OS

    public static void crearOS(OrdenServicio os){

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

    public static void crearOSconAnio(OrdenServicio os){

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

    public static void insertSucursalToDB(short numSucursal){
        String insertSucursalQuery = "INSERT INTO tblSucursales (numSucursal) VALUES ?";

        try(PreparedStatement ps = conn.prepareStatement(insertSucursalQuery)){
            ps.setShort(1, numSucursal);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la sucursal a la BD");
        }
        System.out.println("Se insertó nueva sucursal: " + numSucursal);
        sucursales = querySucursales();
    }
    public static void insertSETToDB(String codSET){
        String insertSETQuery = "INSERT INTO tblSETs (codSET) VALUES ?";

        try(PreparedStatement ps = conn.prepareStatement(insertSETQuery)){
            ps.setString(1, codSET);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la SET a la BD");
        }
        System.out.println("Se insertó nueva SET: " + codSET);
        SETs = querySETs();
    }
    public static void insertMarcaMedidorToDB(String nomMarcaMedidor){
        String insertMarcaMedidorQuery = "INSERT INTO tblMarcasMedidor (codMarcaMedidor) VALUES ?";

        try(PreparedStatement ps = conn.prepareStatement(insertMarcaMedidorQuery)){
            ps.setString(1, nomMarcaMedidor);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la marca de medidor a la BD");
        }
        System.out.println("Se insertó la nueva marca de medidor: " + nomMarcaMedidor);

        marcaMedidores = queryMarcaMedidores();
    }
    public static void insertModeloMedidorToDB(short IdMarcaMedidor, String nomModeloMedidor){
        String insertModeloMedidorQuery = "INSERT INTO tblModelosMedidor (idMarcaMedidor, nomModeloMedidor) VALUES ?, ?";

        try(PreparedStatement ps = conn.prepareStatement(insertModeloMedidorQuery)){
            ps.setShort(1, IdMarcaMedidor);
            ps.setString(2, nomModeloMedidor);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el modelo de medidor a la BD");
        }
        System.out.println("Se insertó el nuevo modelo de medidor: " + nomModeloMedidor);

        marcaMedidores = queryMarcaMedidores();
    }
    public static void insertFaseToDB(String codFase){
        String insertFase = "INSERT INTO tblFases (nomFase) VALUES ?";

        try(PreparedStatement ps = conn.prepareStatement(insertFase)){
            ps.setString(1, codFase);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la fase a la BD");
        }
        System.out.println("Se insertó la nueva fase: " + codFase);

        fases = queryFases();
    }
    public static void insertEmpresaContrastadoraToDB(String aliasEmp){
        String insertEmpContrastadora = "INSERT INTO tblEmpresasContrastadoras (aliasEmpresaContrastadora) VALUES ?";

        try(PreparedStatement ps = conn.prepareStatement(insertEmpContrastadora)){
            ps.setString(1, aliasEmp);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la empresa contrastadora a la BD");
        }
        System.out.println("Se insertó la nueva empresa contrastadora: " + aliasEmp);

        empresasContrastadoras = queryEmpresasContrastadoras();
    }
    public static void insertPersonalContrastadorToDB(byte idEmpresaContrastadora, int dni, String nombre, String apellido){
        PersonalContrastador persCont = new PersonalContrastador(idEmpresaContrastadora, dni, nombre, apellido);
        String insertPersCont = "INSERT INTO tblPersonalContrastador(idEmpresaContrastadora, dniPersonalContrastador," +
                "nomPersonalContrastador, apePersonalContrastador) VALUES ?, ?, ?, ?";

        try(PreparedStatement ps = conn.prepareStatement(insertPersCont)){
            ps.setByte(1, idEmpresaContrastadora);
            ps.setInt(2, dni);
            ps.setString(3, nombre);
            ps.setString(4, apellido);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar al personal contrastador a la BD");
        }
        System.out.println("Se insertó al nuevo personal contrastador: " + persCont);

        listaPersonalContrastador = queryPersonalContrastador();
    }


}
