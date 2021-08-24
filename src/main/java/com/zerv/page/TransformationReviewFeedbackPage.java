package com.zerv.page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class TransformationReviewFeedbackPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(TransformationReviewFeedbackPage.class);
	int i = 0;
	private By link_ReviewFeedback = By.xpath("//span[text()[normalize-space()='Transformation Review Feedback']]");

	private String buttons = "//button/span[text()[normalize-space()='%s']]";

	private By status = By
			.xpath("//mat-toolbar-row/div/div[5][contains(text(),'Open') or contains(text(),'InProgress')]");

	// private By
	// status=By.xpath("//mat-toolbar-row/div/div[5][contains(text(),'Open') or
	// contains(text(),'InProgress') or contains(text(),'Complete')]");

	// private By
	// status=By.xpath("//mat-toolbar-row[contains(@class,'defectsList')]");

	private By row_Defect = By.xpath("//mat-toolbar-row/div/div[5]");
	private By lbl_Complete = By.xpath("//mat-toolbar-row/div/div[5][contains(text(),'Complete')]");
	// private By drpdwn_Status=By.xpath("//mat-select[@formcontrolname='status']");
	private String drpdwn_Status = "(//mat-select[@formcontrolname='status'])[%d]";
	private By priority_Options = By.xpath("//mat-option/span");

	private By link_UploadFiles = By.xpath("//input[@value='Upload File(s)']");
	private By link_AttachedFiles = By.xpath("//a[@mattooltip='Click to download']");
	private By btn_Delete = By.xpath("(//mat-icon[text()[normalize-space()='cancel']])[1]");
	private By lbl_Note = By.xpath("//span[text()='Note: Required min. 10 xml sample files']");

	public TransformationReviewFeedbackPage(WebDriver driver) {
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
	public void clickOnReviewFeedbackLink()

	{
		clickJS(getElement(link_ReviewFeedback));
		pause(1000);

	}

	/**
	 * Author:Satya
	 * 
	 * @param btnName
	 */
	public void clickOnButton(String btnName) {
		String xpath = getXPath(buttons, btnName);
		switch (btnName) {
		case "Pass":
			getElement(By.xpath(xpath)).click();
			pause(1000);
			log.info(btnName + " button is clicked");
			break;
		case "Save":
			getElement(By.xpath(xpath)).click();
			log.info(btnName + " button is clicked");
			break;
		case "Back":
			getElement(By.xpath(xpath)).click();
			log.info(btnName + " button is clicked");
			break;
		case "Upload":
			getElement(link_UploadFiles).click();
			pause(1000);
			log.info("Upload link is clicked");
			break;
		case "Delete":
			getElement(btn_Delete).click();
			pause(1000);
			log.info("Delete icon is clicked");
		default:
			break;

		}

	}

	/**
	 * Author:Satya
	 * 
	 * @param value
	 */

	public void select_Status(String value, int row) {
		String xpath = getXPath(drpdwn_Status, row);
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
	 * @param updatedstatus
	 */

	public void update_DefectDetails(String updatedstatus) {
		List<WebElement> defects = getElements(status);
		try {
			// if(isElementExist(getElement(status)));
			// WebDriverWait wait = new WebDriverWait(driver, 30);
			System.out.println(defects.size());
			for (i = 0; i < defects.size(); i++) {
				/*
				 * String xpath="(//mat-toolbar-row/div/div[5])["+(i+1)+"]";
				 * System.out.println(getElement(By.xpath(xpath)).getText());
				 * if(getElement(By.xpath(xpath)).getText().contains("Open") ||
				 * getElement(By.xpath(xpath)).getText().contains("InProgress"))
				 */ defects.get(i).click();
				pause(1000);
				select_Status(updatedstatus, i + 1);
				pause(2000);
				clickOnButton("Save");
				pause(2000);

			}
		} catch (StaleElementReferenceException e) {
			List<WebElement> defects1 = getElements(status);
			defects1.get(0).click();
			pause(1000);
			select_Status(updatedstatus, i + 1);
			pause(2000);
			clickOnButton("Save");
			pause(2000);
		}

	}

	/**
	 * Author:Satya
	 */
	public void isWarningMessagePresent() {
		isElementExist(getElement(lbl_Note));
		log.info("Warning message is present.");
	}

}
