package com.zerv.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class XmlSetupPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(XmlSetupPage.class);
	private String options = "//mat-checkbox[@selenium-id='%s']/label/div/input";
	private String dropvalue = "//mat-select[@selenium-id='%s']";
	private String Dropdownvalues = "//mat-option[@role='option']/span[contains(text(),'%s')]";
	private String comments = "//textarea[@selenium-id='%s']";
	private By Passbutton = By.xpath("//mat-icon[contains(text(),'arrow_forward ')]");
	private By xmlsetup = By.xpath("//span[contains(text(), 'Structure Evaluation')]");
	private By Upload_File = By.xpath("//input[contains(@value,'Upload File(s)')]");

	public XmlSetupPage(WebDriver driver) {
		super(driver);

	}

	public void launchOnboardingUrl(String env, String role, String Publicaton_ID) {
		switch (env) {
		case "QA":
			navigateUrl(read.getProperty("Stable_URL") + role + "/" + Publicaton_ID);
			break;
		case "DEV":
			navigateUrl(read.getProperty("Dev_URL") + role + "/" + Publicaton_ID);
			break;
		default:
			navigateUrl(read.getProperty("Onboarding"));
			break;
		}

	}

	public void clickOnStructureEvaluation()

	{

		getElement(xmlsetup).click();

	}

	public void selectXMLSetupCheckboxValue(String option) {

		String xpath = getXPath(options, option);

		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info("XML Setup value for " + option + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));
			log.info("XML Setup value for " + option + " has been selected");
		}

	}

	public void clickOnDropDown(String Value) {

		String xpath = getXPath(dropvalue, Value);
		getElement(By.xpath(xpath)).click();
	}
	
	public void selectAllDropDownValues(String drpVal,String Value) {
		clickOnDropDown("surname");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}
	
	
	public void selectOnSurnameDropDown(String Value) {
		clickOnDropDown("surname");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOngivenNamesDropDown(String Value) {
		clickOnDropDown("givenNames");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnemailDropDown(String Value) {
		clickOnDropDown("email");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOncorrespondingauthorDropDown(String Value) {
		clickOnDropDown("corespondingAuthor");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnMainorganisationDropDown(String Value) {
		clickOnDropDown("mainOrganisation");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnSuborganisationDropDown(String Value) {
		clickOnDropDown("subOrganisation");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnStreetaddressDropDown(String Value) {
		clickOnDropDown("streetAddress");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnCityDropDown(String Value) {
		clickOnDropDown("city");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnStateProvince(String Value) {
		clickOnDropDown("stateOrProvince");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnCountry(String Value) {
		clickOnDropDown("country");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnRefStartDropDown(String Value) {
		clickOnDropDown("refStart");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectonReferenceTypeDropDown(String Value) {
		clickOnDropDown("referenceType");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnReferenceId(String Value) {
		clickOnDropDown("refID");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnAuthorsurnameDropDown(String Value) {
		clickOnDropDown("refAuthorSurname");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnArticleTitleDropDown(String Value) {
		clickOnDropDown("articleTitle");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectonJournalTitleDropDown(String Value) {
		clickOnDropDown("journalTitle");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnVolumeDropdown(String Value) {
		clickOnDropDown("volume");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnYearDropDown(String Value) {
		clickOnDropDown("year");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnFirstpageDropDown(String Value) {
		clickOnDropDown("firstPage");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectonLastpageDropDown(String Value) {
		clickOnDropDown("lastPage");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnDOIDropdown(String Value) {
		clickOnDropDown("doi");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnPMIDDropDown(String Value) {
		clickOnDropDown("pmid");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnPatentnoDropDown(String Value) {
		clickOnDropDown("patentNumber");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectonPatentCountryDropDown(String Value) {
		clickOnDropDown("patentCountry");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnPatentAssingniegivennamesDropdown(String Value) {
		clickOnDropDown("patentAssigneeGivenNames");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnPatentAssingniesurnamesDropdown(String Value) {
		clickOnDropDown("patentAssigneeSurname");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnPatentAssingnieCorporateDropdown(String Value) {
		clickOnDropDown("patentAssigneeGrpCrpName");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectonBookISBNDropDown(String Value) {
		clickOnDropDown("bookIsnn");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnfundingAcknowledgeStmtDropdown(String Value) {
		clickOnDropDown("fundingAcknowledgeStmt");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnfundingGrpDropdown(String Value) {
		clickOnDropDown("fundingGrp");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void selectOnfundingNumDropdown(String Value) {
		clickOnDropDown("fundingNum");
		String xpath = getXPath(Dropdownvalues, Value);
		getElement(By.xpath(xpath)).click();
	}

	public void EnterTextforItemStarttag(String Value) {
		String xpath = getXPath(comments, "comments1");
		enterTextinTextbox(getElement(By.xpath(xpath)), Value);

	}

	public void EnterTextforItemDocumentType(String Value) {
		String xpath = getXPath(comments, "comments2");
		enterTextinTextbox(getElement(By.xpath(xpath)), Value);

	}

	public void EnterTextforItemTitleg(String Value) {
		String xpath = getXPath(comments, "comments3");
		enterTextinTextbox(getElement(By.xpath(xpath)), Value);

	}

	public void ClickOnPassButton() {
		getElement(Passbutton).click();

	}
	
	public void ClickOnUploadFile() {
		getElement((Upload_File)).click();
		//clickJS(getElement(Upload_File));
		System.out.println("dodcuments uploaded");
		log.info("Upload File button is selected");
	}

}
