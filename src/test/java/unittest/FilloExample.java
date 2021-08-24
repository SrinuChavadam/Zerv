package unittest;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FilloExample {
	public static void main(String[] args) throws FilloException {
		
	
	Fillo fillo=new Fillo();
	Connection connection=fillo.getConnection("./output/Test.xlsx");
	String strQuery="select * from table where username='miller'";
	Recordset recordset=connection.executeQuery(strQuery);
	 
	while(recordset.next()){
	System.out.println(recordset.getField("username"));
	}
	 
	recordset.close();
	connection.close();
}
}