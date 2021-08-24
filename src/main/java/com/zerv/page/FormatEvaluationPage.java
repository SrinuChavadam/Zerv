package com.zerv.page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;



public class FormatEvaluationPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(FormatEvaluationPage.class);
	private By lbl_FormatAcquisition_Info = By.xpath("//span[text()[normalize-space()='Format Evaluation']]");
	private By title_FormatAcquisition_Info = By.xpath("//mat-panel-title/b[text()[normalize-space()='Format Evaluation']]");

	// FileAccess section

	private By FileAccessOptions = By.xpath("//div[text()[normalize-space()='%s']]/ancestor::mat-radio-button[@name='fileAccess']");
	private String select_FileAccess_Security_Timeliness = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[contains(@id,'mat-radio')]";

	// PDF Security
	
	private By PasswordProvided_Uncheck = By.xpath("//span[text()[normalize-space()='Password Provided']]/preceding-sibling::div/input[contains(@id,'mat-checkbox')]");
	private By input_Password = By.xpath("//input[@formcontrolname='password']");

	private By input_Notes = By.id("notes");

	private By processedAsPrint = By.xpath("//span[text()[normalize-space()='Previously Processed In Print']]/ancestor::mat-checkbox[@formcontrolname='processedAsPrint']");
	private By processedAsPrint_Check = By.xpath("//span[text()[normalize-space()='Previously Processed In Print']]/preceding-sibling::div/input[contains(@id,'mat-checkbox')]");

	// Attachments

	private By attachments = By.xpath("//span[text()[normalize-space()='Attachments (optional)']]");

	// Buttons
	
	private String button="//button/span[text()[normalize-space()='%s']]";

	
	
	// passed popup

	//private By input_Editor = By.xpath("//p[text()[normalize-space()='Evaluation result comment']]/following-sibling::ckeditor[@name='editor1']/textarea");
	private By textArea_Comment=By.xpath("//html//body/p");
	

	public FormatEvaluationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
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
	 * Author:Satya
	 */

	public void clickOnFormatEvaluationLink()

	{
		clickJS(getElement(lbl_FormatAcquisition_Info));
		// getElement(lbl_FormatAcquisition_Info).click();
	}

	private void getXpathandClick(String field)
	{
		String xpath = getXPath(select_FileAccess_Security_Timeliness, field);

		if (getElement(By.xpath(xpath)).isSelected()) {
			 log.info(field + " option is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));

		}
	}
	/**
	 * Author:Satya
	 * 
	 * @param fileaccess
	 */
	public void ClickOnFileAccessOption(String fileaccess) {

		getXpathandClick(fileaccess);

	}

	/**
	 * Author : Satya
	 * 
	 * @param fileaccess
	 */

	public void ClickOnPDFSecurity(String security) {

		getXpathandClick(security);
	}

	/**
	 * Author:Satya
	 */
	
	public void ClickOnPasswordProvided() {

		if (getElement(PasswordProvided_Uncheck).isSelected()) {
			// System.out.println("PasswordProvided checkbox option is already selected");
			log.info("PasswordProvided checkbox option is already selected");
		} else {
			clickJS(getElement(PasswordProvided_Uncheck));

		}
	}

	/**
	 * Author:Satya
	 * 
	 * @param text
	 */

	public void enterPassword(String text) {

		enterTextinTextbox(getElement(input_Password), text);

	}

	/**
	 * Author:Satya
	 * 
	 * @param text
	 */
	public void enterNoteForProcessingTeam(String text) {

		enterTextinTextbox(getElement(input_Notes), text);

	}

	/**
	 * Author:Satya
	 */
	public void ClickOnPreviouslyProcessedInPrint() {

		if (getElement(processedAsPrint_Check).isSelected()) {
			// System.out.println("processedAsPrint checkbox is already selected");
			log.info("processedAsPrint checkbox is already selected");
		} else {
			getElement(processedAsPrint).click();

		}
	}

	/**
	 * Author:Satya
	 * 
	 * @param security
	 */
	public void ClickOnTimeliness(String timeliness) {

		getXpathandClick(timeliness);
	}

	
	/**
	 * Author:Satya
	 * 
	 * @param text
	 */
	public void enterEvaluationResultComment(String text) {
		switchToFrame("iframe");
		enterTextJS(textArea_Comment,text);
		switchToDefault();
	}

	/**
	 * Author:Satya	
	 * @param field
	 */
	
	public void clickOnButton(String field) {
		String xpath = getXPath(button, field);
		switch (field) {
		case "Pass":
			getElement(By.xpath(xpath)).click();
			break;
		case "Reject":
    		getElement(By.xpath(xpath)).click();
			break;
		case "Complete":
			getElement(By.xpath(xpath)).click();
			break;
		case "Cancel":
    		getElement(By.xpath(xpath)).click();
			break;
		case "Yes,capture":
    		getElement(By.xpath(xpath)).click();
			break;
		default:
			break;

		}

	}
	
	
	

}
