package com.silverlink.queriers;

import com.silverlink.entities.AvisoContraste;
import com.silverlink.entities.OrdenServicio;
import com.silverlink.entities.PersonalContrastador;

import java.io.Reader;
import java.sql.Date;
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

    public static short idAvisoContraste = 0;

    public static void crearNuevaOS(OrdenServicio os){

        String insertOS = "INSERT INTO tblOSsContrastes (nroOS, descripcion, idCECO, idUsuario, esNTSCE, soloImpresion, esAlterno)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?)";

        int nuevoNumOS = cuentaOSporAnio() + 1;

        try(PreparedStatement ps = conn.prepareStatement(insertOS)){
            ps.setInt(1, nuevoNumOS);
            ps.setString(2, os.getDescripcion());
            ps.setString(3, os.getIdCECO());
            ps.setInt(4, os.getIdUsuario());
            ps.setBoolean(5, os.esNTSCE());
            ps.setBoolean(6, os.isSoloImpresion());
            ps.setBoolean(7, os.esAlterno());
            ps.execute();
            System.out.println("Se creó la OS " + 001 + "-" + 23 + "-" + nuevoNumOS);
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
            ps.setBoolean(6, os.esNTSCE());
            ps.setBoolean(7, os.isSoloImpresion());
            ps.setBoolean(8, os.esAlterno());
            ps.execute();
        } catch(SQLException sqle){
            System.out.println("No se pudo insertar la OS");
            sqle.printStackTrace();
        }
    }

    public static void insertSucursalToDB(short numSucursal){
        String insertSucursalQuery = "INSERT INTO tblSucursales (numSucursal) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertSucursalQuery)){
            ps.setShort(1, numSucursal);
            ps.execute();
            System.out.println("Se insertó nueva sucursal: " + numSucursal);
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la sucursal a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        sucursales = querySucursales();
    }
    public static void insertSETToDB(String codSET){
        String insertSETQuery = "INSERT INTO tblSETs (codSET) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertSETQuery)){
            ps.setString(1, codSET);
            ps.execute();
            System.out.println("Se insertó nueva SET: " + codSET);
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la SET a la BD");
        }

        SETs = querySETs();
    }
    public static void insertMarcaMedidorToDB(String codMarcaMedidor){
        String insertMarcaMedidorQuery = "INSERT INTO tblMarcasMedidor (codMarcaMedidor) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertMarcaMedidorQuery)){
            ps.setString(1, codMarcaMedidor);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la marca de medidor a la BD");
        }
        System.out.println("Se insertó la nueva marca de medidor: " + codMarcaMedidor);

        marcaMedidores = queryMarcaMedidores();
    }
    public static void insertModeloMedidorToDB(short idMarcaMedidor, String nomModeloMedidor){
        short nuevoIdModeloMedidor = (short)(queryModelosMedidorPorMarca(idMarcaMedidor) + 1);

        String insertModeloMedidorQuery = "INSERT INTO tblModelosMedidor (idMarcaMedidor, idModeloMedidor, nomModeloMedidor) VALUES (?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(insertModeloMedidorQuery)){
            ps.setShort(1, idMarcaMedidor);
            ps.setShort(2, nuevoIdModeloMedidor);
            ps.setString(3, nomModeloMedidor);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el modelo de medidor a la BD");
        }
        System.out.println("Se insertó el nuevo modelo de medidor: " + nomModeloMedidor);

        modelosMedidor = queryModelosMedidor();
    }
    public static void insertFaseToDB(String codFase){
        String insertFase = "INSERT INTO tblFases (nomFase) VALUES (?)";

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
        String insertEmpContrastadora = "INSERT INTO tblEmpresasContrastadoras (aliasEmpresaContrastadora) VALUES (?)";

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
        short nuevoIdPersonalContrastador = (short)(queryPersonalContrastadorPorEmpresa(idEmpresaContrastadora) + 1);

        PersonalContrastador persCont = new PersonalContrastador(idEmpresaContrastadora, dni, nombre, apellido);
        String insertPersCont = "INSERT INTO tblPersonalContrastador(idEmpresaContrastadora, idPersonalContrastador, dniPersonalContrastador," +
                "nomPersonalContrastador, apePersonalContrastador) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(insertPersCont)){
            ps.setByte(1, idEmpresaContrastadora);
            ps.setShort(2, nuevoIdPersonalContrastador);
            ps.setInt(3, dni);
            ps.setString(4, nombre);
            ps.setString(5, apellido);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar al personal contrastador a la BD");
        }
        System.out.println("Se insertó al nuevo personal contrastador: " + persCont);

        listaPersonalContrastador = queryPersonalContrastador();
    }

    public static void insertAvisoContrasteToDB(OrdenServicio os, AvisoContraste aviso){
        String insertAviso = "INSERT INTO dbo.tblAvisosContrastes (nroOS, idAvisoContraste, numCorrelativo, " + //3
                "numCliente, nomCliente, dirCliente, idPaisDestino, idDepartamentoDestino, idProvinciaDestino, idDistritoDestino, " + //10
                "idSucursal, numSector, numZona, numCorrelativo2, promedio, latitud, longitud, idSET, numSED, letraSED, idMarcaMedidor, " + //21
                "idModeloMedidor, numMedidor, idFase, anioFab, fechaContraste, horaInicio, horaFin, idEmpresaContrastadora, patron, " + //30
                "carga, idPersonalContrastador, idPlazoEntrega, idTipoEnvio, precioAsignado, idEstEntrega, fecIngresado, fecAsignado, " + //38
                "idPersonal, fecDescargado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //42

        try(PreparedStatement ps = conn.prepareStatement(insertAviso)){
            ps.setShort(1, os.getNroOS());
            ps.setShort(2, idAvisoContraste++);
            ps.setInt(3, aviso.getNumCorrelativo());
            ps.setInt(4, aviso.getNumCliente());
            ps.setString(5, aviso.getNomCliente());
            ps.setString(6, aviso.getDireccion());
            ps.setShort(7, aviso.getDistrito().getIdPais());
            ps.setShort(8, aviso.getDistrito().getIdDepartamento());
            ps.setShort(9, aviso.getDistrito().getIdProvincia());
            ps.setShort(10, aviso.getDistrito().getIdDistrito());
            ps.setShort(11, aviso.getIdSucursal());
            ps.setShort(12, aviso.getNumSector());
            ps.setShort(13, aviso.getNumZona());
            ps.setShort(14, aviso.getNumCorrelativo2());
            ps.setDouble(15, aviso.getPromedio());
            ps.setDouble(16, aviso.getLatitud());
            ps.setDouble(17, aviso.getLongitud());
            ps.setShort(18, aviso.getIdSET());
            ps.setShort(19, aviso.getNumSED());
            ps.setString(20, aviso.getLetraSED() + ""); //TODO Checar que pase bien este dato
            ps.setShort(21, aviso.getIdMarcaMedidor());
            ps.setShort(23, aviso.getIdModeloMedidor());
            ps.setInt(24, aviso.getNumMedidor());
            ps.setShort(25, aviso.getIdFase());
            ps.setShort(26, aviso.getAnioFab());
            ps.setDate(27, Date.valueOf(aviso.getFechaContraste1()));
            ps.setShort(28, aviso.getIdModeloMedidor());
            //TODO Terminar de colocar parametros

        } catch (SQLException sqle){
            System.out.println("No se pudo insertar aviso de contraste a la BD");
            sqle.printStackTrace();
        }
    }


}
