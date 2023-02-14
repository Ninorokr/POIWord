package com.silverlink;

public class Main {

    public static void main(String[] args) throws Exception {
//        NTSCE ntsce = new NTSCE();
//        ntsce.generar();

        ExcelGatherer eg = new ExcelGatherer();
        eg.extract("D:\\Apache POI test\\SERVCON_AV06-23.xlsx");
    }
}
