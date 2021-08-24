package com.zerv.excel.utils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;

public class SpreadSheetBaseFunction extends SpreadsheetReader {

	public static String subCategory;
	public static String fieldLabel;
	public static String fieldValue;
	public static String SUBCATEGORY = "Subcategory";
	public static String FIELDLABEL = "Field name";
	public static int startColumnIndex;
	public static int endColumnIndex;
	public static int startRowIndex;
	public static int endRowIndex;
	public String excelPath;
	

	public String customProfileExcelPath(String filename) {
		excelPath = "./src/test/java/data/" + filename + ".xlsx";
		return excelPath;
	}



	/**
	 * 
	 * @param screenName
	 *            - Excel sheet name
	 * @param tableName
	 *            - Excel table name
	 * @param value
	 *            - integer test data index
	 * @return
	 */
	public Map<String, String> getTableValues(String screenName, String tableName, int value) {
		 excelPath(customProfileExcelPath());
		startRowIndex = getTableStartRowIndex(screenName, tableName);
		endRowIndex = getTableEndRowIndex(screenName, tableName);
		/*
		 * int accountNumber = getSheetColumnNum(screenName, tableName,
		 * value);System.out.println(accountNumber); int fieldName =
		 * getSheetColumnNum(screenName, tableName,
		 * FIELDLABEL);System.out.println(fieldName);
		 */
		Map<String, String> fieldLabelValue = new LinkedHashMap<String, String>();
		for (int i = startRowIndex + 1; i <= endRowIndex; i++) {
			fieldLabel = excelSheetValues(screenName, i, 0);
			fieldValue = excelSheetValues(screenName, i, value);
			fieldLabelValue.put(fieldLabel, fieldValue);
		}
		workbookClose();
		return fieldLabelValue;
	}

	public void writeData(String filename, String screenName, String tableName, List<List<String>> data) {
		excelPath(customProfileExcelPath(filename));
		startRowIndex = getTableStartRowIndex(screenName, tableName);
		endRowIndex = getTableEndRowIndex(screenName, tableName);
		startColumnIndex = getTableStartColIndex(screenName, tableName);
		endColumnIndex = getTableEndColIndex(screenName, tableName);

		XSSFTable table = getTable(screenName, tableName);
		CTTable ctTable = table.getCTTable();
		AreaReference my_data_range = new AreaReference(new CellReference(startRowIndex, startColumnIndex),
				new CellReference(endRowIndex, endColumnIndex), null);

		/** Set Range to the Table */
		// ctTable.setRef(my_data_range.formatAsString());
		// ctTable.setDisplayName(tableName); /* this is the display name of the table
		// */
		// ctTable.setName(tableName);
		// ctTable.setId(1L);
		int datasize = 0;
		for (List<String> list : data) {
			datasize = list.size();
			break;
		}

		/*
		 * // setting column headers if (datasize > 5) { setFirstPeriodRow(datasize);
		 * 
		 * } else { setFirstRow(datasize); }
		 */

		int i = startRowIndex + 1;
		/* Add remaining Table Data */
		for (List<String> list : data) {
			/* Create a Row */
			XSSFRow row = sheet.createRow(i);
			for (int j = 0; j < datasize; j++) // Three columns in each row
			{
				XSSFCell localXSSFCell = row.createCell(j);
				if (j == 0) {
					localXSSFCell.setCellValue(list.get(j));
				} else {
					localXSSFCell.setCellValue(Integer.valueOf(list.get(j)).intValue());
				}
			}
			i = i + 1;
		}
		excelWritePath(customProfileExcelPath(filename));

	}

	private void setFirstRow(int datasize) {
		XSSFRow firstRow = sheet.createRow(startRowIndex);
		for (int j = 0; j < datasize; j++) // Three columns in each row
		{
			XSSFCell localXSSFCell = firstRow.createCell(j);
			switch (j) {
			case 0:
				localXSSFCell.setCellValue("Date");
				break;
			case 1:
				localXSSFCell.setCellValue("Total");
				break;
			case 2:
				localXSSFCell.setCellValue("Completed");
				break;
			case 3:
				localXSSFCell.setCellValue("Failed");
				break;
			case 4:
				localXSSFCell.setCellValue("FailedJobs");
				break;
			default:
				break;
			}
		}
	}

	private void setFirstPeriodRow(int datasize) {
		XSSFRow firstRow = sheet.createRow(startRowIndex);
		for (int j = 0; j < datasize; j++) // Three columns in each row
		{
			XSSFCell localXSSFCell = firstRow.createCell(j);
			switch (j) {
			case 0:
				localXSSFCell.setCellValue("Date");
				break;
			case 1:
				localXSSFCell.setCellValue("DAILY");
				break;
			case 2:
				localXSSFCell.setCellValue("WEEKLY");
				break;
			case 3:
				localXSSFCell.setCellValue("DEMAND");
				break;
			case 4:
				localXSSFCell.setCellValue("MONTHLY");
				break;
			case 5:
				localXSSFCell.setCellValue("CRON");
				break;
			case 6:
				localXSSFCell.setCellValue("OTHERS");
				break;
			case 7:
				localXSSFCell.setCellValue("TOTAL");
				break;
			default:
				break;
			}
		}
	}

	public void writeJobData(String filename, String screenName, String tableName, List<List<String>> data) {
		excelPath(customProfileExcelPath(filename));
		startRowIndex = getTableStartRowIndex(screenName, tableName);
		endRowIndex = getTableEndRowIndex(screenName, tableName);
		startColumnIndex = getTableStartColIndex(screenName, tableName);
		endColumnIndex = getTableEndColIndex(screenName, tableName);

		XSSFTable table = getTable(screenName, tableName);
		CTTable ctTable = table.getCTTable();
		AreaReference my_data_range = new AreaReference(new CellReference(startRowIndex, startColumnIndex),
				new CellReference(endRowIndex, endColumnIndex), null);

		/** Set Range to the Table */
		// ctTable.setRef(my_data_range.formatAsString());
		ctTable.setDisplayName(tableName); /* this is the display name of the table */
		ctTable.setName(tableName);
		// ctTable.setId(1L);
		int datasize = 0;
		for (List<String> list : data) {
			datasize = list.size();
			break;
		}

		int i = startRowIndex + 1;

		/* Add remaining Table Data */
		for (List<String> list : data) {
			/* Create a Row */
			XSSFRow row = sheet.createRow(i);
			int k = startColumnIndex;
			for (int j = 0; j < datasize; j++) // Three columns in each row
			{
				XSSFCell localXSSFCell = row.createCell(k);
				localXSSFCell.setCellValue(list.get(j));
				k = k + 1;

			}
			i = i + 1;
		}
		excelWritePath(customProfileExcelPath(filename));

	}

	/*
	 * public int getSheetRownNum(String screenName, String tableName, String
	 * rowName) { startRowIndex = getTableStartRowIndex(screenName, tableName);
	 * System.out.println(startRowIndex + "startRowIndex"); endRowIndex =
	 * getTableEndRowIndex(screenName, tableName);System.out.println(endRowIndex
	 * +"endRowIndex"); boolean flag = false; int i; for(i = startRowIndex; i <=
	 * endRowIndex; i++) { fieldLabel = excelSheetValues(screenName, startRowIndex,
	 * i);System.out.println(fieldLabel); if(fieldLabel.equalsIgnoreCase(rowName)) {
	 * flag = true; break; }
	 * 
	 * } if( flag == false ) { //log } return i; }
	 */

	public Map<String, String> getTablValues(String filename, String screenName, String tableName, String column) {
		excelPath(customProfileExcelPath(filename));
		startRowIndex = getTableStartRowIndex(screenName, tableName);
		endRowIndex = getTableEndRowIndex(screenName, tableName);
		int colIndex = getSheetColumnNum(screenName, tableName, column);
		Map<String, String> fieldLabelValue = new LinkedHashMap<String, String>();

		for (int i = startRowIndex + 1; i <= endRowIndex; i++) {
			fieldLabel = excelSheetValues(screenName, i, 1);
			fieldValue = excelSheetValues(screenName, i, colIndex);
			fieldLabelValue.put(fieldLabel, fieldValue);
		}
		workbookClose();
		return fieldLabelValue;
	}

	public List<String> getTableFieldValue(String filename, String screenName, String tableName, String value) {
		excelPath(customProfileExcelPath(filename));
		startColumnIndex = getTableStartColIndex(screenName, tableName);
		endColumnIndex = getTableEndColIndex(screenName, tableName);
		startRowIndex = getTableStartRowIndex(screenName, tableName);
		endRowIndex = getTableEndRowIndex(screenName, tableName);
		List<String> fieldLabelList = new ArrayList<String>();
		for (int i = startRowIndex + 1; i <= endRowIndex; i++) {
			fieldLabel = excelSheetValues(screenName, i, startColumnIndex);
			if (fieldLabel.equalsIgnoreCase(value)) {
				for (int j = startColumnIndex + 1; j <= endColumnIndex; j++) {
					fieldValue = excelSheetValues(screenName, i, j);
					fieldLabelList.add(fieldValue);
				}
				break;
			}

		}
		workbookClose();
		return fieldLabelList;
	}

	/**
	 * Removing Table Data
	 */

	public void RemoveTable(String filename, String sheetname, String tablename) {
		excelPath(customProfileExcelPath(filename));

		int a = getTableStartRowIndex(sheetname, tablename);
		int b = getTableEndRowIndex(sheetname, tablename);
		int c = getTableStartColIndex(sheetname, tablename);
		int d = getTableEndColIndex(sheetname, tablename);
		sheet = wb.getSheet(sheetname);
		for (int k = a + 1; k <= b; k++) {

			XSSFRow row = sheet.getRow(k);
			for (int m = c; m <= d; m++) {

				// XSSFCell cell = row.getCell(m);

				try {
					if (row.getCell(m) != null || row.getCell(m).getStringCellValue().length() != 0) {
						row.removeCell(row.getCell(m));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}

			}
		}

		excelWritePath(customProfileExcelPath(filename));

	}

	/**
	 * getting index based on value match
	 */

	public int getindex(String filename, String screenName, String tableName, String value) {
		excelPath(customProfileExcelPath(filename));
		startColumnIndex = getTableStartColIndex(screenName, tableName);
		endColumnIndex = getTableEndColIndex(screenName, tableName);
		startRowIndex = getTableStartRowIndex(screenName, tableName);
		endRowIndex = getTableEndRowIndex(screenName, tableName);
		int j = 0;
		for (int i = startRowIndex + 1; i <= endRowIndex; i++) {
			fieldLabel = excelSheetValues(screenName, i, startColumnIndex);
			if (fieldLabel.equalsIgnoreCase(value)) {
				j = i;
				break;
			}

		}
		workbookClose();
		return j;

	}

	/**
	 * Row updation for given index
	 */

	public void updateRow(String filename, String sheetname, String tablename, int index, List<String> total) {
		excelPath(customProfileExcelPath(filename));

		int a = getTableStartRowIndex(sheetname, tablename);
		int b = getTableEndRowIndex(sheetname, tablename);
		int c = getTableStartColIndex(sheetname, tablename);
		int d = getTableEndColIndex(sheetname, tablename);
		sheet = wb.getSheet(sheetname);
		sheet.getRow(index).getCell(0).setCellValue(total.get(0));
		sheet.getRow(index).getCell(1).setCellValue(total.get(1));
		sheet.getRow(index).getCell(2).setCellValue(total.get(2));
		sheet.getRow(index).getCell(3).setCellValue(total.get(3));
		sheet.getRow(index).getCell(4).setCellValue(total.get(4));
		sheet.getRow(index).getCell(5).setCellValue(total.get(5));

		excelWritePath(customProfileExcelPath(filename));

	}

	public Set<String> getSubCategory(String filename, String screenName, String tableName, String colName) {
		excelPath(customProfileExcelPath(filename));
		startRowIndex = getTableStartRowIndex(screenName, tableName);
		endRowIndex = getTableEndRowIndex(screenName, tableName);
		Set<String> jobSet = new LinkedHashSet<>();
		for (int i = startRowIndex + 1; i <= endRowIndex; i++) {
			subCategory = excelSheetValues(screenName, i, getSheetColumnNum(screenName, tableName, colName));
			jobSet.add(subCategory);
		}
		workbookClose();
		return jobSet;
	}
	
	/**
	 * Method will give last row in table
	 * 
	 */
	
	public int getlastrow(String filename, String sheetname, String tablename) {
		excelPath(customProfileExcelPath(filename));
		sheet = wb.getSheet(sheetname);
     	int last=sheet.getLastRowNum();
     	return last;
	}
	
	/**
	 * Method will update last row in table
	 */
	
	public void updateLastRow(String filename, String sheetname, String tablename, int index, List<String> total) {
		excelPath(customProfileExcelPath(filename));
		sheet = wb.getSheet(sheetname);
		XSSFRow row=sheet.createRow(index+1);
		
		row.createCell(0).setCellValue(total.get(0));
		row.createCell(1).setCellValue(total.get(1));
		row.createCell(2).setCellValue(total.get(2));
		row.createCell(3).setCellValue(total.get(3));
		row.createCell(4).setCellValue(total.get(4));
		row.createCell(5).setCellValue(total.get(5));
		excelWritePath(customProfileExcelPath(filename));

	}

	public static String tag;

	public static String TAG_NAME = "TAG_NAME";



	public String customProfileExcelPath1() {
		String excelPath;
		System.out.println();
		//excelPath = "./src/test/resources/ScienceXmlSheets/ScienceInput.xlsx";
		excelPath = "./src/test/java/data/TestData_AMP-JEDI.xlsx";
		//System.out.println(excelPath);
		return excelPath;
	}
	
	public String customProfileExcelPath() {
		String excelPath;
		System.out.println();
		excelPath = "./InputFiles/TestData/TestData.xlsx";
		//excelPath = "C:\\Users\\43419\\Desktop\\TestData_AMP-JEDI.xlsx";
		//System.out.println(excelPath);
		return excelPath;
	}
	



	/**
	 * 
	 * @param screenName - Excel sheet name
	 * @param tableName - Excel table name
	 * @param columnName - Filed name in the first coloun 
	 * @return it return the index for test data
	 */
	public int getSheetColumnNum(String screenName, String tableName, String columnName) {
		startColumnIndex = getTableStartColIndex(screenName, tableName);//System.out.println(startColumnIndex +"startColumnIndex");
		endColumnIndex = getTableEndColIndex(screenName, tableName);//System.out.println(endColumnIndex + "endColumnIndex");
		int startrowIndex= getTableStartRowIndex(screenName, tableName);
		boolean flag = false;
		int i;
		for(i = startColumnIndex; i <= endColumnIndex; i++) {
			fieldLabel = excelSheetValues(screenName, startrowIndex,  i);//System.out.println(fieldLabel);
			if(fieldLabel.equalsIgnoreCase(columnName)) {				
				flag = true;
				break;
			}			
			
		}
		if( flag == false ) {
		//log
		}
		return i;
	}
	
	
	
	public String getFieldLabelValue(String screenName, String tableName, String PermId, String tagValue) {
		excelPath(customProfileExcelPath());
		startRowIndex = getTableStartRowIndex(screenName, tableName);
		endRowIndex = getTableEndRowIndex(screenName, tableName);
		int	permIDIndex = getSheetColumnNum(screenName, tableName, PermId);		
		for(int i = startRowIndex+1; i <= endRowIndex; i++) {
			tag = excelSheetValues(screenName, i, getSheetColumnNum(screenName, tableName, TAG_NAME));
			if(tag.equals(tagValue)) 
			{
				
				fieldValue = excelSheetValues(screenName, i, permIDIndex).trim();
				
				
								
			}
	
		}
		workbookClose();
		return fieldValue;
	}
	
	public List<String> getFieldValues(String screenName, String tableName, String PermId, String tagValue) {
		List<String> valueList=new ArrayList<String>();
		excelPath(customProfileExcelPath());
		startRowIndex = getTableStartRowIndex(screenName, tableName);
		endRowIndex = getTableEndRowIndex(screenName, tableName);
		int	permIDIndex = getSheetColumnNum(screenName, tableName, PermId);	
		int tagColIndex= getSheetColumnNum(screenName, tableName, TAG_NAME);
		for(int i = startRowIndex+1; i <= endRowIndex; i++) {
			fieldValue="";
			tag = excelSheetValues(screenName, i,tagColIndex);
			if(tag.equals(tagValue)) {
				fieldValue = excelSheetValues(screenName, i, permIDIndex);
				if(fieldValue.isEmpty())
                {
                       
                       //valueList.add("null");
                }
                
              else
                {
                       
                valueList.add(fieldValue);
                }

				/*if(fieldValue.equals(" "))
				{
					
				}
				else
				{
				valueList.add(fieldValue);
				}
		*/	}
	
		}
		workbookClose();
		return valueList;
					
	}
	
	
	
		public List<String> getFieldValuesPatronym(String screenName, String tableName, String PermId, String tagValue) {
				List<String> valueList=new ArrayList<String>();
				excelPath(customProfileExcelPath());
				startRowIndex = getTableStartRowIndex(screenName, tableName);
				endRowIndex = getTableEndRowIndex(screenName, tableName);
				int	permIDIndex = getSheetColumnNum(screenName, tableName, PermId);	
				int tagColIndex= getSheetColumnNum(screenName, tableName, TAG_NAME);
				for(int i = startRowIndex+1; i <= endRowIndex; i++) {
					fieldValue="";
					tag = excelSheetValues(screenName, i,tagColIndex);
					if(tag.equals(tagValue)) {
						fieldValue = excelSheetValues(screenName, i, permIDIndex);
						if(fieldValue.isEmpty())
						{
							
							valueList.add("null");
						}
						else
						{
							
						valueList.add(fieldValue);
						}
					}
			
				}
				workbookClose();
				return valueList;
			} 
		 

	
	public List<String> getTablename(String screenName, String tableName)
	{
		List<String> permid=new ArrayList<String>();
		excelPath(customProfileExcelPath());
		startColumnIndex = getTableStartColIndex(screenName, tableName);//System.out.println(startColumnIndex +"startColumnIndex");
		endColumnIndex = getTableEndColIndex(screenName, tableName);//System.out.println(endColumnIndex + "endColumnIndex");
		sheet = wb.getSheet(screenName);
			
		for(int k=startColumnIndex+1;k<=endColumnIndex;k++)
		{
			permid.add(sheet.getRow(0).getCell(k).getStringCellValue());
			//System.out.println(sheet.getRow(0).getCell(k).getStringCellValue());
			
		}
		
		return permid;
		
	}
	
	

	/**
	 * This method will get all PiPermid from table
	 * @param filename- Name of Excel File
	 * @param sheetname- Name of Sheet
	 * @param tablename- Name of Table
	 * @return
	 */
	public Set<String> getpiPermid(String filename,String sheetname,String tablename)
	{
		
		excelPath(customProfileExcelPath(filename));
		int a=getTableStartRowIndex(sheetname,tablename);
		int b=getTableEndRowIndex(sheetname,tablename);
		int d=getTableEndColIndex(sheetname,tablename);
		Set<String> permid = new HashSet<String>();
		
		for(int i=a+1;i<=b;i++)
		{
			String s=sheet.getRow(i).getCell(d).toString();
			permid.add(s);
			
			//System.out.println(s);
		}
		return permid;
	}

	/**
	 * 
	 * public Map<String, String> getFieldLabelValue(String screenName, String
	 * tableName, String AccountNumber, String SubCatValue) { boolean flag = false;
	 * excelPath(customProfileExcelPath(envSheetName)); startRowIndex =
	 * getTableStartRowIndex(screenName, tableName); endRowIndex =
	 * getTableendRowIndex(screenName, tableName); accountNumber =
	 * getSheetColumnNum(screenName, tableName, AccountNumber); Map<String, String>
	 * fieldLabelValue = new LinkedHashMap<>(); for(int i = startRowIndex+1; i <=
	 * endRowIndex; i++) { subCategory = excelSheetValues(screenNmae, i,
	 * getSheetColumnNum(scrrenName, tableNmae, SUBCATEGORY));
	 * if(subCategort.equals(SubCatValue)) { fieldLabel =
	 * excelSheetValues(screenNmae, i, getSheetColumnNum(scrrenName, tableNmae,
	 * FIELDLABEL)); fieldValue = excelSheetValues(screenNmae, i, accountNumber);
	 * fieldLabelValue.put(fieldLabel, fieldValue); flag = true; }
	 * if(!(subCategort.equals(SubCatValue)) && (flag == true)) { break; }
	 * 
	 * } workbookClose(); return fieldLableValue; }
	 * 
	 * public List<String> getTableFieldLabel(String screenName, String tableName) {
	 * excelPath(customProfileExcelPath(envSheetName)); startColumnIndex =
	 * getTableStartColumnIndex(screenName, tableName); endColumnIndex =
	 * getTableendColumnIndex(screenName, tableName); startRowIndex =
	 * getTableStartRowIndex(screenName, tableName); List<String> fieldLabelList =
	 * new ArrayList<>(); for(int i = startColumnIndex+1; i <= endColumnIndex; i++)
	 * { fieldLabel = excelSheetValues(screenNmae, startRowIndex, i);
	 * fieldLabelList.add(fieldLabel); } workbookClose(); return fieldLableList; }
	 * 
	 * public List<String> getTableFieldLabel(String screenName, String tableName,
	 * String value) { excelPath(customProfileExcelPath(envSheetName));
	 * startColumnIndex = getTableStartColumnIndex(screenName, tableName);
	 * endColumnIndex = getTableendColumnIndex(screenName, tableName); startRowIndex
	 * = getTableStartRowIndex(screenName, tableName); endRowIndex =
	 * getTableendRowIndex(screenName, tableName); List<String> fieldLabelList = new
	 * ArrayList<>(); for(int i = startRowIndex+1; i <= endRowIndex; i++) {
	 * fieldLabel = excelSheetValues(screenNmae, i, startColumnIndex);
	 * if(fieldLabel.equalsIgnoreCase(value)) { for(int j = startColumnIndex+1; j <=
	 * endColumnIndex; j++) { fieldValue = excelSheetValues(screenNmae, i, j);
	 * fieldLabelList.add(fieldValue); } break; }
	 * 
	 * } workbookClose(); return fieldLableList; }
	 * 
	 * public String[][] getTableAllFieldVaue(String screenName, String tableName) {
	 * excelPath(customProfileExcelPath(envSheetName)); startColumnIndex =
	 * getTableStartColumnIndex(screenName, tableName); endColumnIndex =
	 * getTableendColumnIndex(screenName, tableName); startRowIndex =
	 * getTableStartRowIndex(screenName, tableName); endRowIndex =
	 * getTableendRowIndex(screenName, tableName); int rowSize =
	 * endRowIndex-startRowIndex; int columnSize =
	 * endColumnIndex-startColumnIndex+1; String[][] allServiceValues = new
	 * String[rowSize][columnSize]; int m = 0; for(int i = startRowIndex; i <=
	 * endRowIndex; i++) { int n = 0; for(int j = startColumnIndex+1; j <=
	 * endColumnIndex; j++) { allServiceValues[m][n] = excelSheetValues(screenNmae,
	 * i+1, j); n++; } m++; } workbookClose(); return allServiceValues; }
	 * 
	 * public String[] keyName(String keyValue) {
	 * excelPath(customProfileExcelPath(ExcelConfig.testDataExcelPath)); String[]
	 * allServiceValues = new String[] {}; startColumnIndex =
	 * getTableStartColumnIndex(ExcelConfig.testDataExcelName,
	 * ExcelConfig.testDataExcelName); endColumnIndex =
	 * getTableendColumnIndex(ExcelConfig.testDataExcelName,
	 * ExcelConfig.testDataExcelName); startRowIndex =
	 * getTableStartRowIndex(ExcelConfig.testDataExcelName,
	 * ExcelConfig.testDataExcelNmae); endRowIndex =
	 * getTableendRowIndex(ExcelConfig.testDataExcelName,
	 * ExcelConfig.testDataExcelName); for(int i = startRowIndex+1; i <=
	 * endRowIndex; i++) { fieldLabel =
	 * excelSheetValues(ExcelConfig.testDataExcelName, i, startColumnIndex);
	 * if(fieldLabel.equalsIgnoreCase(keyValue)) { fieldLabel =
	 * excelSheetValues(ExcelConfig.testDataExcelName, i, endColumnIndex);
	 * if(fieldLabel.contains(";")) { testData = fieldLabel.split(";"); }else {
	 * testData = new String[] { fieldLabel }; } break;; }
	 * 
	 * } workbookClose(); return testData; }
	 * 
	 * public Map<String, String> getData(String tableName, String value) {
	 * Map<String, String> tdata = new HashMap<>();
	 * excelPath(customProfileExcelPath(envSheetName)); String screenName =
	 * ExcelConfig.testDataExcelName; startColumnIndex =
	 * getTableStartColumnIndex(screenName, tableName); endColumnIndex =
	 * getTableendColumnIndex(screenName, tableName); startRowIndex =
	 * getTableStartRowIndex(screenName, tableName); endRowIndex =
	 * getTableendRowIndex(screenName, tableName); for(int i = startRowIndex+1; i <=
	 * endRowIndex; i++) { fieldLabel = excelSheetValues(screenName, i,
	 * startColumnIndex); if(fieldLabel.equalsIgnoreCase(keyValue)) { fieldValue =
	 * excelSheetValues(screenName, i, endColumnIndex); tdata.put(fieldLabel,
	 * fieldValue); }
	 * 
	 * } return rdata; }
	 * 
	 */

}
