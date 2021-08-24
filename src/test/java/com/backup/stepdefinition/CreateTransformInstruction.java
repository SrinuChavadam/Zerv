package com.backup.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.*;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.zerv.api.utils.PUBOBAPIUtilities;
import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.runner.TestRunner;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateTransformInstruction extends TestRunner{
	
	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;	
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();
	String publicationId;
	
	
	 
	@Given("^am a Transform_Instruction Team member \"(.*?)\"$")
	public void getConnection(String endpoint)throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));
		
	}

	@When("^user provides a publicationID along with Transform Instructions \"(.*?)\" and \"(.*?)\" and \"(.*?)\" values$")
	public void getResponse(String fileNames, String transforms,String deliveredFolder) throws Throwable {
	  		
		
		 String query="select publication_id from format_evaluation";
	      Set<String> publicationIDs = db.getDbListValues(query, "publication_id");
	         for (String value : publicationIDs)
	         {    	        	
	             publicationId=value;
	        	 
	        String query1="select count(*) as count from transformation_instructions where publication_id='"+publicationId+ "'";
	        String query2="select count(*) as count from supplement_files where publication_id='"+publicationId+ "'";
	        int count=db.getRowCount(query1);
	        int count1=db.getRowCount(query2);
	        if(count==0&&count1==0)
	        {
	           	        	
	        	String createTransformInstruction = api.createTransformInstructions(fileNames, transforms, deliveredFolder);
	     		response=request.body(createTransformInstruction.toString()).post(publicationId);
	     		
	        break;
	        }
	        else	           
	        System.out.println("Record with publicationid" +value+ "is already present");
	         }
		   	    		 		 		 
		 Reporter.addStepLog(response.prettyPrint());
	}
	
		

	@Then("^user should get a successful response for Transform_Instruction$")
	public void validateStatus() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 200 || statusCode == 201, "status code is not correct");
	}
	
	
	@Then("^new record should be created in Transform_Instruction table and validate aganist Database along with\"(.*?)\" and \"(.*?)\" and \"(.*?)\"values$")
	public void validateDatabase(String fileNames , String transforms ,String deliveredFolder) throws Throwable {
	   String[] format1=null;
	   String[] format2=null;
	   String[] format3=null;
	  
	   List<String> fileNames_list=new ArrayList<String>();
	   List<String> transforms_list=new ArrayList<String>();
	   
	   
		Map<List<String>,List<String>> dbMap=new HashMap<List<String>,List<String>>();
		Map<List<String>,List<String>> apiMap=new HashMap<List<String>,List<String>>();

		if (fileNames.contains(",")||fileNames.length()==1||transforms.contains(",")||transforms.length()==1)	{ 
				format1 = fileNames.split(",");	
				format2 = transforms.split(",");	
		  for (int k = 0; k < format1.length; k++) {
			  fileNames_list.add(format1[k]);
			  if(format2[k].equals("transform"))
			  {
				  transforms_list.add("Y");	 
			  }
			  else
			  {
				  transforms_list.add("N");
			  }			  		
		   }
		  }
		
		
		 if (deliveredFolder.contains(",")||deliveredFolder.length()==1)	{ 
				format3 = deliveredFolder.split(",");					
		    for (int i = 0; i < format3.length; i++) {
		
	  String query="select * from transformation_instructions where publication_id= '" + this.publicationId + "' and delivered_folder='"+format3[i]+"'";
		  
	  List<String> fileNamesFromDb = db.getDbListOfValues(query,"file_name");
	  List<String> transformFromDb = db.getDbListOfValues(query,"transform");
	  
	  dbMap.put(fileNamesFromDb, transformFromDb);
	  apiMap.put(fileNames_list, transforms_list);
	  
	  Assert.assertEquals(dbMap, apiMap,"Values are mismatched");
		
		    }
		    
		 }		 
		
	 
	  }
	
	@Then("^user should get a error response for Transform_Instruction$")
	public void validateErrorResponse() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));	
		Assert.assertTrue(statusCode == 500, "status code is not correct");
	}
	
	
	}