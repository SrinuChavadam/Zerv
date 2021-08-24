package com.backup.stepdefinition;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.cucumber.listener.Reporter;
import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.page.*;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class ValidateBibilioSetupUI {

	BiblioPolicyEvaluationPage cbpe = new BiblioPolicyEvaluationPage(Webutils.getDriver());
	ScholarlyArtifactPage csap = new ScholarlyArtifactPage(Webutils.getDriver());
	JobNameAuthorityFilePage jafp = new JobNameAuthorityFilePage(Webutils.getDriver());
	PDFXMLElementInventoryPage peip = new PDFXMLElementInventoryPage(Webutils.getDriver());
	DatabaseUtils db = new DatabaseUtils();
	SpreadSheetBaseFunction sbf = new SpreadSheetBaseFunction();
	ReadProperty read = new ReadProperty();
	//String browser = System.getProperty("browser");
	//String env = System.getProperty("env");
	String browser = "chrome";
	String env = "QA";
	Map<String, String> bibilioData;
	Map<String, String> ScholarlyData;
	Map<String, String> JobNameAuthorityFile;
	Map<String, String> JobNameAuthorityInput;
	Map<String, String> PDFXMLElementInventory;
	String publicationID = "";
	Logger log = Logger.getLogger(ValidateBibilioSetupUI.class);

	@Given("^am content Biblio user \"(.*?)\" and \"(.*?)\"$")
	public void am_on_content_biblio_user_something_something(String role, String publicationid) throws Throwable {
		publicationID = publicationid;
		cbpe.initiateBrowser(browser);
		cbpe.launchOnboardingUrl(env, role, publicationid);
		cbpe.insertJavaScript(read.getProperty("newsession"));
		cbpe.launchOnboardingUrl(env, role, publicationid);
		cbpe.pause(3000);

	}

	@Then("^user clicks Reject button$")
	public void user_clicks_reject_button() throws Throwable {
		cbpe.ClickOnReject();
		cbpe.pause(3000);
		cbpe.enterReasonReject(bibilioData.get("EvaluationResult"));
		cbpe.enterProcessingTeam(bibilioData.get("ProcessingTeam"));
		cbpe.pause(3000);
		cbpe.ClickOnYesReject();
		cbpe.quitBrowsers();
	}

	@When("^user provides Bibliographic Policy Evaluation data \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_Bibliographic_Policy_Evaluation_data_and_table(String tableName, String data)
			throws Throwable {
		bibilioData = sbf.getTableValues("BibilioEvaluation", tableName, Integer.parseInt(data.substring(4)));
		cbpe.clickOnBiblioEvaluationPolicyLink();
		cbpe.pause(5000);

		if (bibilioData.get("Numeric_Pagination").equalsIgnoreCase("Yes")) {
			cbpe.ClickOnNumeric_Pagination();
		} else if (bibilioData.get("Numeric_Pagination").equalsIgnoreCase("NO")) {
			cbpe.UnClickOnNumeric_Pagination();
		}

		if (bibilioData.get("Pagination_Continuous").equalsIgnoreCase("Yes")) {
			cbpe.ClickOnPagination_Continous();

		} else if (bibilioData.get("Pagination_Continuous").equalsIgnoreCase("NO")) {
			cbpe.UnClickOnPagination_Continous();
		}

		if (bibilioData.get("Article_Number").equalsIgnoreCase("Yes")) {
			cbpe.ClickOnArticle_Number();
		} else if (bibilioData.get("Article_Number").equalsIgnoreCase("NO")) {
			cbpe.UnClickOnArticle_Number();
		}

		if (bibilioData.get("DOI").equalsIgnoreCase("Yes")) {
			cbpe.ClickOnDOI();
		} else if (bibilioData.get("DOI").equalsIgnoreCase("NO")) {
			cbpe.UnClickOnDOI();
		}

		cbpe.ClickOnPass();
		// cbpe.pause();
		cbpe.clickOnComplete();
		cbpe.pause(4000);
		cbpe.ClickOnPass();
		cbpe.pause(4000);
	}

	@Then("^system should be saved into database$")
	public void system_should_be_saved_into_database() throws Throwable {
		List<String> DBBibilioList = new ArrayList<String>();
		List<String> UIBibilioList = new ArrayList<String>();

		String query = "select numeric_pagination,pagination_continuous,article_number,digital_object_identifier from biblio_policy_evaluation where publication_id= '"
				+ publicationID + "'";

		DBBibilioList = db.getRowList(query);

		if (bibilioData.get("Numeric_Pagination").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		if (bibilioData.get("Pagination_Continuous").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		if (bibilioData.get("Article_Number").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		if (bibilioData.get("DOI").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		SoftAssert sa = new SoftAssert();
		Reporter.addStepLog(UIBibilioList.toString());
		Reporter.addStepLog(DBBibilioList.toString());
		sa.assertEquals(UIBibilioList, DBBibilioList, "Bibiblio policy evaluation values are mismatched in database");
		sa.assertAll();

	}

	@Then("^system should not saved into database$")
	public void ValidatedatabaseForReject() throws Throwable {
		List<String> DBBibilioList = new ArrayList<String>();
		List<String> UIBibilioList = new ArrayList<String>();

		DatabaseUtils db = new DatabaseUtils();

		String query = "select numeric_pagination,pagination_continuous,article_number,digital_object_identifier from biblio_policy_evaluation where publication_id= '"
				+ publicationID + "'";

		DBBibilioList = db.getRowList(query);

		if (bibilioData.get("Numeric_Pagination").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		if (bibilioData.get("Pagination_Continuous").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		if (bibilioData.get("Article_Number").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		if (bibilioData.get("DOI").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		SoftAssert sa = new SoftAssert();
		sa.assertNotEquals(UIBibilioList, DBBibilioList, "Bibiblio policy evaluation values are updated in database");
		sa.assertAll();

	}

	// Scholarly Artifact
	@When("^user provides Provided Document Type data \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_Provided_Document_Type_data_and_table(String tableName, String data) throws Throwable {

		List<String> li1 = new ArrayList<String>();
		ScholarlyData = sbf.getTableValues("ScholarlyArtifact", tableName, Integer.parseInt(data.substring(4)));
		for (String key : ScholarlyData.keySet()) {
			li1.add(key);
		}

		for (int i = 0; i <= 32; i++) {
			if (i + 1 == 33) {
				break;
			}
			csap.ClickAddButton(i + 1);
			csap.enterProvidedValue(ScholarlyData.get(li1.get(i)), i + 1);
			csap.ClickOnArticleCheckButton(i + 1);

		}

	}

	@Then("^system should be saved into Scholarly Artifact database$")
	public void system_should_be_saved_into_Scholarly_Artifact_database() throws Throwable {
		Map<String, String> db_map = new LinkedHashMap<String, String>();
		String query = "select * from scholarly_classification where publication_id='" + publicationID + "'";
		DatabaseUtils db = new DatabaseUtils();
		List<String> dbListOfclarivate_doc = db.getDbListOfValues(query, "clarivate_doc");

		List<String> dbListOfprovider_doc = db.getDbListOfValues(query, "provider_doc");
		for (int i = 0; i < dbListOfclarivate_doc.size(); i++) {
			db_map.put(dbListOfclarivate_doc.get(i), dbListOfprovider_doc.get(i));
		}

		ScholarlyData.forEach((key, value_ui) -> {
			String value_db = db_map.get(key);
			Assert.assertEquals(value_db, value_ui, "value is not matching for the key:" + key);

		});

	}

	@When("^user provides Provided Document Type data \"(.*?)\" and table \"(.*?)\"\\ for combination diffrent Clarivate Document Type.$")
	public void user_provides_Provided_Document_Type(String tableName, String data) throws Throwable {

		ScholarlyData = sbf.getTableValues("ScholarlyArtifact", tableName, Integer.parseInt(data.substring(4)));
		String[] article_data = ScholarlyData.get("Article").split(",");
		for (int i = 1; i < article_data.length; i++) {
			csap.ClickOnProvidedValue(i);
			log.info("Selected Provided Document Type Value is " + article_data[i - 1]);
			csap.ClickOnArticleCheckButton(i);
		}

	}

	@Then("^system should be saved UI inputs into database$")
	public void system_should_be_saved_UI_inputs_into_database() throws Throwable {

		List<String> DBBibilioList = new ArrayList<String>();
		List<String> UIBibilioList = new ArrayList<String>();

		DatabaseUtils db = new DatabaseUtils();

		String query = "select numeric_pagination,pagination_continuous,article_number,digital_object_identifier from biblio_policy_evaluation where publication_id= '"
				+ publicationID + "'";

		DBBibilioList = db.getRowList(query);

		if (bibilioData.get("Numeric_Pagination").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		if (bibilioData.get("Pagination_Continuous").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		if (bibilioData.get("Article_Number").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}
		if (bibilioData.get("DOI").equals("Yes")) {
			UIBibilioList.add("Y");
		} else {
			UIBibilioList.add("N");
		}

		SoftAssert sa = new SoftAssert();
		Map<String, String> db_map = new LinkedHashMap<String, String>();
		String query1 = "select * from scholarly_classification where publication_id='" + publicationID + "'";

		List<String> dbListOfclarivate_doc = db.getDbListOfValues(query1, "clarivate_doc");
		List<String> dbListOfprovider_doc = db.getDbListOfValues(query1, "provider_doc");
		for (int i = 0; i < dbListOfclarivate_doc.size(); i++) {
			db_map.put(dbListOfclarivate_doc.get(i), dbListOfprovider_doc.get(i));
		}

		ScholarlyData.forEach((key, value_ui) -> {
			if (value_ui != "") {
				String value_db = db_map.get(key);
				sa.assertEquals(value_db, value_ui, "value is not matching for the key:" + key);
			}

		});

		Map<String, String> presentPdfUIMap = new HashMap<String, String>();
		Map<String, String> presentXmlUIMap = new HashMap<String, String>();
		Map<String, String> presentPdfDbMap = new HashMap<String, String>();
		Map<String, String> presentXmlDb = new HashMap<String, String>();

		PDFXMLElementInventory.forEach((key, value) -> {
			if (!value.equals("") && (key.contains("PDF"))) {
				presentPdfUIMap.put(key.substring(key.indexOf("#") + 1, key.length()), "Y");
			} else if (value.equals("") && (key.contains("PDF"))) {
				presentPdfUIMap.put(key.substring(key.indexOf("#") + 1, key.length()), "N");
			} else if (!value.equals("") && (key.contains("XML"))) {
				presentXmlUIMap.put(key.substring(key.indexOf("#") + 1, key.length()), "Y");
			} else if (value.equals("") && (key.contains("XML"))) {
				presentXmlUIMap.put(key.substring(key.indexOf("#") + 1, key.length()), "N");
			}

		});

		System.out.println(presentPdfUIMap);
		System.out.println(presentXmlUIMap);

		String query2 = "select * from biblio_format_inventory where publication_id='" + publicationID + "'";

		List<String> questlookupDB = db.getDbListOfValues(query2, "quest_lookup_id");
		List<String> presentpdfDb = db.getDbListOfValues(query2, "present_pdf");
		List<String> presentxmlDb = db.getDbListOfValues(query2, "present_xml");

		for (int i = 0; i < questlookupDB.size(); i++) {
			presentPdfDbMap.put(questlookupDB.get(i), presentpdfDb.get(i));
			presentXmlDb.put(questlookupDB.get(i), presentxmlDb.get(i));

		}

		System.out.println(presentPdfDbMap);
		System.out.println(presentXmlDb);

		Reporter.addStepLog(UIBibilioList.toString());
		Reporter.addStepLog(DBBibilioList.toString());
		sa.assertEquals(UIBibilioList, DBBibilioList, "Bibiblio policy evaluation values are mismatched in database");

		sa.assertAll();

	}

	@When("^user provides Provided Document Type as Article,Letter and Editorial material data \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_Provided_Document_Type_as_Article_Letter_and_Editorial_material_data_and_table(
			String tableName, String data) throws Throwable {
		ScholarlyData = sbf.getTableValues("ScholarlyArtifact", tableName, Integer.parseInt(data.substring(4)));
		List<String> result = ScholarlyData.entrySet().stream().filter(x -> !x.getValue().equals(""))
				.map(x -> x.getKey()).collect(Collectors.toList());

		for (String keyValue : result) {
			switch (keyValue) {
			case "Article":
				csap.clickOnArticleValue();
				break;
			case "Letter":
				csap.clickOnLetterValue();
				break;
			case "Editorial material":
				csap.clickOnEditorialMaterial();
				break;
			default:
				break;

			}
		}
		csap.ClickOnPass();
	}

	@When("^user provides Clarivate Document Type and Provided Document Type data \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_Clarivate_Document_Type_and_Provided_Document_Type_data_and_table(String tableName,
			String data) throws Throwable {
		ScholarlyData = sbf.getTableValues("ScholarlyArtifact", tableName, Integer.parseInt(data.substring(4)));

		csap.addClarivateDocTypeBtn();
		csap.enterClarivateDocTypeArea1(ScholarlyData.entrySet().stream().filter(x -> !x.getValue().equals(""))
				.map(x -> x.getKey()).collect(Collectors.joining()));
		csap.enterProvidedDocumentTypeArea1(ScholarlyData.get("Sample Article"));
		csap.ClickOnPass();
	}

	@When("^user provides Provided Document Type value Reprint data \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_Provided_Document_Type_value_Reprint_data_and_table(String tableName, String data)
			throws Throwable {
		ScholarlyData = sbf.getTableValues("ScholarlyArtifact", tableName, Integer.parseInt(data.substring(4)));
		csap.ClickAddButton(14);
		csap.enterReprint(ScholarlyData.get("Reprint"));
		csap.ClickOnPass();
		csap.pause(4000);
	}

	@When("^user provides input data for JobNameAuthorityFile \"(.*?)\" and table \"(.*?)\"$")
	public void user_provides_input_data_for_JobNameAuthorityFile_and_table(String tableName, String data)
			throws Throwable {
		String JobName = "";
		String AuthorityData = "Data1";
		JobNameAuthorityFile = sbf.getTableValues("JobNameAuthorityFile", tableName,
				Integer.parseInt(data.substring(4)));
		JobNameAuthorityInput = sbf.getTableValues("JobNameAuthorityInput", "JobNameAuthorityInput",
				Integer.parseInt(AuthorityData.substring(4)));
		jafp.clickDropdownJobName();

		String query = "select processed_lookup_value from processed_format_lookup where processed_lookup_id in (select format_lookup_id from format_to_process where publication_id='"
				+ publicationID + "')";
		String ProcessedToBeFormatDBValue = db.getDbSingleValue(query, "processed_lookup_value");

		switch (ProcessedToBeFormatDBValue) {
		case "PDF Only": {
			JobName = JobNameAuthorityFile.get("JobName52");
			break;
		}
		case "XML Only": {
			JobName = JobNameAuthorityFile.get("JobName34");
			break;
		}
		case "Print": {
			JobName = JobNameAuthorityFile.get("JobName16");
			break;
		}
		case "PDF & XML": {
			JobName = JobNameAuthorityFile.get("JobName34");
			break;
		}
		default:
			break;

		}

		jafp.selectDropDownValue(JobName);
		jafp.clickOnConfirmButton();
		jafp.pause(2000);
		String templateValue = jafp.getTemplateValue();
		System.out.println(templateValue);
		System.out.println(JobName);
		System.out.println(JobNameAuthorityInput);
		System.out.println(JobNameAuthorityInput.get(JobName));
		jafp.clickOnUpdateButton();
		jafp.clickOnNextButton();
		Assert.assertEquals(JobNameAuthorityInput.get(JobName), templateValue, "Job Name are mapped wrongly");

	}

	@When("^user provides input data for JobNameAuthorityFile \"(.*?)\" and table \"(.*?)\" for all possible Job Name\\.$")
	public void user_provides_input_data_for_JobNameAuthorityFile_and_table_for_all_possible_Job_Name(String tableName,
			String data) throws Throwable {
		String JobName = "";
		String AuthorityData = "Data1";
		JobNameAuthorityFile = sbf.getTableValues("JobNameAuthorityFile", tableName,
				Integer.parseInt(data.substring(4)));
		JobNameAuthorityInput = sbf.getTableValues("JobNameAuthorityInput", "JobNameAuthorityInput",
				Integer.parseInt(AuthorityData.substring(4)));
		String query = "select processed_lookup_value from processed_format_lookup where processed_lookup_id in (select format_lookup_id from format_to_process where publication_id='"
				+ publicationID + "')";
		String ProcessedToBeFormatDBValue = db.getDbSingleValue(query, "processed_lookup_value");
		switch (ProcessedToBeFormatDBValue) {
		case "PDF Only": {
			JobName = JobNameAuthorityFile.get("JobName54");
			break;
		}
		case "XML Only": {
			JobName = JobNameAuthorityFile.get("JobName56");
			break;
		}
		case "Print": {
			JobName = JobNameAuthorityFile.get("JobName55");
			break;
		}
		case "PDF & XML": {
			JobName = JobNameAuthorityFile.get("JobName56");
			break;
		}
		default:
			break;

		}
		String jobs[] = JobName.split(",");
		for (int i = 0; i < jobs.length; i++) {
			jafp.clickDropdownJobName();
			jafp.selectDropDownValue(jobs[i]);
			jafp.clickOnConfirmButton();
			jafp.pause(2000);
			String templateValue = jafp.getTemplateValue();
			Reporter.addStepLog("Authority file Template Name present in UI is : " + templateValue);
			Reporter.addStepLog("Authority file Template name for job Name " + jobs[i] + " is present in spec as : "
					+ JobNameAuthorityInput.get(jobs[i]));

			SoftAssert sa = new SoftAssert();
			sa.assertEquals(JobNameAuthorityInput.get(jobs[i]), templateValue, "Job Name are mapped wrongly");
			sa.assertAll();
		}

	}

	@When("^user provides input data for JobNameAuthorityFile \"(.*?)\" and table \"(.*?)\" for update the template\\.$")
	public void user_provides_input_data_for_JobNameAuthorityFile_and_table_for_update_the_template(String tableName,
			String data) throws Throwable {
		String JobName = "";
		String AuthorityData = "Data1";
		JobNameAuthorityFile = sbf.getTableValues("JobNameAuthorityFile", tableName,
				Integer.parseInt(data.substring(4)));
		JobNameAuthorityInput = sbf.getTableValues("JobNameAuthorityInput", "JobNameAuthorityInput",
				Integer.parseInt(AuthorityData.substring(4)));
		jafp.clickDropdownJobName();

		String query = "select processed_lookup_value from processed_format_lookup where processed_lookup_id in (select format_lookup_id from format_to_process where publication_id='"
				+ publicationID + "')";
		String ProcessedToBeFormatDBValue = db.getDbSingleValue(query, "processed_lookup_value");

		switch (ProcessedToBeFormatDBValue) {
		case "PDF Only": {
			JobName = JobNameAuthorityFile.get("JobName52");
			break;
		}
		case "XML Only": {
			JobName = JobNameAuthorityFile.get("JobName34");
			break;
		}
		case "Print": {
			JobName = JobNameAuthorityFile.get("JobName16");
			break;
		}
		case "PDF & XML": {
			JobName = JobNameAuthorityFile.get("JobName34");
			break;
		}
		default:
			break;

		}

		jafp.selectDropDownValue(JobName);
		jafp.clickOnConfirmButton();
		jafp.pause(2000);
		jafp.clickOnUpdateButton();
		jafp.switchToFrame("iframe");
		jafp.pause(2000);
		jafp.enterTextInTemplate("Sample test need to update");
		jafp.switchToDefault();
		jafp.clickOnNextButton();
	}

	@Then("^user provides input data for PDFXMLElementInventory \"(.*?)\" and table \"(.*?)\"\\.$")
	public void user_provides_input_data_for_PDFXMLElementInventory_and_table(String tableName, String data)
			throws Throwable {
		PDFXMLElementInventory = sbf.getTableValues("PDFXMLElementInventory", tableName,
				Integer.parseInt(data.substring(4)));
		List<String> result = PDFXMLElementInventory.entrySet().stream().filter(x -> !x.getValue().equals(""))
				.map(x -> x.getKey()).collect(Collectors.toList());
		for (String keyvalue : result) {
			peip.selectPDFXMLElementInventoryValue(PDFXMLElementInventory.get(keyvalue));
		}

		peip.clickOnDoneBtn();
	}

}
