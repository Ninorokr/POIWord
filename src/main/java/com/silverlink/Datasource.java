package com.silverlink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.silverlink.utils.Constantes.connURL;

public class Datasource {

    public static Connection conn;

    public static void open() {
        try {
            conn = DriverManager.getConnection(connURL);
        } catch (SQLException sqle) {
            System.out.println("No se pudo conectar a la BD");
            sqle.printStackTrace();
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection. :?");
        }
    }

}
