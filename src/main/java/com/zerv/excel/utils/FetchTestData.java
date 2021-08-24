package com.zerv.excel.utils;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FetchTestData extends SpreadSheetBaseFunction {

	String filename = "./src/test/java/data/DFJobsReport.xlsx";
	String filename1 = "./src/test/java/data/DFReports.xlsx";
	String jobSheet = "./src/test/java/data/Jobs.xlsx";

	/**
	 * This method reads the default sheet in the given excel file and returns
	 * String array
	 * 
	 * @param excelFileName
	 *            - Excel File to be read
	 * @return - Object[][] - To be used in DataProvider
	 */
	public static Object[][] readExcel(String excelFileName) {

		String[][] data = null;

		try {
			FileInputStream fis = new FileInputStream("./src/test/java/data/" + excelFileName + ".xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);

			// get the number of rows
			int rowCount = sheet.getLastRowNum();

			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][columnCount];

			// loop through the rows
			for (int i = 1; i < rowCount + 1; i++) {
				try {
					XSSFRow row = sheet.getRow(i);
					for (int j = 0; j < columnCount; j++) { // loop through the columns
						try {
							String cellValue = "";
							try {
								cellValue = row.getCell(j).getStringCellValue().trim();
							} catch (NullPointerException e) {

							}

							data[i - 1][j] = cellValue; // add to the data array
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			fis.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

	/**
	 * This method writes ExecutedJobs list in given excel sheet
	 * 
	 * @param sheet
	 *            - SheetName
	 * @param jobname
	 *            - JobName
	 * @param jobstatus
	 *            - Job Status
	 * @param runnum
	 *            - Run Number
	 * @param starttime
	 *            - Job Initiated time
	 * @param endtime
	 *            - Job Ended time
	 * @param time
	 *            - Duration
	 * @throws InvalidFormatException 
	 */
	public void writeexcel(String sheet, String jobname, String jobstatus, String runnum, String starttime,
			String endtime, String time,String job_Avg,String percentage,String frequency) throws InvalidFormatException {
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		XSSFRow r = null;
		XSSFRow rr = null;

		File file = null;
		try {
			file = new File(filename1);
			if (file.exists()) {
				wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			} else {
				wb = new XSSFWorkbook();
			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i;

		if (wb.getNumberOfSheets() == 0) {
			wb.createSheet(sheet);
			sh = wb.getSheet(sheet);
		} else {

			for (i = 0; i < wb.getNumberOfSheets(); i++) {

				if (wb.getSheetName(i).equals(sheet)) {
					// System.out.println("i am in");
					sh = wb.getSheet(sheet);
					break;
				}

			}

			if (i == wb.getNumberOfSheets()) {
				wb.createSheet(sheet);
				sh = wb.getSheet(sheet);
			}
		}

		rr = sh.createRow(0);

		rr.createCell(0).setCellValue("Jobname");
		rr.createCell(1).setCellValue("JobStatus");
		rr.createCell(2).setCellValue("RunNumber");
		rr.createCell(3).setCellValue("Server_Initiated_Time");
		rr.createCell(4).setCellValue("Server_Ended_Time");
		rr.createCell(5).setCellValue("Duration_Job");
		rr.createCell(6).setCellValue("Avg Time(s)");
		rr.createCell(7).setCellValue("Health Index(%)");
		rr.createCell(8).setCellValue("Frequency");
		
		int rowcount = sh.getLastRowNum();

	
		r = sh.createRow(rowcount + 1);

		r.createCell(0).setCellValue(jobname);
		r.createCell(1).setCellValue(jobstatus);
		r.createCell(2).setCellValue(runnum);
		r.createCell(3).setCellValue(starttime);
		r.createCell(4).setCellValue(endtime);
		r.createCell(5).setCellValue(time);
		r.createCell(6).setCellValue(job_Avg);
		r.createCell(7).setCellValue(percentage);
		r.createCell(8).setCellValue(frequency);
		
		
		
		try {
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.flush();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method writes the Job Dependencies
	 * 
	 * @param sheet
	 *            - sheetName
	 * @param jobname
	 *            - JobName
	 * @param dependencies
	 *            - List of Dependent Jobs
	 * @throws InvalidFormatException 
	 */
	public void writeexcel(String sheet, String jobname, List<String> dependencies) throws InvalidFormatException {
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		XSSFRow r = null;
		XSSFRow rr = null;

		File file = null;
		try {
			file = new File(filename);
			if (file.exists()) {
				wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			} else {
				wb = new XSSFWorkbook();
			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i;

		if (wb.getNumberOfSheets() == 0) {
			wb.createSheet(sheet);
			sh = wb.getSheet(sheet);
		} else {

			for (i = 0; i < wb.getNumberOfSheets(); i++) {

				if (wb.getSheetName(i).equals(sheet)) {
					// System.out.println("i am in");
					sh = wb.getSheet(sheet);
					break;
				}

			}

			if (i == wb.getNumberOfSheets()) {
				wb.createSheet(sheet);
				sh = wb.getSheet(sheet);
			}
		}

		rr = sh.createRow(0);

		rr.createCell(0).setCellValue("Jobname");
		rr.createCell(1).setCellValue("Dependency List");

		int rowcount = sh.getLastRowNum();

		r = sh.createRow(rowcount + 1);

		r.createCell(0).setCellValue(jobname);
		r.createCell(1).setCellValue(dependencies.toString());

		try {
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.flush();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method writes the Job Command Strings
	 * 
	 * @param sheet
	 *            - sheetName
	 * @param jobname
	 *            - JobName
	 * @param commands
	 *            - List of Command strings used
	 * @param wrkdir
	 *            - Working directory
	 * @throws InvalidFormatException 
	 */
	public void writeexcel(String sheet, String jobname, String wrkdir, List<String> commands) throws InvalidFormatException {

		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		XSSFRow r = null;
		XSSFRow rr = null;

		File file = null;
		try {
			file = new File(filename);
			if (file.exists()) {
				wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			} else {
				wb = new XSSFWorkbook();
			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i;

		if (wb.getNumberOfSheets() == 0) {
			wb.createSheet(sheet);
			sh = wb.getSheet(sheet);
		} else {

			for (i = 0; i < wb.getNumberOfSheets(); i++) {

				if (wb.getSheetName(i).equals(sheet)) {
					// System.out.println("i am in");
					sh = wb.getSheet(sheet);
					break;
				}

			}

			if (i == wb.getNumberOfSheets()) {
				wb.createSheet(sheet);
				sh = wb.getSheet(sheet);
			}
		}

		rr = sh.createRow(0);

		rr.createCell(0).setCellValue("Jobname");
		rr.createCell(1).setCellValue("Working Directory");
		rr.createCell(2).setCellValue("Command Strings");

		int rowcount = sh.getLastRowNum();

		r = sh.createRow(rowcount + 1);

		r.createCell(0).setCellValue(jobname);
		r.createCell(1).setCellValue(wrkdir);
		r.createCell(2).setCellValue(commands.toString());

		try {
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.flush();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * This method writes the Failed , Completed list of jobs with its Periodicity , run number
	 * 
	 * @param sheet
	 *            - SheetName
	 * @param jobname
	 *            - JobName
	 * @param period
	 *            - Periodicity
	 * @throws InvalidFormatException 
	 */
	public void writeexcel(String sheet, String jobname, String period,String run_nr) throws InvalidFormatException {
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		XSSFRow r = null;
		XSSFRow rr = null;

		File file = null;
		try {
			if(sheet.contains("Failed"))
				{
					file = new File(filename1);	
				}
				else
				{
					file = new File(filename);
				}
			if (file.exists()) {
				wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			} else {
				wb = new XSSFWorkbook();
			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i;

		if (wb.getNumberOfSheets() == 0) {
			wb.createSheet(sheet);
			sh = wb.getSheet(sheet);
		} else {

			for (i = 0; i < wb.getNumberOfSheets(); i++) {

				if (wb.getSheetName(i).equals(sheet)) {
					// System.out.println("i am in");
					sh = wb.getSheet(sheet);
					break;
				}

			}

			if (i == wb.getNumberOfSheets()) {
				wb.createSheet(sheet);
				sh = wb.getSheet(sheet);
			}
		}

		rr = sh.createRow(0);

		rr.createCell(0).setCellValue("Jobname");
		rr.createCell(1).setCellValue("Periodicity");
		rr.createCell(2).setCellValue("Run_Nr");

		int rowcount = sh.getLastRowNum();

		r = sh.createRow(rowcount + 1);

		r.createCell(0).setCellValue(jobname);
		r.createCell(1).setCellValue(period);
		r.createCell(2).setCellValue(run_nr);

		try {
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.flush();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method generates graph in excel sheets using VB Scripting
	 */
	public void generateGraph() {
		try {
			Runtime.getRuntime().exec("wscript D:\\vbs\\graph.vbs");
		} catch (IOException e) {
			System.exit(0);
		}
	}

	/**
	 * This method removes the row data in given sheet
	 * @param sheetName
	 * @throws InvalidFormatException 
	 */
	public void removeRows(String filename,String sheetName)  {
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		File file = null;
		try {
			file = new File("./src/test/java/data/" + filename + ".xlsx");
			if (file.exists()) {
				wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			} else {
				wb = new XSSFWorkbook();
			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int index = wb.getSheetIndex(sheetName);
		sh = wb.getSheetAt(index);
		int lastRowNum = sh.getLastRowNum();
		for (int i = 0; i <= lastRowNum; i++) {
			if ((sh.getRow(i)) != null) {
				sh.removeRow(sh.getRow(i));
			}

		}

		try {
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.flush();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * This method writes the job names from ExecutedJobs script to Jobs Sheet - Test Data Input 
	 * @param sheet
	 * @param jobname
	 * @throws InvalidFormatException
	 */
	public void writeexcel(String sheet, String jobname) throws InvalidFormatException {
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		XSSFRow r = null;
		XSSFRow rr = null;

		File file = null;
		try {
			file = new File(jobSheet);
			if (file.exists()) {
				wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			} else {
				wb = new XSSFWorkbook();
			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i;

		if (wb.getNumberOfSheets() == 0) {
			wb.createSheet(sheet);
			sh = wb.getSheet(sheet);
		} else {

			for (i = 0; i < wb.getNumberOfSheets(); i++) {

				if (wb.getSheetName(i).equals(sheet)) {
					// System.out.println("i am in");
					sh = wb.getSheet(sheet);
					break;
				}

			}

			if (i == wb.getNumberOfSheets()) {
				wb.createSheet(sheet);
				sh = wb.getSheet(sheet);
			}
		}

		rr = sh.createRow(0);

		rr.createCell(0).setCellValue("JobName");
				int rowcount = sh.getLastRowNum();

		r = sh.createRow(rowcount + 1);

		r.createCell(0).setCellValue(jobname);

		try {
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.flush();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	///////////
	
	
	
	public void writehealth(String sheet,String date, String total,String completed,String failed) throws InvalidFormatException {
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		XSSFRow r = null;
		XSSFRow rr = null;

		File file = null;
		try {
			file = new File(filename1);
			if (file.exists()) {
				wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			} else {
				wb = new XSSFWorkbook();
			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i;

		if (wb.getNumberOfSheets() == 0) {
			wb.createSheet(sheet);
			sh = wb.getSheet(sheet);
		} else {

			for (i = 0; i < wb.getNumberOfSheets(); i++) {

				if (wb.getSheetName(i).equals(sheet)) {
					// System.out.println("i am in");
					sh = wb.getSheet(sheet);
					break;
				}

			}

			if (i == wb.getNumberOfSheets()) {
				wb.createSheet(sheet);
				sh = wb.getSheet(sheet);
			}
		}

		/*rr = sh.createRow(0);

		rr.createCell(0).setCellValue("Date");
		rr.createCell(1).setCellValue("Unique Jobs");
		rr.createCell(2).setCellValue("Completed");
		rr.createCell(3).setCellValue("End of Failure");
		rr.createCell(4).setCellValue("% Failure");
		rr.createCell(5).setCellValue("Health of Skybot");
		
*/		
		int rowcount = sh.getLastRowNum();
		      
		r = sh.createRow(rowcount + 1);

		r.createCell(0).setCellValue(date);
		r.createCell(1).setCellValue(Integer.valueOf(total).intValue());
		r.createCell(2).setCellValue(Integer.valueOf(completed).intValue());
		r.createCell(3).setCellValue(Integer.valueOf(failed).intValue());
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(wb.createDataFormat().getFormat("0.000%"));

		String strFormula="(D"+String.valueOf(rowcount+2)+"/B"+String.valueOf(rowcount+2)+")";
		XSSFCell createCell = r.createCell(4);
		createCell.setCellStyle(style);
		createCell.setCellFormula(strFormula);
		
		//r.createCell(4).setCellFormula(strFormula);
		String strFormula1="1-(E"+String.valueOf(rowcount+2)+")";
		XSSFCell createCell1 = r.createCell(5);
		createCell1.setCellStyle(style);
		createCell1.setCellFormula(strFormula1);
		//r.createCell(5).setCellFormula(strFormula1);
			
		try {
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.flush();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
