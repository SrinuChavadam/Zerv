package com.backup.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.zerv.api.utils.PUBOBAPIUtilities;
import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.runner.TestRunner;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EditScholarlyArtifact extends TestRunner{
	
	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;	
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();
	String publicationId;	
	
	 
	@Given("^am a Bibilo Evaluator \"(.*?)\"\\.$")
	public void getConnection(String endpoint)throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));
		
	}

	@When("^user provides scholarly artifact information \"(.*?)\",\"(.*?)\" for PublicationId \"(.*?)\"\\.$")
	public void getResponse(String clarivateDocType,String providerDocType, String publicationId) throws Throwable {
	  		  	        	
	    this.publicationId=publicationId;     	 	    	           	        	
	    String editScholarlyArtifact = api.createScholarlyArtifact(clarivateDocType, providerDocType);
	    System.out.println(editScholarlyArtifact);
	    response=request.body(editScholarlyArtifact).put(publicationId);  		   	    		 		 		 
		Reporter.addStepLog(response.prettyPrint());
	}
	
		
@Then("^the user should get a success response\\.$")
	public void validateStatus() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 200 || statusCode == 201, "status code is not correct");
	}
	
	
@And("^the system should updated the values in scholarly_classification table \"([^\"]*)\",\"([^\"]*)\" for the given PublicationId \"([^\"]*)\".$")
	public void validateDatabase(String clarivateDocType,String providerDocType, String publicationId) throws Throwable {
	
	String query="select * from scholarly_classification where publication_id= '" + this.publicationId + "'";
	String clarivateDocTypeFromDb=  db.getStringDbValue(query, "clarivate_doc");
	String providerDocTypeFromDb=  db.getStringDbValue(query, "provider_doc");
	
	Assert.assertEquals(clarivateDocType, clarivateDocTypeFromDb,"Database value for clarivateDocType are Mismatched");
	Assert.assertEquals(providerDocType, providerDocTypeFromDb,"Database value for providerDocType are Mismatched");	
	

}
		

@Then("^the user should get a error response for invalid PublicationId\\.$")
public void the_user_should_get_a_error_response_for_invalid_publicationid() throws Throwable {
	int statusCode=response.getStatusCode();
	Reporter.addStepLog(Integer.toString(statusCode));
	Assert.assertTrue(statusCode == 400, "status code is not correct");
}
@Then("^the user should get a error response for Not present PublicationId\\.$")
public void the_user_should_get_a_error_response_for_not_present_publicationid() throws Throwable {
	int statusCode=response.getStatusCode();
	Reporter.addStepLog(Integer.toString(statusCode));
	Assert.assertTrue(statusCode == 400, "status code is not correct");
}	

@Given("^am a Bibilo Evaluator with invalid role \"([^\"]*)\".$")
public void am_a_bibilo_evaluator_with_invalid_role_something(String endpoint) throws Throwable {
	request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("InvalidRoles"),
			prop.getProperty("Content-Type"), prop.getProperty(endpoint));
}
@Then("^the user should get a error response for invalid role\\.$")
public void the_user_should_get_a_error_response_for_invalid_role() throws Throwable {
	int statusCode=response.getStatusCode();
	Reporter.addStepLog(Integer.toString(statusCode));
	Assert.assertTrue(statusCode == 403, "status code is not correct");
}
	 
			 
	  }
	
	
	