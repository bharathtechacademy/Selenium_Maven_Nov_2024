package com.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public static String [][] readData(String workbook, String sheetName){
		
		String [][] data = null;
		
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+workbook);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh =wb.getSheet(sheetName);
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();
			
			data = new String [totalRows-1][totalColumns];
			
			for(int r=1;r<totalRows ;r++) {
				for(int c=0;c<totalColumns; c++) {
					data[r-1][c] =sh.getRow(r).getCell(c).getStringCellValue();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return data;		
	}
	
	
	public static List<Map<String,String>> readExcelData(String workbook, String sheetName){
		
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+workbook);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh =wb.getSheet(sheetName);
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();			
			
			for(int r=1;r<totalRows ;r++) {
				Map<String,String> rowData = new HashMap<String,String>();
				for(int c=0;c<totalColumns; c++) {
					String key = sh.getRow(0).getCell(c).getStringCellValue();
					String value = sh.getRow(r).getCell(c).getStringCellValue();
					rowData.put(key, value);
				}
				data.add(rowData);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return data;		
	}

}
