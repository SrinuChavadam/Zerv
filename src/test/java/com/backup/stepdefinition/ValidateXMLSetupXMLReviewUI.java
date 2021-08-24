package com.backup.stepdefinition;

import java.util.*;


import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;

import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.XMLSetup_XMLReview_page;
import com.zerv.runner.TestRunner;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class ValidateXMLSetupXMLReviewUI extends TestRunner {
	
	XMLSetup_XMLReview_page xmlSR=new XMLSetup_XMLReview_page(Webutils.getDriver());
	
	SpreadSheetBaseFunction sbf=new SpreadSheetBaseFunction();
	ReadProperty read = new ReadProperty();
	//String browser = System.getProperty("browser");
    //String env = System.getProperty("env");
	String browser = "chrome";
	String env = "QA";	
	Logger log = Logger.getLogger(ValidateXMLSetupXMLReviewUI.class);
	String publicationID="";
	List<String> expected_Data = new ArrayList<String>();
	List<String> actual_Data = new ArrayList<String>();
	SoftAssert sa=new SoftAssert();
	Set<String> publicationIDs;
	DatabaseUtils db=new DatabaseUtils();
	
	@Given("^am a XML Reviewer on Transformation Review page\\.\"(.*?)\",\"(.*?)\"$")
	public void am_a_XML_Reviewer_on_Transformation_Review_page(String role, String Publication_Id) throws Throwable {
		xmlSR.initiateBrowser(browser);
		publicationID=Publication_Id;
		xmlSR.launchOnboardingUrl(env,role,Publication_Id);		
		xmlSR.insertJavaScript(read.getProperty("newsession"));
		xmlSR.launchOnboardingUrl(env,role,Publication_Id);	
		xmlSR.pause(5000);
	}

	@When("^user provides Defect information \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" and save the page\\.$")
	public void user_provides_Defect_information_and_save_the_page(String defectDesc, String priority, String status, String remarks) throws Throwable {
		xmlSR.clickOnTransformationReviewLink();
		xmlSR.pause(1000);
		xmlSR.clickOnButton("+ Add Defect");
		xmlSR.pause(1000);
		xmlSR.enterInputFields("description", defectDesc);
		xmlSR.selectPriority_Status("priority",priority);
		xmlSR.selectPriority_Status("status",status);
		xmlSR.enterInputFields("remarks", remarks);
		xmlSR.clickOnButton("Save");
		xmlSR.pause(1000);
		xmlSR.isSaveSuccess();
		xmlSR.quitBrowsers();
		    	    
	}

	@Then("^\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" data should be saved into defect_details table\\.$")
	public void data_should_be_saved_into_defect_details_table(String defectDesc, String priority, String status, String remarks) throws Throwable {
	    
		String query1="select description,priority,status,remarks from defect_details where publication_id= '" + publicationID + "'";
		actual_Data = db.getDbListOfValues(query1);
		expected_Data.add(defectDesc);
		expected_Data.add(priority.toUpperCase());
		expected_Data.add(status.toUpperCase());
		expected_Data.add(remarks);
		sa.assertEquals(actual_Data, expected_Data, "XML CPS information is not matching" +"Actual:"+actual_Data +"Expected:" +expected_Data);
	      
	}
	
	//Scenario-2
	
	@When("^user clicks on existing defect and updates \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" information and save the page\\.$")
	public void updateDefect_Open_InProgress(String defectDesc, String priority, String status, String remarks) throws Throwable {
		xmlSR.clickOnTransformationReviewLink();
		xmlSR.pause(1000);
		xmlSR.update_DefectDetails(defectDesc, priority, status, remarks, "Open");
			
	}

	@Then("^updated \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" data should be saved into defect_details table\\.$")
	public void updated_data_should_be_saved_into_defect_details_table(String defectDesc, String priority, String status, String remarks) throws Throwable {
	    
		String query1="select description,priority,status,remarks from defect_details where publication_id= '" + publicationID + "'";
		actual_Data = db.getDbListOfValues(query1);
		expected_Data.add(defectDesc);
		expected_Data.add(priority.toUpperCase());
		expected_Data.add(status.toUpperCase());
		expected_Data.add(remarks);
		sa.assertEquals(actual_Data, expected_Data, "XML CPS information is not matching" +"Actual:"+actual_Data +"Expected:" +expected_Data);
	      
	}
	
	//Scenario-3
	@When("^user changed status to complete and updates \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" information and save the page\\.$")
	public void updateDefect_InProgress_Complete(String defectDesc, String priority, String status, String remarks) throws Throwable {
		xmlSR.clickOnTransformationReviewLink();
		xmlSR.pause(1000);
		xmlSR.update_DefectDetails(defectDesc, priority, status, remarks, "InProgress");
			
	}


	}