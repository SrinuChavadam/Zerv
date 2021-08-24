package com.backup.stepdefinition;

import java.util.*;
import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;

import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.*;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class ValidateAcquisitionSetupUI {
	
	FormatInfoPage cfip=new FormatInfoPage(Webutils.getDriver());	
	AcquisitionInfoPage cape=new AcquisitionInfoPage(Webutils.getDriver());
	TaskChecklistPage ctcp=new TaskChecklistPage(Webutils.getDriver());
	
	SpreadSheetBaseFunction sbf = new SpreadSheetBaseFunction();
	Map<String, String> formatInfo;
	Map<String, String> acquisitionInfo;
	Map<String, String> taskCheckList;
	ReadProperty read = new ReadProperty();
	//String browser = System.getProperty("browser");
	//String env = System.getProperty("env");
	String browser = "chrome";
	String env = "QA";	
	String publication_Id=null;
	Logger log = Logger.getLogger(ValidateAcquisitionSetupUI.class);
	
	@Given("^am on content acquisition user \"(.*?)\" ,\"(.*?)\"$")
	public void am_on_content_acquisition_user(String role, String Publication_Id) throws Throwable {
		publication_Id=Publication_Id;
		cfip.initiateBrowser(browser);
		cfip.launchOnboardingUrl(env,role,Publication_Id);		
		cfip.insertJavaScript(read.getProperty("newsession"));
		cfip.launchOnboardingUrl(env,role,Publication_Id);	
	    Thread.sleep(6000);
	}
 


@When("^user provides Format Info data \"(.*?)\" and table \"(.*?)\"\\.$")
public void user_provides_Format_Info_data_and_table(String tableName, String data) throws Throwable {
	formatInfo = sbf.getTableValues("FormatInfo", tableName, Integer.parseInt(data.substring(4)));
	
	cfip.pause(10000);
	cfip.clickOnFormatTab();
	cfip.pause(3000);	
	cfip.AvailableFormatValue(formatInfo.get("FormatTypeAvailable"));
	cfip.processedFormatValue(formatInfo.get("FormatTypeToProcessed"));
	
	if(!formatInfo.get("XMLType").equals(""))
	{
		cfip.xmlOnlyValue(formatInfo.get("XMLType"));		
	}
		
	cfip.ClickOnNextButton();	    
}

@When("^user provides Acquisition Info data \"(.*?)\" and table \"(.*?)\"\\.$")
public void user_provides_Acquisition_Check_List_data_and_table(String tableName, String data) throws Throwable {
	cape.pause(3000);
	acquisitionInfo = sbf.getTableValues("AcquisitionInfo", tableName, Integer.parseInt(data.substring(4)));
    cape.PublisherCategoryValue(acquisitionInfo.get("Publisher Category"));
    cape.enterSchemaOne(acquisitionInfo.get("Schema 1"));
    cape.enterSchemaTwo(acquisitionInfo.get("Schema 2"));
    cape.enterSchemaThree(acquisitionInfo.get("Schema 3"));    
    cape.ReceiptMethodValue(acquisitionInfo.get("Receipt Method"));
    cape.TransmissionTypeValue(acquisitionInfo.get("Transmission Type"));
    cape.FTPReceiptMethodValue(acquisitionInfo.get("FTP Receipt Method"));
    cape.contentSizeValue(acquisitionInfo.get("content_size"));
    cape.clickOnSummaryInfo();
    cape.clickOnPriority();
    cape.priorityValue(acquisitionInfo.get("Priority"));
    cape.summaryInfoValue(acquisitionInfo.get("Summary info Value"));
    cape.ClickOnNextButton_Acquisiation();
}

@When("^user provides Task Check List data \"(.*?)\" and table \"(.*?)\"\\.$")
public void user_provides_Task_Check_List_data_and_table(String tableName, String data) throws Throwable {
	
	taskCheckList = sbf.getTableValues("TaskChecklist", tableName, Integer.parseInt(data.substring(4)));
	ctcp.taskChecklistValue(taskCheckList.get("Task Checklist"));
	ctcp.pause(3000);
	ctcp.ClickOnPassButton();
	//ctcp.switchToAlert();
	ctcp.switchToFrame("iframe");
	ctcp.pause(2000);
	ctcp.enterTextInTemplate("Sample test need to update");
	ctcp.pressEnter();
	ctcp.switchToDefault();	
	ctcp.pause(3000);
	//ctcp.clickOnBold();
	//ctcp.ClickOnCompleteButton();
	
	ctcp.pause(3000);
	
}


@Then("^The system should save the selected values into the Database$")
public void the_system_should_save_the_selected_values_into_the_Database() throws Throwable {
	DatabaseUtils db=new DatabaseUtils();
	Set<String> availableFormatUI=new TreeSet<String>();
	Set<String> taskCheckListUI=new TreeSet<String>();
	Map<String,String> acquisitionSetupUi=new HashMap<String,String>();
	String query1="select available_lookup_value from available_format_lookup where available_lookup_id in (select format_lookup_id from available_format where publication_id='"+publication_Id+"')";
	String query2="select content_type_lookup_value from content_type_lookup where content_type_lookup_id in (select content_type_lookup_id from format_to_process where publication_id='"+publication_Id+"')";
	String query3="select processed_lookup_value from processed_format_lookup where processed_lookup_id in (select format_lookup_id from format_to_process where publication_id='"+publication_Id+"')";
	String query4="select task_checklist_lookup_value from task_checklist_lookup where task_checklist_lookup_id in (select task_checklist_lookup_id from task_checklist where publication_id='"+publication_Id+"')";
	String query5="select * from acquisition_info where publication_id='"+publication_Id+"'";
	
	Set<String> availableFormatDB = db.getDbListValues(query1, "available_lookup_value");
	Set<String> taskCheckListDB = db.getDbListValues(query4, "task_checklist_lookup_value");
	Map<String, String> acquisitionSetupDB = db.getRowMap(query5);
	String[] formatInfoval = formatInfo.get("FormatTypeAvailable").split(",");
	for (int i = 0; i < formatInfoval.length; i++) {
		availableFormatUI.add(formatInfoval[i]);
	}
	
	
	String[] taskCheckListval = taskCheckList.get("Task Checklist").split(",");
	for (int i = 0; i < taskCheckListval.length; i++) {
		taskCheckListUI.add(taskCheckListval[i]);
	}
	
	String ProcessedToBeFormatDBValue = db.getDbSingleValue(query3, "processed_lookup_value");
	String ProcessedToBeFormatUIValue=formatInfo.get("FormatTypeToProcessed");
		
	String XMLOnlyDBValue = db.getDbSingleValue(query2, "content_type_lookup_value");
	String XMLOnlyUIValue=formatInfo.get("XMLType");
	

	switch (acquisitionInfo.get("Publisher Category")){
	case "NLM":
		acquisitionSetupUi.put("publisher_category", "1");
		break;
		
	case "Non-NLM":
		acquisitionSetupUi.put("publisher_category", "2");
		break;
		
		default:
			break;
	}
	
		 
	 switch (acquisitionInfo.get("Receipt Method")) {
		case "FTP":
			acquisitionSetupUi.put("receipt_method", "1");
			break;
		case "Email":
			acquisitionSetupUi.put("receipt_method", "2");
			break;
		case "Web":
			acquisitionSetupUi.put("receipt_method", "3");
			break;
						}
	 
	 switch (acquisitionInfo.get("Transmission Type")) {
		case "Push":
			acquisitionSetupUi.put("transmission_type", "1");
			break;
		case "Pull":
			acquisitionSetupUi.put("transmission_type", "2");
			break;
		
						}
	 
	 switch (acquisitionInfo.get("FTP Receipt Method")) {
		case "FTP":
			acquisitionSetupUi.put("ftp_type", "1");
			break;
		case "SFTP":
			acquisitionSetupUi.put("ftp_type", "2");
			break;
		case "FTPS":
			acquisitionSetupUi.put("ftp_type", "3");;
			break;
		case "":
			acquisitionSetupUi.put("ftp_type", null);
			break;
		
						}
	 switch (acquisitionInfo.get("content_size")) {
		case "<1GB":
			acquisitionSetupUi.put("content_size", "1");
			break;
		case "1-5GB":
			acquisitionSetupUi.put("content_size", "2");
			break;
		case ">5GB":
			acquisitionSetupUi.put("content_size", "3");
			break;
		case "":
			acquisitionSetupUi.put("content_size", null);
			break;
		
						}
	 
	 switch (acquisitionInfo.get("Browser Type")) {
		case "Firefox":
			acquisitionSetupUi.put("browser_type", "1");
			break;
		case "HTML UNIT":
			acquisitionSetupUi.put("browser_type", "2");
			break;
		case "Chrome":
			acquisitionSetupUi.put("browser_type", "3");
			break;
		case "":
			acquisitionSetupUi.put("browser_type", null);
			break;
	 }
	 
	 switch (acquisitionInfo.get("Acquisition Trigger")) {
		case "Email":
			acquisitionSetupUi.put("aquisition_trigger", "1");
			break;
		case "Scheduled":
			acquisitionSetupUi.put("aquisition_trigger", "2");
			break;
		case "":
			acquisitionSetupUi.put("aquisition_trigger", null);
			break;
		
	 }		
	 
	 if(acquisitionInfo.get("receipt_url").equals(""))
	 {
		 
		 acquisitionSetupUi.put("receipt_url", null);
		 
	 }
	 else
	 {
		 acquisitionSetupUi.put("receipt_url", acquisitionInfo.get("receipt_url")); 
	 }
	  	 	 
	 
	 if(acquisitionInfo.get("User ID").equals(""))
	 {
		 acquisitionSetupUi.put("user_id", null);
	 }
	 else
	 {
		 acquisitionSetupUi.put("user_id", acquisitionInfo.get("User ID")); 
	 }
	 
	 	
	 if(acquisitionInfo.get("Schema 1").equals("") && acquisitionInfo.get("Schema 2").equals("") && acquisitionInfo.get("Schema 3").equals("")) {
		 acquisitionSetupUi.put("publisher_schema_version",null); 
	 }
	 else
	 {
		 acquisitionSetupUi.put("publisher_schema_version", acquisitionInfo.get("Schema 1")+"~"+acquisitionInfo.get("Schema 2")+"~"+acquisitionInfo.get("Schema 3")); 
		 
	 }
	
	 String[] str=acquisitionInfo.get("Summary info Value").split(",");
		for (int i = 0; i < str.length; i++) {
			switch (str[i]) {
			case "Sensitive Data":
				acquisitionSetupUi.put("sensitive_data", "Y");
				break;
			case "Timezone Restriction":
				acquisitionSetupUi.put("restrict_timezone", "Y");
				break;
			case "Duplicated Check":
				acquisitionSetupUi.put("check_duplicates", "Y");
				break;
			case "Paid":
				acquisitionSetupUi.put("paid_content", "Y");
				break;
			case "Content Metadata":
				acquisitionSetupUi.put("content_metadata", "Y");
				break;
			
		 }		
		
		}
	 
			
	SoftAssert sa=new SoftAssert();
	sa.assertEquals(availableFormatDB, availableFormatUI, "Available Format List Values are mismatched from Database");
	sa.assertEquals(ProcessedToBeFormatDBValue, ProcessedToBeFormatUIValue, "Processed To Be Format Values are mismatched from Database");
	sa.assertEquals(XMLOnlyDBValue, XMLOnlyUIValue, "XML Only Values are mismatched from Database");
	sa.assertEquals(taskCheckListDB, taskCheckListUI, "Task CheckList Values are mismatched from Database");
	acquisitionSetupUi.forEach((key_ui,value_ui)->
	{		
		String value_db=acquisitionSetupDB.get(key_ui);
		sa.assertEquals(value_db, value_ui, "value is not matching for the key:"+key_ui);
		
	});
	
	sa.assertAll();
}

@When("^user provides Acquisition Info data \"(.*?)\" and table \"(.*?)\" for Receipt Method as Web\\.$")
public void user_provides_Acquisition_Info_data_and_table_for_Receipt_Method_as_Web(String tableName, String data) throws Throwable {
    
	cape.pause(3000);
	acquisitionInfo = sbf.getTableValues("AcquisitionInfo", tableName, Integer.parseInt(data.substring(4)));
    cape.PublisherCategoryValue(acquisitionInfo.get("Publisher Category"));
    cape.enterSchemaOne(acquisitionInfo.get("Schema 1"));
    cape.ReceiptMethodValue(acquisitionInfo.get("Receipt Method"));
    cape.TransmissionTypeValue(acquisitionInfo.get("Transmission Type"));
    cape.clickOnTrust_Certificates();
    cape.browserTypeValue(acquisitionInfo.get("Browser Type"));
    cape.clickOnSummaryInfo();
    cape.clickOnPriority();
    cape.priorityValue(acquisitionInfo.get("Priority"));
    cape.summaryInfoValue(acquisitionInfo.get("Summary info Value"));
    cape.ClickOnNextButton_Acquisiation();	
	
}
@When("^user provides Acquisition Info data \"(.*?)\" and table \"(.*?)\" for Receipt Method as Web and transmission Type as Pull\\.$")
public void user_provides_Acquisition_Info_data_and_table_for_Receipt_Method_as_Web_and_transmission_Type_as_Pull(String tableName, String data) throws Throwable {
	cape.pause(3000);
	acquisitionInfo = sbf.getTableValues("AcquisitionInfo", tableName, Integer.parseInt(data.substring(4)));
    cape.PublisherCategoryValue(acquisitionInfo.get("Publisher Category"));
    cape.enterSchemaOne(acquisitionInfo.get("Schema 1"));
    cape.ReceiptMethodValue(acquisitionInfo.get("Receipt Method"));
    cape.TransmissionTypeValue(acquisitionInfo.get("Transmission Type"));
    cape.enterOnWebURL(acquisitionInfo.get("receipt_url"));
    cape.enterOnUserId(acquisitionInfo.get("User ID"));
    cape.enterOnPassword(acquisitionInfo.get("Password"));
    //cape.enterOnWebSleepTime(acquisitionInfo.get("Sleep Time"));
    cape.clickOnTrust_Certificates();
    cape.browserTypeValue(acquisitionInfo.get("Browser Type"));
    cape.acquisitionTriggerValue(acquisitionInfo.get("Acquisition Trigger")); 
    cape.clickOnAttachment();
    cape.clickOnSummaryInfo();
    cape.ClickOnUploadFile();
    cape.pause(3000);
    cape.uploadFile(acquisitionInfo.get("Attachment1"));
    cape.pause(8000);
    cape.uploadFile(acquisitionInfo.get("Attachment2"));    
    cape.pause(8000);
    cape.uploadFile(acquisitionInfo.get("Attachment3"));
    cape.pause(8000); 
    cape.uploadFile(acquisitionInfo.get("Attachment4"));
    cape.pause(4000);
    cape.clickOnPriority();
    cape.priorityValue(acquisitionInfo.get("Priority"));
    cape.summaryInfoValue(acquisitionInfo.get("Summary info Value"));
    cape.ClickOnNextButton_Acquisiation();	
}

@When("^user provides Acquisition Info data \"(.*?)\" and table \"(.*?)\" for Receipt Method as FTP and transmission Type as Pull\\.$")
public void user_provides_Acquisition_Info_data_and_table_for_Receipt_Method_as_FTP_and_transmission_Type_as_Pull(String tableName, String data) throws Throwable {
	cape.pause(3000);
	acquisitionInfo = sbf.getTableValues("AcquisitionInfo", tableName, Integer.parseInt(data.substring(4)));
    cape.PublisherCategoryValue(acquisitionInfo.get("Publisher Category"));
    cape.enterSchemaOne(acquisitionInfo.get("Schema 1"));
    cape.ReceiptMethodValue(acquisitionInfo.get("Receipt Method"));
    cape.TransmissionTypeValue(acquisitionInfo.get("Transmission Type"));  
    cape.enterOnFTPPath(acquisitionInfo.get("receipt_url"));
    cape.enterOnUserId(acquisitionInfo.get("User ID"));
    cape.enterOnPassword(acquisitionInfo.get("Password"));
    cape.acquisitionTriggerValue(acquisitionInfo.get("Acquisition Trigger"));
    cape.FTPReceiptMethodValue(acquisitionInfo.get("FTP Receipt Method"));
    cape.clickOnSummaryInfo();
    cape.clickOnPriority();
    cape.priorityValue(acquisitionInfo.get("Priority"));
    cape.summaryInfoValue(acquisitionInfo.get("Summary info Value"));
    cape.ClickOnNextButton_Acquisiation();	
}
@When("^user provides Acquisition Info data \"(.*?)\" and table \"(.*?)\" for Receipt Method as Email\\.$")
public void user_provides_Acquisition_Info_data_and_table_for_Receipt_Method_as_Email(String tableName, String data) throws Throwable {
	cape.pause(3000);
	acquisitionInfo = sbf.getTableValues("AcquisitionInfo", tableName, Integer.parseInt(data.substring(4)));
    cape.PublisherCategoryValue(acquisitionInfo.get("Publisher Category"));
    cape.enterSchemaOne(acquisitionInfo.get("Schema 1"));
    cape.enterSchemaTwo(acquisitionInfo.get("Schema 2"));
    cape.enterSchemaThree(acquisitionInfo.get("Schema 3"));
    cape.ReceiptMethodValue(acquisitionInfo.get("Receipt Method"));
    cape.enterOnEmailReceiptMethod(acquisitionInfo.get("receipt_url"));
    cape.ClickOnNextButton_Acquisiation();
}


@When("^user provides Acquisition Info data \"(.*?)\" and table \"(.*?)\" for Format Type to be Processed as PDF Only and Receipt Method as FTP and transmission Type as Push\\.$")
public void user_provides_Acquisition_Info_data_and_table_for_Format_Type_to_be_Processed_as_PDF_Only_and_Receipt_Method_as_FTP_and_transmission_Type_as_Push(String tableName, String data) throws Throwable {
	cape.pause(3000);
	acquisitionInfo = sbf.getTableValues("AcquisitionInfo", tableName, Integer.parseInt(data.substring(4)));      
    cape.ReceiptMethodValue(acquisitionInfo.get("Receipt Method"));
    cape.TransmissionTypeValue(acquisitionInfo.get("Transmission Type"));
    cape.FTPReceiptMethodValue(acquisitionInfo.get("FTP Receipt Method"));
    cape.contentSizeValue(acquisitionInfo.get("content_size"));
    cape.clickOnSummaryInfo();
    cape.clickOnPriority();
    cape.priorityValue(acquisitionInfo.get("Priority"));
    cape.summaryInfoValue(acquisitionInfo.get("Summary info Value"));
    cape.ClickOnNextButton_Acquisiation();
}


	}