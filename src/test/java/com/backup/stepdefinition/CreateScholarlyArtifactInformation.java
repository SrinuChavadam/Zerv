package com.backup.stepdefinition;

import java.util.Set;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.cucumber.listener.Reporter;
import com.zerv.api.utils.PUBOBAPIUtilities;
import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.runner.TestRunner;

import cucumber.api.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateScholarlyArtifactInformation extends TestRunner {

	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();
	Set<String> publicationIDs;
	String publicationId = null;
    
	@Given("^am a Bibilo Evaluators \"(.*?)\"\\.$")
	public void am_a_Bibilo_Evaluator(String endpoint) throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));

	}

	@When("^user provides scholarly artifact information \"(.*?)\",\"(.*?)\" for PublicationId\\.$")
	public void user_provides_scholarly_artifact_information_for_PublicationId(String clarivateDocType,String providerDocType) throws Throwable {
		
		
		String query = "select publication_id from biblio_policy_evaluation except select publication_id from Scholarly_classification";
		publicationId = db.getDbSingleValue(query, "publication_id");
		System.out.println(publicationId);
		String createRequestWithParameters = api.createScholarlyArtifact(clarivateDocType,providerDocType);
		request.body(createRequestWithParameters.toString());
		response = request.post(publicationId);
		/*String query = "select publication_ID from biblio_policy_evaluation";
		publicationIDs = db.getDbListValues(query, "publication_id");
		for (String value : publicationIDs) {
			publicationId = value;
			System.out.println(publicationId);
			String query1 = "select count(*) as count from Scholarly_classification where publication_id='" + publicationId + "'";
			int count = db.getRowCount(query1);
			if (count == 0) {

				String createRequestWithParameters = api.createScholarlyArtifact(clarivateDocType,providerDocType);
				request.body(createRequestWithParameters.toString());
				response = request.post(publicationId);
				// System.out.println(response.prettyPrint());
				break;
			} else
				
				//System.out.println("Record with publicationid" + value + "is already present");
				Reporter.addStepLog("Record with publicationid" + value + "is already present");

		}*/
	}

	@Then("^the user should get a successfull response\\.$")
	public void the_user_should_get_a_success_response() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		// System.out.println(statusCode);
		Assert.assertTrue(statusCode == 200 || statusCode == 201, "Response Code is not Success");
	}

	@Then("^the system should update the values in scholarly_classification table for \"(.*?)\",\"(.*?)\" and PublicationId\\.$")
	public void validateDatabase_scholarly_classification_table(String clarivateDocType, String providerDocType) throws Throwable {
		String query="select * from scholarly_classification where publication_id= '" + publicationId + "'";
		String clarivateDocTypeFromDb=  db.getStringDbValue(query, "clarivate_doc");
		String providerDocTypeFromDb=  db.getStringDbValue(query, "provider_doc");
		SoftAssert sa =new SoftAssert();
		sa.assertEquals(clarivateDocTypeFromDb,clarivateDocType,"Database value for clarivateDocType are Mismatched");
		sa.assertEquals(providerDocTypeFromDb,providerDocType, "Database value for providerDocType are Mismatched");	
		sa.assertAll();	
	}

	//Scenario_2

	@When("^user provides scholarly artifact information \"(.*?)\",\"(.*?)\" for \"(.*?)\"\\.$")
	public void user_provides_scholarly_artifact_information_for(String clarivateDocType,String providerDocType,String publicationID) throws Throwable {
		String createRequestWithParameters = api.createScholarlyArtifact(clarivateDocType,providerDocType);
		request.body(createRequestWithParameters.toString());
		response = request.post(publicationID);
	}

	@Then("^the user should get error message like invalid format\\.$")
	public void the_user_should_get_error_message_like_invalid_format() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		// System.out.println(statusCode);
		Assert.assertTrue(statusCode == 400, "Response Code is not Success");
	}
  //Scenario_3
	@When("^user sends request with publication id which is not present in policy evaluation \"(.*?)\",\"(.*?)\" for \"(.*?)\"\\.$")
	public void user_sends_request_with_publication_id_which_is_not_present_in_policy_evaluation_for(String clarivateDocType,String providerDocType,String publicationID) throws Throwable {
		String createRequestWithParameters = api.createScholarlyArtifact(clarivateDocType,providerDocType);
		request.body(createRequestWithParameters.toString());
		response = request.post(publicationID);
	}

	@Then("^the user should get errorMessage like publicationId should be present in Policy Evaluation\\.$")
	public void the_user_should_get_errorMessage_like_publicationId_should_be_present_in_Policy_Evaluation() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		// System.out.println(statusCode);
		Assert.assertTrue(statusCode == 400, "Response Code is not Success");
	}
	//Scenario_4
	@When("^user sends request with publication id which is already existed in db \"(.*?)\",\"(.*?)\"\\.$")
	public void user_sends_request_with_publication_id_which_is_already_existed_in_db(String clarivateDocType, String providerDocType) throws Throwable {
		String query = "select publication_ID from biblio_policy_evaluation";
		publicationIDs = db.getDbListValues(query, "publication_id");
		for (String value : publicationIDs) {
			publicationId = value;
			String query1 = "select count(*) as count from Scholarly_classification where publication_id='" + publicationId + "'";
			int count = db.getRowCount(query1);
			if (count > 0) {

				String createRequestWithParameters = api.createScholarlyArtifact(clarivateDocType,providerDocType);
				request.body(createRequestWithParameters.toString());
				response = request.post(publicationId);
				// System.out.println(response.prettyPrint());
				break;
			} else
				
				//System.out.println("Record with publicationid" + value + "is already present");
				Reporter.addStepLog("Record with publicationid" + value + "is already present");

		}
	}

	@Then("^the user should get errorMessage like publicationId is already present\\.$")
	public void the_user_should_get_errorMessage_like_publicationId_is_already_present() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		// System.out.println(statusCode);
		Assert.assertTrue(statusCode == 400, "Response Code is not Success");
	}

	//Scenario_5
	@Given("^am not a Bibilo Evaluator \"(.*?)\"\\.$")
	public void am_not_a_Bibilo_Evaluator(String endpoint) throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("InvalidRole"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));
	}

	@When("^user sends request with invalid role \"(.*?)\",\"(.*?)\" for \"(.*?)\"\\.$")
	public void user_sends_request_with_invalid_role_for(String clarivateDocType, String providerDocType, String publicationID) throws Throwable {
		String createRequestWithParameters = api.createScholarlyArtifact(clarivateDocType,providerDocType);
		request.body(createRequestWithParameters.toString());
		response = request.post(publicationID);
	}

	@Then("^the user should get Access is denied errorMessage\\.$")
	public void the_user_should_get_Access_is_denied_errorMessage() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		// System.out.println(statusCode);
		Assert.assertTrue(statusCode == 403, "Response Code is not Success");
	}


	
}
