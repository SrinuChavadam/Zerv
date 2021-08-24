package com.backup.stepdefinition;

import java.util.*;


import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;

import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.CPS_Acquisition_Setup_Page;
import com.zerv.runner.TestRunner;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class ValidateCPSInputFormUI extends TestRunner {
	
	CPS_Acquisition_Setup_Page cps=new CPS_Acquisition_Setup_Page(Webutils.getDriver());
	
	SpreadSheetBaseFunction sbf=new SpreadSheetBaseFunction();
	ReadProperty read = new ReadProperty();
	//String browser = System.getProperty("browser");
   //String env = System.getProperty("env");
	String browser = "chrome";
	String env = "QA";	
	Logger log = Logger.getLogger(ValidateCPSInputFormUI.class);
	String publicationID="";
	List<String> expected_Data = new ArrayList<String>();
	List<String> actual_Data = new ArrayList<String>();
	SoftAssert sa=new SoftAssert();
	Set<String> publicationIDs;
	DatabaseUtils db=new DatabaseUtils();
	
	
	@Given("^am a cps user \"(.*?)\" ,\"(.*?)\"\\.$")
	public void am_a_cps_user(String role, String Publication_Id) throws Throwable {
		cps.initiateBrowser(browser);
		publicationID=Publication_Id;
			cps.launchOnboardingUrl(env,role,Publication_Id);		
			cps.insertJavaScript(read.getProperty("newsession"));
			cps.launchOnboardingUrl(env,role,Publication_Id);	
		    cps.pause(5000);
	}

	@When("^user provides CPS information \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\"\\.$")
	public void user_provides_CPS_information(String pathurl, String unValue, String pwdValue, String addlcomment) throws Throwable {
	    cps.clickOnCPSAcquisitionLink();
	    cps.enterInputfields("path", pathurl);
	    cps.enterInputfields("username", unValue);
	    cps.enterInputfields("password", pwdValue);
	    cps.enterInputfields("comment", addlcomment);
	    cps.ClickOnPassButton();
	    
	}
	@Then("^CPS information \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" should be saved into cps_information table\\.$")
	public void cps_information_should_be_saved_into_cps_information_table(String pathurl, String unValue, String pwdValue, String addlcomment) throws Throwable {
		String query1="select receipt_url,username,password,additional_info from cps_information where publication_id= '" + publicationID + "'";
		actual_Data = db.getDbListOfValues(query1);
		expected_Data.add(pathurl);
		expected_Data.add(unValue);
		expected_Data.add(pwdValue);
		expected_Data.add(addlcomment);
		sa.assertEquals(actual_Data, expected_Data, "CPS information is not matching" +"Actual:"+actual_Data +"Expected:" +expected_Data);
	      
		
	}

	

	}