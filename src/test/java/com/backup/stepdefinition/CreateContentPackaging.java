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

public class CreateContentPackaging extends TestRunner{
	
	PUBOBAPIUtilities api = new PUBOBAPIUtilities();
	RequestSpecification request;
	Response response;	
	DatabaseUtils db = new DatabaseUtils();
	ReadProperty prop = new ReadProperty();
	String publicationId;
	Set<String> publicationIDs;
	
	@Given("^am a technicalEvaluator_addContentPackage \"(.*?)\"$")
	public void am_a_technicalEvaluator_addContentPackage(String endpoint) throws Throwable {
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("Roles"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));
	}
	@When("^user provides content packaging information \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" for publicationId$")
	public void user_provides_content_packaging_information_for_publicationId(String zipName, String zipNameAccess, String zipContent, String volume,
			String formatLupId, String notes, String matching, String issue, String folderId,String folderCon,String folderName,String folderType, String folderStructure,String fileId,String fileName,String nameCon) throws Throwable {
	    
		String query = "select publication_id from format_evaluation";
		publicationIDs = db.getDbListValues(query, "publication_id");
		for (String value : publicationIDs) {
			publicationId = value;

			String query1 = "select count(*) as count from content_packaging_info where publication_id='"+ publicationId + "'";
			int count = db.getRowCount(query1);
			if (count == 0) {

				String createContentPackageRequest = api.createContentPackageRequest(zipName, zipNameAccess, zipContent,
						volume, formatLupId, notes, matching, issue, folderId,folderCon,folderName,folderType,folderStructure,fileId,fileName,nameCon);
				// System.out.println(createContentPackageRequest);

				response = request.body(createContentPackageRequest.toString()).post(publicationId);
                System.out.println(response);
				break;
			} else
				System.out.println("Record with publicationid" + value + "is already present");

		}

		Reporter.addStepLog(response.prettyPrint());
	}

	@Then("^the user should get a success response_addContentPackage\\.$")
	public void the_user_should_get_a_success_response_addContentPackage() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 200 || statusCode == 201, "status code is not correct");
	}
	
	@Then("^new record should be created in content_packaging_info,file_name_convention and folder_name_convention Tables with \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" for publicationId$")
	public void new_record_should_be_created_in_content_packaging_info_file_name_convention_and_folder_name_convention_Tables_with_for_publicationId(String zipName, String zipNameAccess, String zipContent, String volume,
			String formatLupId, String notes, String matching, String issue, String folderId,String folderCon,String folderName,String folderType, String folderStructure,String fileId,String fileName,String nameCon)throws Throwable {
	    
		String query="select * from content_packaging_info where publication_id= '" + this.publicationId + "'";
		String query1="select * from file_name_convention where publication_id= '" + this.publicationId + "'";
		String query2="select * from folder_name_convention where publication_id= '" + this.publicationId + "'";
				 
		//System.out.println(this.publicationId );
		  String packagingFormatFromDb = db.getStringDbValue(query,"packaging_format");
		  String zipNameRegularFromDb = db.getStringDbValue(query,"zip_name_regular");
		  String ZipNameEarlyFromDb = db.getStringDbValue(query,"zip_name_early_access");
		  String volumeFromDb           = db.getStringDbValue(query,"volume");
		  String issueFromDb = db.getStringDbValue(query,"issue");
		  String notesFromDb = db.getStringDbValue(query,"notes");
		  String folderStructureFromDb = db.getStringDbValue(query,"folder_structure");
		  String zipContentFromDb = db.getStringDbValue(query,"zip_content");
		  String nameMatchFromDb = db.getStringDbValue(query,"name_match");
		  
		  
		  //getting values from file_name_convention table
		  String nameConventionFromDb = db.getStringDbValue(query1,"name_convention");
		  String fileNamingFromDb = db.getStringDbValue(query1,"file_naming");
		  
		//getting values from folder_name_convention
		  String folder_typeFromDb = db.getStringDbValue(query2,"folder_type");
		  String folder_namingFromDb = db.getStringDbValue(query2,"folder_naming");
		  String folder_contentsFromDb = db.getStringDbValue(query2,"folder_contents");
		  
		  SoftAssert sa=new SoftAssert();
		{
			sa.assertEquals(packagingFormatFromDb, formatLupId,"formatLupId value Mismatched with database value");
			sa.assertEquals(zipNameRegularFromDb, zipName,"zipNameRegular value Mismatched with database value");
			sa.assertEquals(ZipNameEarlyFromDb, zipNameAccess,"zipNameAccess value Mismatched with database value");
			sa.assertEquals(notesFromDb, notes, "Notes value Mismatched with database value");
			sa.assertEquals(volumeFromDb, volume, "volume value Mismatched with database value");
			sa.assertEquals(issueFromDb, issue, "issue value Mismatched with database value");
			sa.assertEquals(folderStructureFromDb, folderStructure, "folderStructure value Mismatched with database value");
			sa.assertEquals(zipContentFromDb, zipContent, "zipContent value Mismatched with database value");

			//sa.assertEquals(attachemntLinkFromDb, attachments, "Attachments value Mismatched with database value");
			if (matching.equalsIgnoreCase("true"))
				sa.assertEquals(nameMatchFromDb, "Y", "passwordProvided value Mismatched with database value");
			else if (matching.equalsIgnoreCase("false"))
				sa.assertEquals(nameMatchFromDb, "N", "passwordProvided value Mismatched with database value");
			
			sa.assertEquals(nameConventionFromDb, nameCon,"nameConvention value Mismatched with database value");
			sa.assertEquals(fileNamingFromDb, fileName,"fileName value Mismatched with database value");
			
			sa.assertEquals(folder_typeFromDb, folderType,"nameConvention value Mismatched with database value");
			sa.assertEquals(folder_namingFromDb, folderName,"nameConvention value Mismatched with database value");
			sa.assertEquals(folder_contentsFromDb, folderCon,"nameConvention value Mismatched with database value");
			
			

		}
			 sa.assertAll();
		 
	}
	
	//Scenario-2
	
	@When("^user provides content packaging information \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" for publicationId \"(.*?)\"$")
	public void user_provides_content_packaging_information_for_publicationId(String zipName, String zipNameAccess, String zipContent, String volume,
			String formatLupId, String notes, String matching, String issue, String folderId,String folderCon,String folderName,String folderType, String folderStructure,String fileId,String fileName,String nameCon,String publicationID) throws Throwable {
	    
		String query1 = "select count(*) as count from content_packaging_info where publication_id='"+ publicationID + "'";
		int count = db.getRowCount(query1);
		if (count == 0) {

			String createContentPackageRequest = api.createContentPackageRequest(zipName, zipNameAccess, zipContent,
					volume, formatLupId, notes, matching, issue, folderId,folderCon,folderName,folderType,folderStructure,fileId,fileName,nameCon);
			// System.out.println(createContentPackageRequest);

			response = request.body(createContentPackageRequest.toString()).post(publicationID);
            System.out.println(response);
			
		} else
			System.out.println("Record with publicationid" + publicationID + "is already present");
	Reporter.addStepLog(response.prettyPrint());
	}

	@Then("^the user should get a error response_addContentPackage\\.$")
	public void the_user_should_get_a_error_response_addContentPackage() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 400, "status code is not correct");
	}
	 //Scenario-3
	@When("^user provides content packaging information \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" for publicationID$")
	public void user_provides_content_packaging_information_for_publicationID(String zipName, String zipNameAccess, String zipContent, String volume,
			String formatLupId, String notes, String matching, String issue, String folderId,String folderCon,String folderName,String folderType, String folderStructure,String fileId,String fileName,String nameCon) throws Throwable {
		String query = "select publication_id from format_evaluation";
		publicationIDs = db.getDbListValues(query, "publication_id");
		for (String value : publicationIDs) {
			publicationId = value;

			String query1 = "select count(*) as count from content_packaging_info where publication_id='"+ publicationId + "'";
			int count = db.getRowCount(query1);
			if (count> 0) {

				String createContentPackageRequest = api.createContentPackageRequest(zipName, zipNameAccess, zipContent,
						volume, formatLupId, notes, matching, issue, folderId,folderCon,folderName,folderType,folderStructure,fileId,fileName,nameCon);
				// System.out.println(createContentPackageRequest);

				response = request.body(createContentPackageRequest.toString()).post(publicationId);
                System.out.println(response);
				break;
			} else
				System.out.println("Record with publicationid" + value + "is not present");

		}

		Reporter.addStepLog(response.prettyPrint());
	}

	//Scenario4
	
	@Then("^the user should get  error response_addContentPackage\\.$")
	public void the_user_should_get_error_response_addContentPackage() throws Throwable {
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 400, "status code is not correct");
	}
	
	

	@Given("^am not a technicalEvaluator_addContentPackage \"(.*?)\"$")
	public void am_not_a_technicalEvaluator_addContentPackage(String endpoint) throws Throwable {
		
		request = api.getRequestwithHeaderDetails(prop.getProperty("SessionToken"), prop.getProperty("InvalidRole"),
				prop.getProperty("Content-Type"), prop.getProperty(endpoint));
	    
	}

	@Then("^the user should get Error response_addContentPackage\\.$")
	public void the_user_should_get_Error_response_addContentPackage() throws Throwable {
	 
		int statusCode=response.getStatusCode();
		Reporter.addStepLog(Integer.toString(statusCode));
		Assert.assertTrue(statusCode == 403, "status code is not correct");
	}

}
	


