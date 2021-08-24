package com.zerv.excel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class SpreadsheetReader {

	public File file;
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public static int excelColumnCount;
		static int excelRowCount;
		public static int startRowIndex;
		public static int startColumnIndex;
		public static int endRowIndex;
		public static int endColumnIndex;

		//Excel WorkBook declaration

		public void excelPath(String path) {
			try {
				file = new File(path);
				fis = new FileInputStream(file);
				wb = new XSSFWorkbook(fis);
			} catch(Exception e) {
				
			}

		}

		public void excelWritePath(String path) {
			try {
				file = new File(path);
				fos = new FileOutputStream(file);
				wb.write(fos);
				fos.flush();
				wb.close();
			} catch(Exception e) {
				
			}

		}
		
		public Workbook getWorkbook() {
			return wb;
		}

		public XSSFSheet getSheet(String sheetName) {
			return wb.getSheet(sheetName);
		}

		//Fetching data fron excel sheet

		public String excelSheetValues(int excelSheetIndexNo, int excelRowNo, int excelColumnNo) {
			String exceldata = "";
			wb.getSheetAt(excelSheetIndexNo);
			DataFormatter formatter = new DataFormatter();
			exceldata = formatter.formatCellValue(sheet.getRow(excelRowNo).getCell(excelColumnNo));
			return exceldata.trim();
		}

		//Fetching data fron excel sheet

		public String excelSheetValues(String excelSheetIndexName, int excelRowNo, int excelColumnNo) {
			String exceldata = "";
			sheet = wb.getSheet(excelSheetIndexName);
			DataFormatter formatter = new DataFormatter();
			try {
				exceldata = formatter.formatCellValue(sheet.getRow(excelRowNo).getCell(excelColumnNo));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			return exceldata.trim();
		}

		//Excel Row Count

		private int excelRowCounts(String excelSheetName) {
			sheet = wb.getSheet(excelSheetName);
			excelRowCount = sheet.getLastRowNum();
			return excelRowCount;
		}

		//Excel Column count

		
		private int excelColumnCounts(String excelSheetName, int excelRowNO) {
			sheet = wb.getSheet(excelSheetName);
			excelRowCount = sheet.getRow(excelRowNO).getLastCellNum();
			excelRowCount = excelRowCount - 1;
			return excelRowCount;
		}

		//This function will take the parameter has excel sheet name and table name
		// @return start row index num for the perticular table

		public int getTableStartRowIndex(String excelSheetName, String tableName) {
			sheet = wb.getSheet(excelSheetName);
			List<XSSFTable> tables = sheet.getTables();
			for(XSSFTable table : tables) {
				if(table.getName().equalsIgnoreCase(tableName)) {
					startRowIndex = table.getStartRowIndex();
					break;
				}
			}
			return startRowIndex;		
		}

		
		//This function will take the parameter has excel sheet name and table name
		// @return start col index num for the perticular table

		public int getTableStartColIndex(String excelSheetName, String tableName) {
			sheet = wb.getSheet(excelSheetName);
			List<XSSFTable> tables = sheet.getTables();
			for(XSSFTable table : tables) {
				if(table.getName().equalsIgnoreCase(tableName)) {
					startColumnIndex = table.getStartColIndex();
					break;
				}
			}
			return startColumnIndex;		
		}	
		

		
		//This function will take the parameter has excel sheet name and table name
		// @return end row index num for the perticular table

		public int getTableEndRowIndex(String excelSheetName, String tableName) {
			sheet = wb.getSheet(excelSheetName);
			List<XSSFTable> tables = sheet.getTables();
			for(XSSFTable table : tables) {
				if(table.getName().equalsIgnoreCase(tableName)) {
					endRowIndex = table.getEndRowIndex();
					break;
				}
			}
			return endRowIndex;		
		}

		//This function will take the parameter has excel sheet name and table name
		// @return end col index num for the perticular table

		public int getTableEndColIndex(String excelSheetName, String tableName) {
			sheet = wb.getSheet(excelSheetName);
			List<XSSFTable> tables = sheet.getTables();
			for(XSSFTable table : tables) {
			if(table.getName().equalsIgnoreCase(tableName)) {
					endColumnIndex = table.getEndColIndex();
					break;
				}
			}
			return endColumnIndex;		
		}

		public XSSFTable getTable(String excelSheetName, String tableName) {
			sheet = wb.getSheet(excelSheetName);
			XSSFTable myTable=null;
			List<XSSFTable> tables = sheet.getTables();
			for(XSSFTable table : tables) {
			if(table.getName().equalsIgnoreCase(tableName)) {
				myTable=table;
					break;
				}
			}
			return myTable;		
		}

		public XSSFTable createTable(String excelSheetName, String tableName) {
			sheet = wb.getSheet(excelSheetName);
			XSSFTable myTable=null;
			myTable = sheet.createTable();
			return myTable;		
		}
		
		// Closing the workbook
		
		public void workbookClose() {
			try {
				fis.close();
				wb.close();
				wb = null;
			} catch(Exception e){}
		}

	}

