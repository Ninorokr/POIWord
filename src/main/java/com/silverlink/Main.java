package com.silverlink;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.silverlink.Datasource.conn;

public class Main {

    public static void main(String[] args){

        Datasource.open();
        ResultSet rs = null;

        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM tblPlazoEntrega ORDER BY idPlazoEntrega ASC")){
            rs = ps.executeQuery();
            System.out.println("idPlazoEntrega | nomPlazoEntrega");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " | " + rs.getString(2));
            }

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }


//        NTSCE ntsce = new NTSCE();
//        ntsce.generar();

//        ExcelGatherer eg = new ExcelGatherer();
//        eg.extract("D:\\Apache POI test\\BVERITAS_AV02-23.xlsx");


    }
}
