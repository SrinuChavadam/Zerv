package com.backup.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Set;
import java.util.TreeSet;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.zerv.api.utils.PUBOBAPIUtilities;
import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.runner.TestRunner;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TaskCheckList extends TestRunner {

	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();
	Set<String> publicationIDs;
	String publicationId = null;

	@Given("^Am a content acqisition Team member_taskchecklist \"(.*?)\"$")
	public void verifyHeaderDetails_taskchecklist(String endpoint) throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));

	}

	@When("^user provides publicationID,taskchecklistID \"(.*?)\"\\.$")
	public void sendRequest_taskchecklist(String taskChecklistLookupId) throws Throwable {
		String query = "select publication_ID from acquisition_info";
		publicationIDs = db.getDbListValues(query, "publication_id");
		for (String value : publicationIDs) {
			publicationId = value;
			String query1 = "select count(*) as count from task_checklist where publication_id='" + publicationId + "'";
			int count = db.getRowCount(query1);
			if (count == 0) {

				String createRequestWithParameters = api.createRequestForTaskCheckList(taskChecklistLookupId);
				request.body(createRequestWithParameters.toString());
				response = request.post(publicationId);
				// System.out.println(response.prettyPrint());
				break;
			} else
				
				//System.out.println("Record with publicationid" + value + "is already present");
				Reporter.addStepLog("Record with publicationid" + value + "is already present");

		}
	}

	@Then("^User should get a successful response_taskChecklist$")
	public void verifyResponse_taskchecklist() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		// System.out.println(statusCode);
		Assert.assertTrue(statusCode == 200 || statusCode == 201, "Response Code is not Success");
	}

	@Then("^new record should be created in TASK_CHECKLIST table for taskChecklistLookupId \"(.*?)\"$")
	public void verifyDataInDatabase_taskchecklist(String taskChecklistLookupId) throws Throwable {

		Set<String> set = new TreeSet<String>();
		String[] formats = null;
		if (taskChecklistLookupId.contains(",") || taskChecklistLookupId.length() == 1) {
			formats = taskChecklistLookupId.split(",");
			for (int k = 0; k < formats.length; k++) {
				set.add(formats[k]);
			}
		}

		String query1 = "select * from TASK_CHECKLIST where publication_id= '" + this.publicationId + "'";
		Set<String> actual_value = db.getDbListValues(query1, "task_checklist_lookup_id");	
		Assert.assertEquals(set, actual_value, "Record not created in Task Checklist table");

	}
	//Scenario-2
	@When("^user provides publicationID which is already existed,taskchecklistID \"(.*?)\"\\.$")
	public void user_provides_publicationID_which_is_already_existed_publicationID(String taskChecklistLookupId) throws Throwable {
		String query = "select publication_ID from acquisition_info";
		publicationIDs = db.getDbListValues(query, "publication_id");
		for (String value : publicationIDs) {
			publicationId = value;
			String query1 = "select count(*) as count from task_checklist where publication_id='" + publicationId + "'";
			int count = db.getRowCount(query1);
			if (count>0) {

				String createRequestWithParameters = api.createRequestForTaskCheckList(taskChecklistLookupId);
				request.body(createRequestWithParameters.toString());
				response = request.post(publicationId);
				// System.out.println(response.prettyPrint());
				break;
			} else
				
				//System.out.println("Record with publicationid" + value + "is not present");
				Reporter.addStepLog("Record with publicationid" + value + "is not present");

		}
	}

	@Then("^User should get error message like publicationID is already present\\.$")
	public void user_should_get_error_message_like_publicationID_is_already_present() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		// System.out.println(statusCode);
		Assert.assertTrue(statusCode == 400, "Response Code is not Success");
	}
   //Scenario-3
	@When("^user provides publicationID which is not existed in acquisition info table \"(.*?)\",taskchecklistID \"(.*?)\"\\.$")
	public void user_provides_publicationID_which_is_not_existed_in_acquisition_info_table_taskchecklistID(String publicationId, String taskChecklistLookupId) throws Throwable {
		String createRequestWithParameters = api.createRequestForTaskCheckList(taskChecklistLookupId);
		request.body(createRequestWithParameters.toString());
		response = request.post(publicationId);
	}

	@Then("^User should get error message like publicationid is not present in acquisition information\\.$")
	public void user_should_get_error_message_like_publicationid_is_not_present_in_acquisition_information() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		// System.out.println(statusCode);
		Assert.assertTrue(statusCode == 400, "Response Code is not Success");
	}
    //Scenario-4
	@When("^user provides invalid publicationID \"(.*?)\",taskchecklistID \"(.*?)\"\\.$")
	public void user_provides_invalid_publicationID_taskchecklistID(String publicationId, String taskChecklistLookupId) throws Throwable {
		String createRequestWithParameters = api.createRequestForTaskCheckList(taskChecklistLookupId);
		request.body(createRequestWithParameters.toString());
		response = request.post(publicationId);
	}

	@Then("^User should get error message like publicationid is invalid\\.$")
	public void user_should_get_error_message_like_publicationid_is_invalid() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		// System.out.println(statusCode);
		Assert.assertTrue(statusCode == 400, "Response Code is not Success");
	}


	// Scenario outline_05

	@Given("^Am not a content acqisition Team member_taskchecklist \"(.*?)\"$")
	public void verifyInvalidHeaderDetails_taskchecklist(String endpoint) throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("InvalidRoles"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));
	}

	@Then("^the User should get Error Response_taskChecklist$")
	public void verifyError_Response_taskChecklist() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));	
		Assert.assertTrue(statusCode == 403, "Response Code is not Success");
	}

}
