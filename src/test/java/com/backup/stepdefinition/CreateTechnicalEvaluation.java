package com.backup.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.cucumber.listener.Reporter;
import com.zerv.api.utils.PUBOBAPIUtilities;
import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.runner.TestRunner;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateTechnicalEvaluation extends TestRunner{
	
	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;	
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();
	String publicationId;
	Set<String> publicationIDs;
	
	
	@Given("^Am a technical evaluator_addTechnicalEvaluation \"(.*?)\"\\.$")
	public void addHeaderDetails_TechEvaluation(String endpoint) throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));
	}

	@When("^user provides \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" for publicationId\\.$")
	public void createTechnicalEvaluationRequest(String attachments, String fileAccessLkpId, String notes, String password, String passwordProvided, String pdfSecuritykpId, String processedAsPrint, String timeLinessLkpId) throws Throwable {
	    
		  String query="select publication_id from task_checklist";
		  publicationIDs = db.getDbListValues(query, "publication_id");
	         for (String value : publicationIDs)
	         {    
	             publicationId=value;
	             
	        String query1="select count(*) as count from format_evaluation where publication_id='"+publicationId+ "'";
	        int count=db.getRowCount(query1);
	        if(count==0)
	        {
	        	
	        	String createTechnicalEvaluationRequest = api.createTechnicalEvaluationRequest(attachments,fileAccessLkpId,notes,password,passwordProvided,pdfSecuritykpId,processedAsPrint,timeLinessLkpId);	
	     		//System.out.println(createTechnicalEvaluationRequest);
	        	response=request.body(createTechnicalEvaluationRequest.toString()).post(publicationId);
	       
	        break;
	        }
	        else
	             System.out.println("Record with publicationid" +value+ "is already present");
	        
	         }
		   	    		 		 		 
		 Reporter.addStepLog(response.prettyPrint());
		
	}

	@Then("^User should get a successful response_addTechnicalEvaluation\\.$")
	public void responseValidation_TechEvaluation() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 200 || statusCode == 201, "status code is not correct");
	}

	@Then("^new record should be created in FORMAT_EVALUATION table with \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" data for publicationId$")
	public void formatEvaluation_Table_Verification(String attachments, String fileAccessLkpId, String notes, String password, String passwordProvided, String pdfSecuritykpId, String processedAsPrint, String timeLinessLkpId) throws Throwable {
		String query="select * from format_evaluation where publication_id= '" + this.publicationId + "'";
		 // String query1="select attachment_link from attachment where publication_id='" + this.publicationId +"'";
		//Map<String, String> actual_Data = db.getRowMap(query);
		  String fileAccessLkpIdFromDb = db.getStringDbValue(query,"file_access");
		  String pdfSecuritykpIdFromDb = db.getStringDbValue(query,"pdf_security");
		  String timeLinessLkpIdFromDb = db.getStringDbValue(query,"timeliness");
		  String notesFromDb           = db.getStringDbValue(query,"notes");
		  String passwordProvidedFromDb = db.getStringDbValue(query,"password_provided");
		  String processedAsPrintFromDb = db.getStringDbValue(query,"processed_as_print");
		 // String attachemntLinkFromDb   = db.getStringDbValue(query1, "attachment_link");	  
		  SoftAssert sa=new SoftAssert();
		
			sa.assertEquals(fileAccessLkpIdFromDb, fileAccessLkpId,
					"FileAccessLookupId value Mismatched with database value");
			sa.assertEquals(pdfSecuritykpIdFromDb, pdfSecuritykpId,
					"PDFSecurityLookupId value Mismatched with database value");
			sa.assertEquals(timeLinessLkpIdFromDb, timeLinessLkpId,
					"TimelinessLookupId value Mismatched with database value");
			sa.assertEquals(notesFromDb, notes, "Notes value Mismatched with database value");

			//sa.assertEquals(attachemntLinkFromDb, attachments, "Attachments value Mismatched with database value");
			if (passwordProvided.equalsIgnoreCase("true"))
				sa.assertEquals(passwordProvidedFromDb, "Y", "passwordProvided value Mismatched with database value");
			else if (passwordProvided.equalsIgnoreCase("false"))
				sa.assertEquals(passwordProvidedFromDb, "N", "passwordProvided value Mismatched with database value");
			if (processedAsPrint.equalsIgnoreCase("true"))
				sa.assertEquals(processedAsPrintFromDb, "Y", "processedAsPrint value Mismatched from database value");
			if (processedAsPrint.equalsIgnoreCase("false"))
				sa.assertEquals(processedAsPrintFromDb, "N", "processedAsPrint value Mismatched from database value");

		
			 sa.assertAll();
		 
	}
	
	//Scenario_2 
	
	@When("^user provides invalid data \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" for publicationId\\.$")
	public void invalid_Data_For_PublicationId(String attachments, String fileAccessLkpId, String notes, String password, String passwordProvided, String pdfSecuritykpId, String processedAsPrint, String timeLinessLkpId) throws Throwable {
	    
		String query="select publication_id from task_checklist";
		  publicationIDs = db.getDbListValues(query, "publication_id");
	         for (String value : publicationIDs)
	         {    
	             publicationId=value;
	             
	        String query1="select count(*) as count from format_evaluation where publication_id='"+publicationId+ "'";
	        int count=db.getRowCount(query1);
	        if(count==0)
	        {
	        	String createTechnicalEvaluationRequest = api.createTechnicalEvaluationRequest(attachments,fileAccessLkpId,notes,password,passwordProvided,pdfSecuritykpId,processedAsPrint,timeLinessLkpId);	
	     		 	response=request.body(createTechnicalEvaluationRequest.toString()).post(publicationId);
	       
	        break;
	        }
	        else
	            
	        System.out.println("Record with publicationid" +value+ "is already present");
	        
	         }
		   	    		 		 		 
		 Reporter.addStepLog(response.prettyPrint());
	}

	
	@Then("^User should get EntityNotFoundException_(\\d+)_addTechnicalEvaluation\\.$")
	public void entityNotFoundException___AddTechnicalEvaluation(int arg1) throws Throwable {
	 
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 500, "status code is not correct");
	}

		
	//Scenario_3

	@When("^user provides data \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" for invalid publicationId \"(.*?)\"\\.$")
	public void invalid_PublicationId(String attachments, String fileAccessLkpId, String notes, String password, String passwordProvided, String pdfSecuritykpId, String processedAsPrint, String timeLinessLkpId, String publicationId) throws Throwable {
	    
		
		// System.out.println(publicationId);
		String createTechnicalEvaluationRequest = api.createTechnicalEvaluationRequest(attachments, fileAccessLkpId,
				notes, password, passwordProvided, pdfSecuritykpId, processedAsPrint, timeLinessLkpId);
		// System.out.println(createTechnicalEvaluationRequest);
		response = request.body(createTechnicalEvaluationRequest.toString()).post(publicationId);

		Reporter.addStepLog(response.prettyPrint());
	}
	    @Then("^User should get type issue_(\\d+)_addTechnicalEvaluation\\.$")
		public void FormatIssue_AddTechnicalEvaluation(int arg1) throws Throwable {
	    	int statusCode=response.getStatusCode();
			Reporter.addStepLog(Integer.toString(statusCode));
			Assert.assertTrue(statusCode == 400, "status code is not correct");
		}
	    
	    //Scenario_4
	 
	    @Given("^Am not a technical evaluator_addTechnicalEvaluation \"(.*?)\"\\.$")
	    public void notTechnicalEvaluator_AddTechnicalEvaluation(String endpoint) throws Throwable {
	    	request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("InvalidRole"),
					prop.getProperty("Content-Type"), prop.getProperty(endpoint));
	    }
	    @Then("^User should get access denied error response_(\\d+)_addTechnicalEvaluation\\.$")
		public void accessDeniedError_AddTechnicalEvaluation(int arg1) throws Throwable {
		 
	    	int statusCode=response.getStatusCode();
			Reporter.addStepLog(Integer.toString(statusCode));
			Assert.assertTrue(statusCode == 403, "status code is not correct");
		}
	
}
	


