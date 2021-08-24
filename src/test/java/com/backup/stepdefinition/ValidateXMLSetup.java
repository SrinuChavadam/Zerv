package com.backup.stepdefinition;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

import com.zerv.api.utils.ReadProperty;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.XmlSetupPage;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class ValidateXMLSetup {
	Map<String,String> XmlSetup;
	Map<String,String> XmlSetup1;
	XmlSetupPage cbpe = new XmlSetupPage(Webutils.getDriver());
	SpreadSheetBaseFunction sbf = new SpreadSheetBaseFunction();
	ReadProperty read = new ReadProperty();
	String browser = "chrome";
	String env = "QA";	
	String publicationID = "";
	Logger log = Logger.getLogger(ValidateXMLSetup.class);

	@Given("^am XMl Setup user \"(.*?)\" and \"(.*?)\"$")
	public void am_XMl_Setup_user_and(String role, String publicationid) throws Throwable {
		publicationID = publicationid;
		cbpe.initiateBrowser(browser);
		cbpe.launchOnboardingUrl(env, role, publicationid );
		cbpe.insertJavaScript(read.getProperty("newsession"));
		cbpe.launchOnboardingUrl(env, role, publicationid );
		cbpe.pause(3000);
		cbpe.clickOnStructureEvaluation();
		cbpe.pause(5000);
	}

	@When("^user provides XMLsetup data \"(.*?)\" ,table \"(.*?)\" and table \"(.*?)\" and table\"(.*?)\"\\.$")
	public void user_provides_XMLsetup_data_table_and_table_and_table(String XMLSetup_Table, String XMLSetup_Table2, String XMLSetup_Table_Data1, String XMLSetup_Table2_Data2) throws Throwable {
		
		XmlSetup = sbf.getTableValues("XMLSetup", XMLSetup_Table, Integer.parseInt(XMLSetup_Table_Data1.substring(4)));
		XmlSetup1 = sbf.getTableValues("XMLSetup", XMLSetup_Table2, Integer.parseInt(XMLSetup_Table2_Data2.substring(4)));
		System.out.println(XmlSetup);
		List<String> result = XmlSetup.entrySet().stream().filter(x -> !x.getValue().equals(""))
		        .map(x -> x.getKey()).collect(Collectors.toList());
		for (String keyvalue : result) {
		    
		    cbpe.selectXMLSetupCheckboxValue(XmlSetup.get(keyvalue));
		}
		
	    cbpe.selectOnSurnameDropDown(XmlSetup1.get("surname"));
	    cbpe.selectOngivenNamesDropDown(XmlSetup1.get("givenNames"));	
	    cbpe.selectOnemailDropDown(XmlSetup1.get("email"));	
	    cbpe.selectOncorrespondingauthorDropDown(XmlSetup1.get("corespondingAuthor"));	
	    cbpe.selectOnMainorganisationDropDown(XmlSetup1.get("mainOrganisation"));
	    cbpe.selectOnSuborganisationDropDown(XmlSetup1.get("subOrganisation"));	
	    cbpe.selectOnStreetaddressDropDown(XmlSetup1.get("streetAddress"));	
	    cbpe.selectOnCityDropDown(XmlSetup1.get("city"));
	    cbpe.selectOnStateProvince(XmlSetup1.get("stateOrProvince"));
	    cbpe.selectOnRefStartDropDown(XmlSetup1.get("refStart"));
	    cbpe.selectonReferenceTypeDropDown(XmlSetup1.get("referenceType"));
	    cbpe.selectOnReferenceId(XmlSetup1.get("refID"));
	    cbpe.selectOnAuthorsurnameDropDown(XmlSetup1.get("refAuthorSurname"));
	    cbpe.selectOnArticleTitleDropDown(XmlSetup1.get("articleTitle"));
	    cbpe.selectonJournalTitleDropDown(XmlSetup1.get("journalTitle"));
	    cbpe.selectOnVolumeDropdown(XmlSetup1.get("volume"));
	    cbpe.selectOnYearDropDown(XmlSetup1.get("year"));
	    cbpe.selectOnFirstpageDropDown(XmlSetup1.get("firstPage"));
	    cbpe.selectonLastpageDropDown(XmlSetup1.get("lastPage"));
	    cbpe.selectOnDOIDropdown(XmlSetup1.get("doi"));
	    cbpe.selectOnPMIDDropDown(XmlSetup1.get("pmid"));
	    cbpe.selectOnPatentnoDropDown(XmlSetup1.get("patentNumber"));
	    cbpe.selectonPatentCountryDropDown(XmlSetup1.get("patentCountry"));
	    cbpe.selectOnPatentAssingniegivennamesDropdown(XmlSetup1.get("patentAssigneeGivenNames"));
	    cbpe.selectOnPatentAssingniesurnamesDropdown(XmlSetup1.get("patentAssigneeSurname"));
	    cbpe.selectOnPatentAssingnieCorporateDropdown(XmlSetup1.get("patentAssigneeGrpCrpName"));
	    cbpe.selectonBookISBNDropDown(XmlSetup1.get("bookIsnn"));
	    cbpe.selectOnfundingAcknowledgeStmtDropdown(XmlSetup1.get("fundingAcknowledgeStmt"));
	    cbpe.selectOnfundingGrpDropdown(XmlSetup1.get("fundingGrp"));
	    cbpe.selectOnfundingNumDropdown(XmlSetup1.get("fundingNum"));
	    cbpe.EnterTextforItemStarttag(XmlSetup1.get("comments1"));
	    cbpe.EnterTextforItemDocumentType(XmlSetup1.get("comments2"));
	    cbpe.EnterTextforItemTitleg(XmlSetup1.get("comments3"));
	    
	}

	@Then("^user click on pass button in XMl setup page$")
	public void user_click_on_pass_button_in_XMl_setup_page() throws Throwable {
		cbpe.ClickOnPassButton();
		cbpe.pause(5000);
		
	}
	
	@Then("^user upload the mapping document and click on pass button in upload document mapping page$")
	public void user_upload_the_mapping_document_and_click_on_pass_button_in_upload_document_mapping_page() throws Throwable {
		cbpe.ClickOnUploadFile();
		cbpe.pause(3000);
		cbpe.uploadFile(XmlSetup1.get("Attachment1"));
		cbpe.ClickOnPassButton();
	}

	}
