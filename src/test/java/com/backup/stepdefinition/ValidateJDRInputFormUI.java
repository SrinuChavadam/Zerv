package com.backup.stepdefinition;

import java.util.*;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;

import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.JDR_Input_Form_Page;
import com.zerv.runner.TestRunner;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class ValidateJDRInputFormUI extends TestRunner {
	
	JDR_Input_Form_Page jdr=new JDR_Input_Form_Page(Webutils.getDriver());
	
	SpreadSheetBaseFunction sbf=new SpreadSheetBaseFunction();
	ReadProperty read = new ReadProperty();
	//String browser = System.getProperty("browser");
	//String env = System.getProperty("env");
	String browser = "chrome";
	String env = "QA";	
	Logger log = Logger.getLogger(ValidateJDRInputFormUI.class);
	String publicationID="";
	Map<String, String> jdrInputFormData;
	Set<String> expected_processedFor = new HashSet<String>(); 
	Set<String> actual_processedFor = new HashSet<String>(); 
	SoftAssert sa=new SoftAssert();
	Set<String> publicationIDs;
	DatabaseUtils db=new DatabaseUtils();
	
	
	@Given("^am a productionsetup user \"(.*?)\" ,\"(.*?)\"\\.$")
	public void launchingApplication(String role, String Publication_Id) throws Throwable {
		jdr.initiateBrowser(browser);
		publicationID=Publication_Id;
		jdr.launchOnboardingUrl(env,role,Publication_Id);		
		jdr.insertJavaScript(read.getProperty("newsession"));
		jdr.launchOnboardingUrl(env,role,Publication_Id);	
		jdr.pause(10000);
	}

	@When("^user provides field order,Reference Characteristics,Indent Values and Processed For data \"(.*?)\" and table \"(.*?)\"\\.$")
	public void data_Filling_01(String datacolumn, String tablename) throws Throwable {
	   jdr.clickOnJDRInputLink();
	   jdrInputFormData = sbf.getTableValues("JDRInputForm", tablename,Integer.parseInt(datacolumn.substring(4)));
	   String[] fieldorder_data=jdrInputFormData.get("FieldOrder_Data").split(",");
	   String[] fieldorder_Values=jdrInputFormData.get("FieldOrder_Values").split(",");
	         
	   for (int i = 0; i < fieldorder_data.length; i++) 
	   {
		   jdr.selectFiledOrder(fieldorder_data[i], fieldorder_Values[i]);
	   }
	   //jdr.enterinputfields("ProcessGroup",jdrInputFormData.get("ProcessGroup"));
       String[] refChar_data=jdrInputFormData.get("Ref_Char_Data").split(",");
	   for (int j = 0; j < refChar_data.length; j++) 
	   {
		   jdr.pause(1000);
		   jdr.selectReferenceCharacter(refChar_data[j]);
	   }
	   
	   jdr.enterinputfields("MaxLines",jdrInputFormData.get("max_lines"));
	   jdr.enterinputfields("indentLeftValue",jdrInputFormData.get("indent_left_value"));
	   jdr.enterinputfields("indentRightValue",jdrInputFormData.get("indent_right_value"));
       String[] processedFor_data=jdrInputFormData.get("Processed_for_data").split(",");
	   
	   for (int k = 0; k < processedFor_data.length; k++) 
	   {
		   jdr.selectProcessedFor(processedFor_data[k]);
		   expected_processedFor.add(jdrInputFormData.get(processedFor_data[k]));
	   }
	   jdr.ClickOnPassButton();
	   jdr.pause(2000);
	   jdr.quitBrowsers();
	}

	 
	
	@Then("^field order\\((\\d+),(\\d+),(\\d+),(\\d+),(\\d+),(\\d+)\\),Processed for\\(ASHP,BIOSIS,ZR,IDPO,DDF\\) and Reference Characteristics information should be saved into journal_data_record table\\.$")
	public void db_Validation_01(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) throws Throwable {
		String query1="select author_field_order,article_field_order,journal_title_field_order,year_field_order,volume_field_order,page_field_order,process_auto_ref,single_line_ref,hanging,multiple_refs,take_from_indent,right_justified,author_initials_first,numbered,max_lines,indent_left_value,indent_right_value from journal_data_record where publication_id= '" + publicationID + "'";
		String query2="select process_lookup from journal_data_process where journal_data_record='" + publicationID + "'";
		Map<String, String> actual_Data = db.getRowMap(query1);
		for(Entry<String, String> m:actual_Data.entrySet())
        {
			 String db_data=m.getValue();
	            String ui_data=jdrInputFormData.get(m.getKey());
	            //System.out.println(ui_data+"is matched to"+db_data);
	            sa.assertEquals(db_data, ui_data, "DB value is not matching for field:" +m.getKey() +"  " +"Actual:"+db_data +"Expected:" +ui_data);
	        
        }
		//Processed for
		actual_processedFor=db.getDbListValues(query2,"process_lookup");
		//System.out.println(actual_processedFor+"is matched to"+expected_processedFor);
		sa.assertEquals(actual_processedFor, expected_processedFor, "Processed for value is not matching" +"Actual:"+actual_processedFor +"Expected:" +expected_processedFor);
      
	}

	@Then("^field order\\((\\d+),(\\d+),(\\d+),(\\d+),(\\d+),(\\d+)\\),Processed for\\(ASHP,BIOSIS,IDPO\\) and Reference Characteristics information should be saved into journal_data_record table\\.$")
	public void db_Validation_02(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) throws Throwable {
		String query1="select author_field_order,article_field_order,journal_title_field_order,year_field_order,volume_field_order,page_field_order,process_auto_ref,hanging,multiple_refs,take_from_indent,right_justified,numbered,max_lines,indent_left_value,indent_right_value from journal_data_record where publication_id= '" + publicationID + "'";
		String query2="select process_lookup from journal_data_process where journal_data_record='" + publicationID + "'";
		Map<String, String> actual_Data = db.getRowMap(query1);
		for(Entry<String, String> m:actual_Data.entrySet())
        {
            String db_data=m.getValue();
            String ui_data=jdrInputFormData.get(m.getKey());
            //System.out.println(ui_data+"is matched to"+db_data);
            sa.assertEquals(db_data, ui_data, "DB value is not matching for field:" +m.getKey() +"  " +"Actual:"+db_data +"Expected:" +ui_data);
         }
		//Processed for
		actual_processedFor=db.getDbListValues(query2,"process_lookup");
		//System.out.println(actual_processedFor+"is matched to"+expected_processedFor);
		sa.assertEquals(actual_processedFor, expected_processedFor, "Processed for value is not matching" +"Actual:"+actual_processedFor +"Expected:" +expected_processedFor);
      
	}

	@Then("^field order\\((\\d+),(\\d+),(\\d+),(\\d+),(\\d+),(\\d+)\\),Processed for\\(ASHP,BIOSIS,ZR,IDPO\\) and Reference Characteristics information should be saved into journal_data_record table\\.$")
	public void db_Validation_03(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) throws Throwable {
		String query1="select author_field_order,article_field_order,journal_title_field_order,year_field_order,volume_field_order,page_field_order,single_line_ref,hanging,multiple_refs,take_from_indent,author_initials_first,numbered,max_lines,indent_left_value,indent_right_value from journal_data_record where publication_id= '" + publicationID + "'";
		String query2="select process_lookup from journal_data_process where journal_data_record='" + publicationID + "'";
		Map<String, String> actual_Data = db.getRowMap(query1);
		for(Entry<String, String> m:actual_Data.entrySet())
        {
            String db_data=m.getValue();
            String ui_data=jdrInputFormData.get(m.getKey());
            sa.assertEquals(db_data, ui_data, "DB value is not matching for field:" +m.getKey() +"  " +"Actual:"+db_data +"Expected:" +ui_data);
         }
		//Processed for
		actual_processedFor=db.getDbListValues(query2,"process_lookup");
		sa.assertEquals(actual_processedFor, expected_processedFor, "Processed for value is not matching" +"Actual:"+actual_processedFor +"Expected:" +expected_processedFor);
      
	}

	}