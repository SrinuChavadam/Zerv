package com.backup.stepdefinition;


import java.util.*;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.Receipting_Rules_Page;
import com.zerv.runner.TestRunner;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class ValidateReceiptingRulesUI extends TestRunner {
	
	
	Receipting_Rules_Page rr= new Receipting_Rules_Page(Webutils.getDriver());
	SpreadSheetBaseFunction sbf=new SpreadSheetBaseFunction();
	ReadProperty read = new ReadProperty();
	//String browser = System.getProperty("browser");
   //String env = System.getProperty("env");
	String browser = "chrome";
	String env = "QA";	
	Logger log = Logger.getLogger(ValidateReceiptingRulesUI.class);
	String publicationID="";
	Map<String, String> receiptingRulesData;
	Set<String> publicationIDs;
	SoftAssert sa=new SoftAssert();
	DatabaseUtils db=new DatabaseUtils();
	@Given("^am a productionSetup user \"(.*?)\" ,\"(.*?)\"\\.$")
	public void am_a_productionSetup_user(String role, String Publication_Id) throws Throwable {
		rr.initiateBrowser(browser);
		publicationID = Publication_Id;
		rr.launchOnboardingUrl(env, role, Publication_Id);
		rr.insertJavaScript(read.getProperty("newsession"));
		rr.launchOnboardingUrl(env, role, Publication_Id);
		rr.pause(6000);
	}

	@When("^user provides Receipting Rules data \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_Receipting_Rules_data_and_table(String datacolumn, String tablename) throws Throwable {
		rr.clickOnLinks("ReceiptingRules");
		rr.clickOnLinks("Rule1");
		rr.pause(1000);
		receiptingRulesData = sbf.getTableValues("ReceiptingRules", tablename,Integer.parseInt(datacolumn.substring(4)));
		String[] rulesData = receiptingRulesData.get("Rules_Data").split(",");
		String[] rulesOptions = receiptingRulesData.get("Rules_Options").split(",");
		String[] fieldOptions = receiptingRulesData.get("Field_Option").split(",");
		String[] autoProcessOptions = receiptingRulesData.get("AutoProcess_Options").split(",");
		String[] autoProcessCheck = receiptingRulesData.get("AutoProcess_Check").split(",");
		   		   		   	   		      
		   for (int i = 0; i < rulesOptions.length; i++) 
		   {
			   //System.out.println(rulesData[i]);
			   rr.selectRules(rulesOptions[i], rulesData[i],receiptingRulesData.get("Rule"));
		   }
		   rr.selectFiled(fieldOptions[0], fieldOptions[1],receiptingRulesData.get("Rule"));
		   rr.pause(1000);
		   if(fieldOptions[1].equalsIgnoreCase("Publication Date"))
		   {
		   rr.pause(1000);
		   rr.enterInputfields("begin",rr.getDate(1),receiptingRulesData.get("Rule"));
		   rr.pause(1000);
		   if(!receiptingRulesData.get("Rule").equals("Rule1"))
		   rr.enterInputfields("end", rr.getDate(5),receiptingRulesData.get("Rule"));
		   }
		   if(fieldOptions[1].equalsIgnoreCase("Issue")||fieldOptions[1].equalsIgnoreCase("Volume"))
		   {
			   rr.enterInputfields("begin",receiptingRulesData.get("FIELD_BEGIN"),receiptingRulesData.get("Rule"));
			   if(!receiptingRulesData.get("Rule").equals("Rule1"))
			   rr.enterInputfields("end", receiptingRulesData.get("FIELD_END"),receiptingRulesData.get("Rule"));
		   }
		   for (int i = 0; i < autoProcessOptions.length; i++) 
		   {
			   rr.checkAutoProcessfields(autoProcessOptions[i],autoProcessCheck[i],receiptingRulesData.get("Rule"));
			   
		   }
		   rr.clickOnButtons("Done");
		   rr.pause(2000);
		   rr.quitBrowsers();
	}
	@Then("^data should be saved into receipting_rules table\\.$")
	public void data_should_be_saved_into_receipting_rules_table() throws Throwable {
		
		String query1="select VOLUME_CODE,SPECIAL_CODE,ISSUE_CODE,MONTH_CODE,PART_CODE,DAY_CODE,SUPPLEMENT_CODE,SEASON_CODE,MONOGRAPH_CODE,FIELD_EFFECTIVE,UPDATED_BY from BIBLIO.RECEIPTING_RULE where PUBLICATION_ID='" + publicationID + "'";
		Map<String, String> actual_Data = db.getRowDataInMap(query1);
		for(Entry<String, String> data:actual_Data.entrySet())
        {
            String db_data=data.getValue();
            String ui_data=receiptingRulesData.get(data.getKey());
            //System.out.println(ui_data+"is matched to"+db_data);
            sa.assertEquals(db_data, ui_data, "DB value is not matching for field:" +data.getKey() +"  " +"Actual:"+db_data +"Expected:" +ui_data);
         }
	}

	//Scenario-2
	
	@When("^user provides Receipting Rules data and entered End field value \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_Receipting_Rules_data_and_entered_End_field_value_and_table(String datacolumn, String tablename) throws Throwable {
		rr.clickOnLinks("ReceiptingRules");
		rr.clickOnLinks("Rule1");
		rr.pause(1000);
		receiptingRulesData = sbf.getTableValues("ReceiptingRules", tablename,Integer.parseInt(datacolumn.substring(4)));
		String[] rulesData = receiptingRulesData.get("Rules_Data").split(",");
		String[] rulesOptions = receiptingRulesData.get("Rules_Options").split(",");
		String[] fieldOptions = receiptingRulesData.get("Field_Option").split(",");

		for (int i = 0; i < rulesOptions.length; i++) {

			rr.selectRules(rulesOptions[i], rulesData[i], receiptingRulesData.get("Rule"));
		}
		rr.selectFiled(fieldOptions[0], fieldOptions[1], receiptingRulesData.get("Rule"));
		rr.pause(1000);
		if (fieldOptions[1].equalsIgnoreCase("Publication Date")) {
			rr.pause(1000);
			rr.enterInputfields("begin", rr.getDate(1), receiptingRulesData.get("Rule"));
			rr.enterInputfields("end", rr.getDate(5), receiptingRulesData.get("Rule"));
		}
		if (fieldOptions[1].equalsIgnoreCase("Issue") || fieldOptions[1].equalsIgnoreCase("Volume")) {
			rr.enterInputfields("begin", receiptingRulesData.get("FIELD_BEGIN"), receiptingRulesData.get("Rule"));
			rr.enterInputfields("end", receiptingRulesData.get("FIELD_END"), receiptingRulesData.get("Rule"));
		}
	}

	@Then("^warning message should display like At most one rule should have an open ending\\.$")
	public void warning_message_should_display_like_At_most_one_rule_should_have_an_open_ending() throws Throwable {
	    Assert.assertTrue(rr.getOpenEndErrorMessage().equals(receiptingRulesData.get("ErrorMessage")), "Error message mismatched");
	    rr.pause(1000);
		rr.quitBrowsers();
	}
	
	//Scenario-3
		@When("^user provides end value less than begin value \"(.*?)\" and table \"(.*?)\"\\.$")
		public void user_provides_end_value_less_than_begin_value_and_table(String datacolumn, String tablename) throws Throwable {
			rr.clickOnLinks("ReceiptingRules");
			rr.clickOnLinks("Rule1");
			rr.pause(1000);
			receiptingRulesData = sbf.getTableValues("ReceiptingRules", tablename,Integer.parseInt(datacolumn.substring(4)));
			String[] fieldOptions = receiptingRulesData.get("Field_Option").split(",");

			rr.selectFiled(fieldOptions[0], fieldOptions[1], receiptingRulesData.get("Rule"));
			rr.pause(1000);
				if (fieldOptions[1].equalsIgnoreCase("Issue") || fieldOptions[1].equalsIgnoreCase("Volume")) {
				rr.enterInputfields("begin", receiptingRulesData.get("FIELD_BEGIN"), receiptingRulesData.get("Rule"));
				rr.enterInputfields("end", receiptingRulesData.get("FIELD_END"), receiptingRulesData.get("Rule"));
				rr.clickEnter();
				
			}
		    }

		@Then("^warning message should display like Begin value must be less than or equal to End value\\.$")
		public void warning_message_should_display_like_Begin_value_must_be_less_than_or_equal_to_End_value() throws Throwable {
			Assert.assertTrue(rr.getEndValueErrorMessage().equals(receiptingRulesData.get("ErrorMessage")), "Error message mismatched");
		    rr.pause(1000);
			rr.quitBrowsers();
		}
	//Scenario-4
	
	@When("^user provides Receipting Rules data with end value \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_Receipting_Rules_data_with_end_value_and_table(String datacolumn, String tablename) throws Throwable {
		rr.clickOnLinks("ReceiptingRules");
		rr.pause(1000);
		rr.clickOnLinks("AddRules");
		rr.pause(1000);
		rr.clickOnRulelink();
		// rr.pause(1000);
		receiptingRulesData = sbf.getTableValues("ReceiptingRules", tablename,
				Integer.parseInt(datacolumn.substring(4)));
		String[] rulesData = receiptingRulesData.get("Rules_Data").split(",");
		String[] rulesOptions = receiptingRulesData.get("Rules_Options").split(",");
		String[] fieldOptions = receiptingRulesData.get("Field_Option").split(",");
		String[] autoProcessOptions = receiptingRulesData.get("AutoProcess_Options").split(",");
		String[] autoProcessCheck = receiptingRulesData.get("AutoProcess_Check").split(",");

		for (int i = 0; i < rulesOptions.length; i++) {
			// System.out.println(rulesData[i]);
			rr.selectRules(rulesOptions[i], rulesData[i], receiptingRulesData.get("Rule"));
		}
		rr.selectFiled(fieldOptions[0], fieldOptions[1], receiptingRulesData.get("Rule"));
		rr.pause(1000);
		if (fieldOptions[1].equalsIgnoreCase("Publication Date")) {
			rr.pause(1000);
			rr.enterInputfields("begin", rr.getDate(1), receiptingRulesData.get("Rule"));
			rr.enterInputfields("end", rr.getDate(5), receiptingRulesData.get("Rule"));
		}
		if (fieldOptions[1].equalsIgnoreCase("Issue") || fieldOptions[1].equalsIgnoreCase("Volume")) {
			rr.enterInputfields("begin", receiptingRulesData.get("FIELD_BEGIN"), receiptingRulesData.get("Rule"));
			rr.enterInputfields("end", receiptingRulesData.get("FIELD_END"), receiptingRulesData.get("Rule"));
		}
		for (int i = 0; i < autoProcessOptions.length; i++) {
			rr.pause(1000);
			rr.checkAutoProcessfields(autoProcessOptions[i], autoProcessCheck[i], receiptingRulesData.get("Rule"));

		}
		rr.clickOnButtons("Done");
		rr.pause(2000);
		rr.quitBrowsers();
	}

	//Scenario-5
	@When("^user clicks on delete icon on Receipting Rules page\"\\.$")
	public void user_clicks_on_delete_icon_on_Receipting_Rules_page() throws Throwable {
		rr.clickOnLinks("ReceiptingRules");
		rr.pause(1000);
	}

	@Then("^Rule should be deleted\\.$")
	public void rule_should_be_deleted() throws Throwable {
		rr.deleteRule();
		rr.clickOnButtons("Done");
		rr.pause(2000);
		rr.quitBrowsers();
	    
	}
	
}