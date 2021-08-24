package com.zerv.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class BiblioPolicyEvaluationPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(BiblioPolicyEvaluationPage.class);
	private By lbl_BiblioEvaluationPolicy_Info = By.xpath("//mat-step-header[contains(@id,'cdk-step-label-0-0')]");

	private By ckbx_Numeric_Pagination = By.xpath(
			"//span[text()[normalize-space()='Numeric Pagination']]//preceding-sibling::div/input[@type='checkbox']");
	private By ckbx_Pagination_Continous = By.xpath(
			"//span[text()[normalize-space()='Pagination Continuous']]//preceding-sibling::div/input[@type='checkbox']");
	private By ckbx_Article_Number = By.xpath(
			"//span[text()[normalize-space()='Article Number']]//preceding-sibling::div/input[@type='checkbox']");
	private By ckbx_DOI = By
			.xpath("//span[text()[normalize-space()='DOI']]//preceding-sibling::div/input[@type='checkbox']");

	private By btn_Pass = By.xpath("//span[contains(.,'arrow_forward Pass')]");
	private By txt_Evalution_Result = By.xpath("(//ckeditor/textarea)[1]");
	private By txt_Processing_Team = By.xpath("(//ckeditor/textarea)[2]");
	private By btn_Reject = By.xpath("//span[contains(.,'Reject')]");
	private By btn_Yes_Reject = By.xpath("//span[contains(.,'Yes,reject')]");
	private By btn_complete = By.xpath("//span[contains(.,'Complete')]");
	private By txt_Reason_Reject = By.xpath("(//ckeditor/textarea)[1]");

	public BiblioPolicyEvaluationPage(WebDriver driver) {
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
	
    /**
    * Author:Manimaran
     */
	public void clickOnBiblioEvaluationPolicyLink()

	{

		getElement(lbl_BiblioEvaluationPolicy_Info).click();

	}

	public void ClickOnNumeric_Pagination() {

		if (getElement(ckbx_Numeric_Pagination).isSelected()) {
			log.info("Numeric_Pagination option is already selected");
		} else {
			clickJS(getElement(ckbx_Numeric_Pagination));

		}

	}

	public void ClickOnPagination_Continous() {

		if (getElement(ckbx_Pagination_Continous).isSelected()) {
			log.info("Pagination_Continous option is already selected");
		} else {
			clickJS(getElement(ckbx_Pagination_Continous));

		}

	}

	public void ClickOnArticle_Number() {

		if (getElement(ckbx_Article_Number).isSelected()) {
			log.info("Article_Number option is already selected");
		} else {
			clickJS(getElement(ckbx_Article_Number));
		}

	}

	public void ClickOnDOI() {

		if (getElement(ckbx_DOI).isSelected()) {
			log.info("DOI option is already selected");
		} else {
			clickJS(getElement(ckbx_DOI));
		}

	}

	public void ClickOnPass() {
		getElement(btn_Pass).click();

	}

	public void ClickOnReject() {
		getElement(btn_Reject).click();

	}

	public void enterReasonReject(String value) {
		enterTextinTextbox(getElement(txt_Reason_Reject), value);

	}

	public void ClickOnYesReject() {
		getElement(btn_Yes_Reject).click();

	}

	public void enterEvaluationResult(String value) {
		enterTextinTextbox(getElement(txt_Evalution_Result), value);

	}

	public void enterProcessingTeam(String value) {
		enterTextinTextbox(getElement(txt_Processing_Team), value);

	}

	public void clickOnComplete() {
		getElement(btn_complete).click();

	}

	public void UnClickOnNumeric_Pagination() {

		if (getElement(ckbx_Numeric_Pagination).isSelected()) {
			clickJS(getElement(ckbx_Numeric_Pagination));
		} else {

			log.info("Numeric_Pagination option is already unselected");
		}

	}

	public void UnClickOnPagination_Continous() {

		if (getElement(ckbx_Pagination_Continous).isSelected()) {
			clickJS(getElement(ckbx_Pagination_Continous));
		} else {

			log.info("Pagination_Continous option is already unselected");
		}

	}

	public void UnClickOnArticle_Number() {

		if (getElement(ckbx_Article_Number).isSelected()) {
			clickJS(getElement(ckbx_Article_Number));
		} else {

			log.info("Article_Number option is already unselected");
		}

	}

	public void UnClickOnDOI() {

		if (getElement(ckbx_DOI).isSelected()) {
			clickJS(getElement(ckbx_DOI));
		} else {

			log.info("DOI option is already unselected");
		}

	}

}
