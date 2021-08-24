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

public class EditFormatInfo extends TestRunner{
	
	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;	
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();
	String publicationId;
	String processedLookupId;
	String contentTypeLookupId;
	String availableLookupId;
	
	 
	@Given("^am a Format_Information Team member \"(.*?)\" for edit$")
	public void getConnection(String endpoint) throws Throwable{
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));
		
	}

	@When("^user provides a \"(.*?)\" along with \"(.*?)\" and \"(.*?)\" and \"(.*?)\" values$")
	public void getResponse(String publicationId,String processedLookupId, String contentTypeLookupId, String availableLookupId) throws Throwable {
	    this.publicationId=publicationId;
	    String json=api.createRequestFormatInfo(processedLookupId, contentTypeLookupId, availableLookupId);
		 request.body(json.toString());
		 response = request.put(this.publicationId);	
		Reporter.addStepLog(response.prettyPrint());
	}
	
		

	@Then("^user should get a successful response for edit Format_Information$")
	public void validateStatus() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 200 || statusCode == 201, "status code is not correct");
	}
	
	
	@Then("^new record should be modified in Format_Information table and validate aganist Database along with \"(.*?)\" and \"(.*?)\" and \"(.*?)\" values$")
	public void validateDatabase(String processedLookupId, String contentTypeLookupId, String availableLookupId) throws Throwable {
	
		Set<String> availableLookupIdList=new TreeSet<String>();
		Set<String> contentTypeLookupIdList=new TreeSet<String>();
		Set<String> processedLookupIdList=new TreeSet<String>();
	  
	  String query1="select * from available_format where publication_id= '" + this.publicationId + "'";
	  String query2="select * from format_to_process where publication_id= '" + this.publicationId + "'";;
	  
	  Set<String> availableLookupIdFromDb = db.getDbListValues(query1, "format_lookup_id");
	  Set<String> contentTypeIdFromDb = db.getDbListValues(query2, "content_type_lookup_id");
	  Set<String> processedFormatIdFromDb = db.getDbListValues(query2, "format_lookup_id");
	 
	  String formats1[]=null;
	  String formats2[]=null;
	  String formats3[]=null;
	  if (availableLookupId.contains(",")||availableLookupId.length()==1 ||  contentTypeLookupId.contains(",")||contentTypeLookupId.length()==1  || processedLookupId.contains(",")||processedLookupId.length()==1    )
	  
	  { 
			formats1 = availableLookupId.split(",");
			formats2 = contentTypeLookupId.split(",");
			formats3 = processedLookupId.split(",");
			
	  for (int k = 0; k < formats1.length; k++) {
		  availableLookupIdList.add(formats1[k]);
		  
	     }
	  for (int k = 0; k < formats2.length; k++) {
		  contentTypeLookupIdList.add(formats2[k]);
		  
	     }
	  for (int k = 0; k < formats3.length; k++) {
		  processedLookupIdList.add(formats3[k]);
		  
	     }
	  
	  
	  }	  	  
	  
	  SoftAssert sa=new SoftAssert();
	  sa.assertEquals(availableLookupIdList, availableLookupIdFromDb,"Available lookup Id Mismatched from database");
	  sa.assertEquals(contentTypeLookupIdList, contentTypeIdFromDb,"contentTypeLookupId Mismatched from database");
	  sa.assertEquals(processedLookupIdList, processedFormatIdFromDb,"processedLookupId Mismatched from database");
	  sa.assertAll();
	  }
	
	@Then("^user should get error response for edit Format_Information$")
	public void validateErrorResponse() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));	
		Assert.assertTrue(statusCode == 500, "status code is not correct");
	}


}