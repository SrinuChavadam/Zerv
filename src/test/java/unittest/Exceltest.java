package unittest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exceltest {

	
		String data=null;
	    String data2=null;
	Map<String, String> mp=new HashMap<String, String>();

	    List<String> arryLst=new ArrayList<String>();
	    FileInputStream inputStream;
	    public void data(String filename,String sheetname)
	    {

	        File file = new File(filename);
	    
	        try {
	            inputStream = new FileInputStream(file);
	        } catch (FileNotFoundException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	        Workbook wb = null;
		try {

			wb = new XSSFWorkbook(inputStream);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sh = wb.getSheet(sheetname);
		int rowCount = sh.getLastRowNum();

		for (int i = 1; i <= rowCount; i++) {

			Row row = sh.getRow(i);
			data = row.getCell(1).getStringCellValue();
			data2 = row.getCell(2).getStringCellValue();

			mp.put(data, data2);

		}

		for (@SuppressWarnings("rawtypes") Map.Entry temp : mp.entrySet()) {
			System.out.println("key :" + temp.getKey() + " Value-->" + temp.getValue());
		}

	}

	public static void main(String[] args) {

		Exceltest obj = new Exceltest();
		obj.data("D:\\DataTest\\sampletest.xlsx", "Data2");

	}
}
