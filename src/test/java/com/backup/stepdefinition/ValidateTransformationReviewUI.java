package com.backup.stepdefinition;

import java.util.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.TransformationReviewFeedbackPage;
import com.zerv.runner.TestRunner;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class ValidateTransformationReviewUI extends TestRunner {
	
	TransformationReviewFeedbackPage trf=new TransformationReviewFeedbackPage(Webutils.getDriver());
	SpreadSheetBaseFunction sbf=new SpreadSheetBaseFunction();
	ReadProperty read = new ReadProperty();
	String browser = "chrome";
	String env = "QA";	
	Logger log = Logger.getLogger(ValidateTransformationReviewUI.class);
	String publicationID="";
	Set<String> attachment_Data = new HashSet<String>();
	Set<String> attachment_Data_DB = new HashSet<String>();
	List<String> actual_Data = new ArrayList<String>();
	SoftAssert sa=new SoftAssert();
	Set<String> publicationIDs;
	DatabaseUtils db=new DatabaseUtils();
	Map<String,String> reviewData;
	String[] files;
	int attachmentsCount=0;
	int attachmentsCount_DB=0;
	
	
	@Given("^am a XML Reviewer on Transformation Review feedback page\\.\"(.*?)\",\"(.*?)\"$")
	public void am_a_XML_Reviewer_on_Transformation_Review_feedback_page(String role, String Publication_Id) throws Throwable {
		trf.initiateBrowser(browser);
		publicationID=Publication_Id;
		trf.launchOnboardingUrl(env,role,Publication_Id);		
		trf.insertJavaScript(read.getProperty("newsession"));
		trf.launchOnboardingUrl(env,role,Publication_Id);	
		trf.pause(5000);
	}
	
	@When("^User updates defect status from open or inprogress to completed, attached ten files \"(.*?)\",\"(.*?)\" and save the page\\.$")
	public void user_updates_defect_status_from_open_or_inprogress_to_completed_attached_ten_files_and_save_the_page(String datacolumn,String tablename) throws Throwable {
		trf.clickOnReviewFeedbackLink();
		reviewData = sbf.getTableValues("ReviewFeedback", tablename,Integer.parseInt(datacolumn.substring(4)));
		files = reviewData.get("Files").split(",");
		trf.isWarningMessagePresent();
		trf.update_DefectDetails(reviewData.get("Status"));
		for (int i = 0; i < files.length; i++) {
			trf.clickOnButton("Upload");
			attachment_Data.add(files[i]);
			trf.uploadFile(files[i]);
			}
		trf.pageRefresh();
		trf.clickOnReviewFeedbackLink();
		trf.clickOnButton("Pass");
	}

	@Then("^Defects data should be saved into defect_details table\\.$")
	public void defects_data_should_be_saved_into_defect_details_table() throws Throwable {
		String query1="select status from defect_details where PUBLICATION_ID='" + publicationID + "'";
		String query2="select attachment_name from attachment_details where publication_id='" + publicationID + "'";
		actual_Data = db.getDbListOfValues(query1,"status");
		attachment_Data_DB=db.getDbListValues(query2,"attachment_name");
		//Defect status validation
		for(String data:actual_Data)
		{
			Assert.assertTrue(data.equals(reviewData.get("Status").toUpperCase()), "Status value is mismatched");
		}
	    //Attachment files validation
		Assert.assertEquals(attachment_Data_DB, attachment_Data);
		//System.out.println("files matched");
		attachmentsCount=db.getRowCount("select count(*) as count from format_evaluation where publication_id='"+publicationID+ "'");
	}
	
	//Scenario-2
	@When("^User uploaded any file and clicked on delete icon \"(.*?)\",\"(.*?)\"\\.$")
	public void user_uploaded_any_file_and_clicked_on_delete_icon(String arg1, String arg2) throws Throwable {
		trf.clickOnReviewFeedbackLink(); 
		trf.clickOnButton("delete");
		trf.pageRefresh();
		trf.clickOnReviewFeedbackLink(); 
		trf.clickOnButton("Pass");
	}

	@Then("^file name should be removed from attachments_details table\\.$")
	public void file_name_should_be_removed_from_attachments_details_table() throws Throwable {
		
		attachmentsCount_DB=db.getRowCount("select count(*) as count from format_evaluation where publication_id='"+publicationID+ "'");
		if(attachmentsCount_DB<attachmentsCount)
		{
			log.info("Attachment is deleted successfully");
		}
		else
		{
			log.info("Attachment is not deleted");
		}
	}

	}

