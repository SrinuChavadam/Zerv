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

public class ViewAvailableFormat extends TestRunner {

	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();

	@Given("^am a content acqisition team members for get method \"(.*?)\"$")
	public void getConnection(String endPoint) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
				prop.getProperty("Content-Type"), prop.getProperty(endPoint));

	}

	// Scenario outline 1

	@When("^user provides existing publicationID \"(.*?)\"$")
	public void getResponse(String publicationID) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		response = api.viewFormatInfoWithPublicationID(request, publicationID);
	}

	@Then("^the user should get success responses$")
	public void validateResponse() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(response.prettyPrint());

		Assert.assertTrue(statusCode == 200 || statusCode == 201, "Response Code is not Successful");
	}

	@Then("^the system should validate information aganist the database for the table column \"(.*?)\" and \"(.*?)\"$")
	public void getAvailableFormat(String ColumnName, String publicationId) throws Throwable {

		Set<String> apiResponse = new TreeSet<String>();

		List<String> checkedList = response.jsonPath().getList("data.formatInformation.availableFormats.checked");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.formatInformation.availableFormats[" + i + "].checked")
					.equals("true")) {
				apiResponse.add(response.jsonPath()
						.getString("data.formatInformation.availableFormats[" + i + "].availableLookupId"));
			}

		}
		String query = "select * from available_format where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");

	}
	// Scenario outline 2

	@Then("^the system should validate information aganist the database for the processed_format table column \"(.*?)\" and \"(.*?)\"$")
	public void getProcessedFormat(String ColumnName, String publicationId) throws Throwable {

		Set<String> apiResponse = new TreeSet<String>();

		List<String> checkedList = response.jsonPath().getList("data.formatInformation.processedFormats.checked");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.formatInformation.processedFormats[" + i + "].checked")
					.equals("true")) {
				apiResponse.add(response.jsonPath()
						.getString("data.formatInformation.processedFormats[" + i + "].processedLookupId"));
			}

		}
		String query = "select * from processed_format where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");
	}

	// Scenario outline 3
	@Then("^the system should validate information aganist the database for the content_type table column \"(.*?)\" and \"(.*?)\"$")
	public void getContentTypes(String ColumnName, String publicationId) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		List<String> li = new ArrayList<String>();
		List<String> checkedList = response.jsonPath().getList("data.formatInformation.contentTypes.selected");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.formatInformation.contentTypes[" + i + "].selected")
					.equals("true")) {
				li.add(response.jsonPath()
						.getString("data.formatInformation.contentTypes[" + i + "].contentTypeLookupId"));
			}

		}
		String query = "select * from content_type where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(li, dbListValues, "Response and DB values are mismatched");

	}

	// Scenario outline 4
	@Then("^the system should validate information aganist the database for the taskchecklist table column \"(.*?)\" and \"(.*?)\"$")
	public void getTaskChecklist(String ColumnName, String publicationId) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Set<String> li = new TreeSet<String>();
		List<String> checkedList = response.jsonPath().getList("data.taskChecklistInfo.taskChecklist.checked");

		System.out.println(checkedList.size());

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.taskChecklistInfo.taskChecklist[" + i + "].checked")
					.equals("true")) {
				li.add(response.jsonPath()
						.getString("data.taskChecklistInfo.taskChecklist[" + i + "].taskChecklistLookupId"));
			}

		}
		String query = "select * from task_checklist where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(li, dbListValues, "Response and DB values are mismatched");

	}

	@Then("^the system should validate information aganist the database for the acquisition_info table publisher_category column \"(.*?)\" and \"(.*?)\"$")
	public void getPublisherCategory(String ColumnName, String publicationId) throws Throwable {

		Set<String> apiResponse = new TreeSet<String>();
		List<String> checkedList = response.jsonPath().getList("data.acquistionInfo.publisherCategories.selected");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.acquistionInfo.publisherCategories[" + i + "].selected")
					.equals("true")) {
				apiResponse.add(response.jsonPath()
						.getString("data.acquistionInfo.publisherCategories[" + i + "].categoryLookupId"));
			}

		}
		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");
	}

	// Scenario outline 4
	@Then("^the system should validate information aganist the database for the acquisition_info table receipt_method column \"(.*?)\" and \"(.*?)\"$")
	public void getReceiptMethod(String ColumnName, String publicationId) throws Throwable {
		Set<String> apiResponse = new TreeSet<String>();
		List<String> checkedList = response.jsonPath().getList("data.acquistionInfo.publisherCategories.selected");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.acquistionInfo.publisherCategories[" + i + "].selected")
					.equals("true")) {
				apiResponse.add(response.jsonPath()
						.getString("data.acquistionInfo.publisherCategories[" + i + "].categoryLookupId"));
			}

		}
		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");
	}

	// Scenario outline 5
	@Then("^the system should validate information aganist the database for the acquisition_info table transmission_type column \"(.*?)\" and \"(.*?)\"$")
	public void getTransmissionType(String ColumnName, String publicationId) throws Throwable {
		Set<String> apiResponse = new TreeSet<String>();
		List<String> checkedList = response.jsonPath().getList("data.acquistionInfo.transmissionTypes.selected");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.acquistionInfo.transmissionTypes[" + i + "].selected")
					.equals("true")) {
				apiResponse.add(response.jsonPath()
						.getString("data.acquistionInfo.transmissionTypes[" + i + "].transmissionTypeLookupId"));
			}

		}
		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");
	}

	// Scenario outline 6
	@Then("^the system should validate information aganist the database for the acquisition_info table ftp_type column \"(.*?)\" and \"(.*?)\"$")
	public void getFtpType(String ColumnName, String publicationId) throws Throwable {
		Set<String> apiResponse = new TreeSet<String>();
		List<String> checkedList = response.jsonPath().getList("data.acquistionInfo.ftpTypes.selected");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.acquistionInfo.ftpTypes[" + i + "].selected").equals("true")) {
				apiResponse
						.add(response.jsonPath().getString("data.acquistionInfo.ftpTypes[" + i + "].ftpTypeLookupId"));
			}

		}
		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");
	}

	// Scenario outline 7
	@Then("^the system should validate information aganist the database for the acquisition_info table content_size column \"(.*?)\" and \"(.*?)\"$")
	public void getContentSize(String ColumnName, String publicationId) throws Throwable {
		Set<String> apiResponse = new TreeSet<String>();
		List<String> checkedList = response.jsonPath().getList("data.acquistionInfo.contentSizes.selected");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.acquistionInfo.contentSizes[" + i + "].selected").equals("true")) {
				apiResponse.add(response.jsonPath()
						.getString("data.acquistionInfo.contentSizes[" + i + "].contentSizeLookupId"));
			}

		}
		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");
	}

	// Scenario outline 8
	@Then("^the system should validate information aganist the database for the acquisition_info table file_priority column \"(.*?)\" and \"(.*?)\"$")
	public void getFilePriority(String ColumnName, String publicationId) throws Throwable {
		Set<String> apiResponse = new TreeSet<String>();
		List<String> checkedList = response.jsonPath().getList("data.acquistionInfo.filePriorities.selected");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.acquistionInfo.filePriorities[" + i + "].selected")
					.equals("true")) {
				apiResponse.add(response.jsonPath()
						.getString("data.acquistionInfo.filePriorities[" + i + "].filePriorityLookupId"));
			}

		}
		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");
	}

	// Scenario outline 9
	@Then("^the system should validate information aganist the database for the acquisition_info table aquisition_trigger column \"(.*?)\" and \"(.*?)\"$")
	public void getAquisitionTrigger_column_and(String ColumnName, String publicationId) throws Throwable {
		Set<String> apiResponse = new TreeSet<String>();
		List<String> checkedList = response.jsonPath().getList("data.acquistionInfo.acquisitionTrigger.selected");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.acquistionInfo.acquisitionTrigger[" + i + "].selected")
					.equals("true")) {
				apiResponse.add(response.jsonPath()
						.getString("data.acquistionInfo.acquisitionTrigger[" + i + "].triggerLookupId"));
			}

		}
		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");
	}

	// Scenario outline 10
	@Then("^the system should validate information aganist the database for the acquisition_info table browser_type column \"(.*?)\" and \"(.*?)\"$")
	public void getBrowserType(String ColumnName, String publicationId) throws Throwable {
		Set<String> apiResponse = new TreeSet<String>();
		List<String> checkedList = response.jsonPath().getList("data.acquistionInfo.browserTypes.selected");

		for (int i = 0; i < checkedList.size(); i++) {
			if (response.jsonPath().getString("data.acquistionInfo.browserTypes[" + i + "].selected").equals("true")) {
				apiResponse.add(
						response.jsonPath().getString("data.acquistionInfo.browserTypes[" + i + "].browserLookupId"));
			}

		}
		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		Set<String> dbListValues = db.getDbListValues(query, ColumnName);
		Assert.assertEquals(apiResponse, dbListValues, "Response and DB values are mismatched");
	}

	// Scenario outline 11
	@Then("^the system should validate information aganist the database for the acquisition_info table publisher_schema_version column \"(.*?)\" and \"(.*?)\"$")
	public void getPublisherSchema(String ColumnName, String publicationId) throws Throwable {

		String publisherSchemaFromResponse = response.jsonPath()
				.getString("data.acquistionInfo.publisherSchemaVersion");

		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		String publisherSchemaFromDB = db.getStringDbValue(query, ColumnName);

		Assert.assertEquals(publisherSchemaFromResponse, publisherSchemaFromDB,
				"Response and DB values are mismatched");

	}

	// Scenario outline 12
	@Then("^the system should validate information aganist the database for the acquisition_info table publisher_code column \"(.*?)\" and \"(.*?)\"$")
	public void getPublisherCode(String ColumnName, String publicationId) throws Throwable {

		String publisherCodeFromResponse = response.jsonPath().getString("data.acquistionInfo.publisherCode");

		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		String publisherCodeFromDB = db.getStringDbValue(query, ColumnName);
		Assert.assertEquals(publisherCodeFromResponse, publisherCodeFromDB, "Response and DB values are mismatched");
	}

	// Scenario outline 13
	@Then("^the system should validate information aganist the database for the acquisition_info table sleep_time column \"(.*?)\" and \"(.*?)\"$")
	public void getSleepTime(String ColumnName, String publicationId) throws Throwable {

		String sleepTimeFromResponse = response.jsonPath().getString("data.acquistionInfo.resolutionTime");

		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		String sleepTimeFromDB = db.getStringDbValue(query, ColumnName);
		Assert.assertEquals(sleepTimeFromResponse, sleepTimeFromDB, "Response and DB values are mismatched");
	}

	// Scenario outline 14
	@Then("^the system should validate information aganist the database for the acquisition_info table data_retention column \"(.*?)\" and \"(.*?)\"$")
	public void getDataRetention(String ColumnName, String publicationId) throws Throwable {
		String dataRetentionFromResponse = response.jsonPath().getString("data.acquistionInfo.dataRetention");

		String query = "select * from acquisition_info where publication_id= '" + publicationId + "'";
		String dataRetentionFromDB = db.getStringDbValue(query, ColumnName);

		Assert.assertEquals(dataRetentionFromResponse, dataRetentionFromDB, "Response and DB values are mismatched");
	}

}
