package com.backup.stepdefinition;


import java.util.*;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.zerv.api.utils.PUBOBAPIUtilities;
import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.runner.TestRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EditTaskChecklist extends TestRunner{
	
	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;		
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();
	Set<String> publicationIDs;
	String publicationId=null;
	 
	@Given("^am a content acquisition user_editTaskChecklist \"(.*?)\"\\.$")
	public void getConnection(String endpoint) throws Throwable {
		
     request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
                                prop.getProperty("Content-Type"), prop.getProperty(endpoint));
                   

	}

	@When("^user sent edit request with publication id,taskLookupId \"(.*?)\"\\.$")
	public void getResponse(String taskChecklistLookupId) throws Throwable {
		String query="select publication_ID from task_checklist";
		publicationIDs=db.getDbListValues(query,"publication_id");
		 for (String value : publicationIDs) 
		 {	 
			 publicationId=value;
			String query1="select count(*) as count from task_checklist where publication_id='"+publicationId+ "'";
		int count=db.getRowCount(query1);
		if(count>0)
		{
			
		String createRequestWithParameters = api.createRequestForTaskCheckList(taskChecklistLookupId);
		request.body(createRequestWithParameters.toString());
		response = request.put(publicationId);		
		break;
		}
		else
			System.out.println("Record with publicationid" +value+ "is not present in task_checkist table");			
		
		 }
	}

	@Then("^the user should get success response_editTaskChecklist\\.$")
	public void validateStatusCode() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));	
		Assert.assertTrue(statusCode == 200 || statusCode == 201, "Response Code is not Success");
	}

	@Then("^the system should save the modified taskLookupId \"(.*?)\" into the TASK_CHECKLIST table\\.$")
	public void validateDatabase(String taskChecklistLookupId) throws Throwable {
		Set<String> set=new TreeSet<String>();
		String[] formats=null;
		 if (taskChecklistLookupId.contains(",")||taskChecklistLookupId.length()==1)        {
             formats = taskChecklistLookupId.split(",");                    
       for (int k = 0; k < formats.length; k++) {    
    	   set.add(formats[k]);
       }
       }
				
        String query1="select * from TASK_CHECKLIST where publication_id= '" + this.publicationId+ "'";
        Set<String> actual_value=db.getDbListValues(query1,"task_checklist_lookup_id");       
        Assert.assertEquals(set,actual_value ,"Record not created in Task Checklist table");
	}
	 //Scenario_02
	@When("^user sent edit request with invalid publicationID \"(.*?)\" and taskLookupId \"(.*?)\"\\.$")
	public void user_sent_edit_request_with_invalid_publicationID_and_taskLookupId(String publicationId, String taskChecklistLookupId) throws Throwable {
		String query="select publication_ID from task_checklist";
		publicationIDs=db.getDbListValues(query,"publication_id");
		 for (String value : publicationIDs) 
		 {	 
			String query1="select count(*) as count from task_checklist where publication_id='"+publicationId+ "'";
		int count=db.getRowCount(query1);
		if(count==0)
		{
			
		String createRequestWithParameters = api.createRequestForTaskCheckList(taskChecklistLookupId);
		request.body(createRequestWithParameters.toString());
		response = request.put(publicationId);
		break;
		}
		else
			Reporter.addStepLog("Record with publicationid" +value+ "is not present in task_checkist table");
		
		 }
	    
	}

	@Then("^the user should get error response code as (\\d+)_editTaskChecklist\\.$")
	public void the_user_should_get_error_response_code_as__editTaskChecklist(int arg1) throws Throwable {
		int statusCode=response.getStatusCode();
        Reporter.addStepLog(response.prettyPrint());                
        Assert.assertTrue(statusCode == 400 , "Request is failed due to invalid data");

	}
    
	//Scenario_03
	@When("^user sent edit request with publication id,invalid taskLookupId \"(.*?)\"\\.$")
	public void user_sent_edit_request_with_publication_id_invalid_taskLookupId(String taskChecklistLookupId) throws Throwable {
		
		String query="select publication_ID from task_checklist";
		publicationIDs=db.getDbListValues(query,"publication_id");
		 for (String value : publicationIDs) 
		 {	 
			 publicationId=value;
			String query1="select count(*) as count from task_checklist where publication_id='"+publicationId+ "'";
		int count=db.getRowCount(query1);
		if(count>0)
		{
			
		String createRequestWithParameters = api.createRequestForTaskCheckList(taskChecklistLookupId);
		request.body(createRequestWithParameters.toString());
		response = request.put(publicationId);
		
		break;
		}
		else
			Reporter.addStepLog("Record with publicationid" +value+ "is not present in task_checkist table");
		
		 }
	}

	@Then("^the user should get error response code (\\d+)_editTaskChecklist\\.$")
	public void the_user_should_get_error_response_code__editTaskChecklist(int arg1) throws Throwable {
		int statusCode=response.getStatusCode();
        Reporter.addStepLog(response.prettyPrint());                
        Assert.assertTrue(statusCode == 500 , "Request is failed due to invalid data");
	}
    //Scenario_04
	@Given("^am not a content acquisition user_editTaskChecklist \"(.*?)\"\\.$")
	public void am_not_a_content_acquisition_user_editTaskChecklist(String endpoint) throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("InvalidRoles"),
				prop.getProperty("Content-Type"),prop.getProperty(endpoint));
	}

	@Then("^the user should get error responseCode as (\\d+)_editTaskChecklist\\.$")
	public void the_user_should_get_error_responseCode_as__editTaskChecklist(int arg1) throws Throwable {
		int statusCode=response.getStatusCode();
        Reporter.addStepLog(response.prettyPrint());                
        Assert.assertTrue(statusCode == 403 , "Request is failed due to invalid data");
	}

	 
	}
