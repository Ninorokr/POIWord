package com.silverlink;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ExcelGatherer {

    Scanner scanner = new Scanner(System.in);

    /*


     */

    public void extract(String excelFile){

        try(XSSFWorkbook wb = new XSSFWorkbook(excelFile)){
            if(wb.getNumberOfSheets() != 1){

            } else {
                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFRow row = sheet.getRow(0);
                XSSFCell cell = row.getCell(0);
                System.out.println(cell.getStringCellValue());
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public int displaySheetNames(){

    }

}
