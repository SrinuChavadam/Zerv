package com.backup.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.cucumber.listener.Reporter;
import com.zerv.api.utils.PUBOBAPIUtilities;
import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.runner.TestRunner;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EditAcquisitionInformation extends TestRunner {

	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();
	String publicationId;
	String processedLookupId;
	String contentTypeLookupId;
	String availableLookupId;

	@Given("^am a Acquisition_Information Team member \"(.*?)\" for edit$")
	public void getConnection(String endpoint) throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));

	}

	@When("^user provides a publicationID \"(.*?)\" along with \"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\"values$")
	public void getResponse(String publicationId, String transmissionTypeLookupId, String receiptMethodLookupId,
			String categoryLookupId, String ftpTypeLookupId, String filePriorityLookupId, String contentSizeLookupId,
			String browserLookupId, String triggerLookupId) throws Throwable {

		this.publicationId = publicationId;
		String createAcquisitionInfoRequest = api.createAcquisitionInfoRequest(transmissionTypeLookupId,
				receiptMethodLookupId, categoryLookupId, ftpTypeLookupId, filePriorityLookupId, contentSizeLookupId,
				browserLookupId, triggerLookupId);
		response = request.body(createAcquisitionInfoRequest.toString()).put(publicationId);

		Reporter.addStepLog(response.prettyPrint());
	}

	@Then("^user should get a successful response for Acquisition_Information edit$")
	public void validateStatus() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 200 || statusCode == 201, "status code is not correct");
	}

	@Then("^record should be edited in Acquisition_Information table and validate aganist Database along with\"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\" and \"(.*?)\"values$")
	public void validateDatabase(String transmissionTypeLookupId, String receiptMethodLookupId, String categoryLookupId,
			String ftpTypeLookupId, String filePriorityLookupId, String contentSizeLookupId, String browserLookupId,
			String triggerLookupId) throws Throwable {

		String query = "select * from acquisition_info where publication_id= '" + this.publicationId + "'";

		String transmissionTypeLookupIdFromDb = db.getStringDbValue(query, "transmission_type");
		String receiptMethodLookupIdFromDb = db.getStringDbValue(query, "receipt_method");
		String categoryLookupIdFromDb = db.getStringDbValue(query, "publisher_category");
		String ftpTypeLookupIdFromDb = db.getStringDbValue(query, "ftp_type");
		String filePriorityLookupIdFromDb = db.getStringDbValue(query, "file_priority");
		String contentSizeLookupIdFromDb = db.getStringDbValue(query, "content_size");
		String browserLookupIdFromDb = db.getStringDbValue(query, "browser_type");
		String triggerLookupIdFromDb = db.getStringDbValue(query, "aquisition_trigger");

		SoftAssert sa = new SoftAssert();
		{
			sa.assertEquals(transmissionTypeLookupId, transmissionTypeLookupIdFromDb,
					"Available lookup Id Mismatched from database");
			sa.assertEquals(receiptMethodLookupId, receiptMethodLookupIdFromDb,
					"contentTypeLookupId Mismatched from database");
			sa.assertEquals(categoryLookupId, categoryLookupIdFromDb, "processedLookupId Mismatched from database");
			sa.assertEquals(ftpTypeLookupId, ftpTypeLookupIdFromDb, "processedLookupId Mismatched from database");
			sa.assertEquals(filePriorityLookupId, filePriorityLookupIdFromDb,
					"processedLookupId Mismatched from database");
			sa.assertEquals(contentSizeLookupId, contentSizeLookupIdFromDb,
					"processedLookupId Mismatched from database");
			sa.assertEquals(browserLookupId, browserLookupIdFromDb, "processedLookupId Mismatched from database");
			sa.assertEquals(triggerLookupId, triggerLookupIdFromDb, "processedLookupId Mismatched from database");
		}
		sa.assertAll();

	}

	@Then("^user should get a error response for Acquisition_Information edit$")
	public void validateErrorResponse() throws Throwable {
		int statusCode = response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 500, "status code is not correct");
	}

}