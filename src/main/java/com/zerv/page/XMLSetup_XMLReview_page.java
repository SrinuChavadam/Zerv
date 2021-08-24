package com.zerv.page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class XMLSetup_XMLReview_page extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(XMLSetup_XMLReview_page.class);
	private By link_TransformationReview = By.xpath("//span[text()[normalize-space()='Transformation Review']]");
	// private By btn_AddDefect = By.xpath("//span[text()[normalize-space()='+ Add
	// Defect']]");

	private By txtarea_DefectDesc = By.xpath("//textarea[@formcontrolname='description']");
	private String drpdwn_Priority_Status = "//mat-select[@formcontrolname='%s']";
	/*
	 * private By
	 * drpdwn_Priority=By.xpath("//mat-select[@formcontrolname='priority']");
	 * private By drpdwn_Status=By.xpath("//mat-select[@formcontrolname='status']");
	 */
	private By priority_Options = By.xpath("//mat-option/span");
	private By txtarea_Remarks = By.xpath("//textarea[@formcontrolname='remarks']");

	private String button = "//button/span[text()[normalize-space()='%s']]";

	private String status = "//mat-toolbar-row/div/div[5][contains(text(),'%s')]";

	private By row_Defect = By.xpath("//mat-toolbar-row/div/div[5]");

	private By lbl_Open = By.xpath("//mat-toolbar-row/div/div[5][contains(text(),'Open')]");
	private By lbl_InProgress = By.xpath("//mat-toolbar-row/div/div[5][contains(text(),'InProgress')]");
	private By lbl_Complete = By.xpath("//mat-toolbar-row/div/div[5][contains(text(),'Complete')]");

	private By label_XMLSamples = By.xpath("//mat-panel-title[text()[normalize-space()='Transformed XML Samples']]");

	public XMLSetup_XMLReview_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Author:satya
	 * 
	 * @param env
	 * @param role
	 * @param Publicaton_ID
	 */
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
	 * Author:Satya
	 */
	public void clickOnTransformationReviewLink()

	{
		clickJS(getElement(link_TransformationReview));

	}

	/**
	 * Author:Satya
	 * 
	 * @param field
	 * @param value
	 */

	public void selectPriority_Status(String field, String value) {
		String xpath = getXPath(drpdwn_Priority_Status, field);
		getElement(By.xpath(xpath)).click();
		pause(1000);
		List<WebElement> options = getElements(priority_Options);
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals(value)) {
				options.get(i).click();
				break;
			}
		}
	}

	/**
	 * Author:Satya
	 * 
	 * @param text
	 */

	public void enterInputFields(String field, String text) {

		switch (field) {
		case "description":
			enterTextinTextbox(getElement(txtarea_DefectDesc), text);
			break;
		case "remarks":
			enterTextinTextbox(getElement(txtarea_Remarks), text);
			break;
		default:
			break;

		}

	}

	/**
	 * Author:Satya
	 */
	public void clickOnButton(String btnName) {

		String xpath = getXPath(button, btnName);
		getElement(By.xpath(xpath)).click();
		log.info(btnName + " button is clicked");

	}

	/**
	 * Author:Satya
	 * 
	 * @param locator
	 */

	public void isSaveSuccess() {
		isElementExist(getElement(label_XMLSamples));
		log.info("Save is successful");
	}

	/**
	 * Author:Satya
	 * 
	 * @param defectDesc
	 * @param priority
	 * @param status
	 * @param remarks
	 * @param currentStatus
	 */

	public void update_DefectDetails(String defectDesc, String priority, String updatedstatus, String remarks,
			String currentStatus) {
		String xpath = getXPath(status, currentStatus);
		if (isElementExist(getElement(By.xpath(xpath))))
			;
		List<WebElement> defects = getElements(By.xpath(xpath));
		for (int i = 0; i < defects.size(); i++) {
			defects.get(i).click();
			pause(1000);
			enterInputFields("description", defectDesc);
			enterInputFields("remarks", remarks);
			selectPriority_Status("priority", priority);
			selectPriority_Status("status", updatedstatus);
			clickOnButton("Save");
			pause(1000);
			isSaveSuccess();
			pause(1000);
			quitBrowsers();
			break;
		}

	}

}
