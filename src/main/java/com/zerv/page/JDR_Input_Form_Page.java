package com.zerv.page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class JDR_Input_Form_Page extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(JDR_Input_Form_Page.class);
	private By link_JDRInputForm = By.xpath("//span[text()[normalize-space()='JDR Input Form']]");
	private String drpdwn_FieldOrder = "//mat-form-field[@formgroupname='%s']//mat-select";
	private By FieldOrder_Options = By.xpath("//mat-option/span");
	private By input_ProcessGroup = By.id("processGroup");

	// Reference characteristics
	private String chk_Reference = "//mat-checkbox[@formcontrolname='%s']//div";
	private String check_Reference_ProcessedFor = "//span[text()[normalize-space()='%s']]//preceding-sibling::div/input[contains(@id,'mat-checkbox')]";

	// Indent values section

	private By input_LeftIndent = By.xpath("//input[@formcontrolname='indentLeftValue']");
	private By input_RightIndent = By.xpath("//input[@formcontrolname='indentRightValue']");
	private By input_MaxLines = By.xpath("//div/input[@formcontrolname='maxLines']");

	// Processed for
	private String chk_ProcessedFor = "//span[text()[normalize-space()='%s']]/ancestor::mat-checkbox";
	private By btn_Pass = By.xpath("//button/span[text()[normalize-space()='Pass']]");
	public JDR_Input_Form_Page(WebDriver driver) {
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
	public void clickOnJDRInputLink()

	{
		clickJS(getElement(link_JDRInputForm));

	}

	/**
	 * Author:Satya
	 */

	public void selectFiledOrder(String field, String value) {
		String xpath = getXPath(drpdwn_FieldOrder, field);
		getElement(By.xpath(xpath)).click();
		pause(1000);
		List<WebElement> options = getElements(FieldOrder_Options);
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
	 * @param field
	 */
	public void selectReferenceCharacter(String field) {

		String xpath = getXPath(check_Reference_ProcessedFor, field);
		// System.out.println(xpath);
		if (getElement(By.xpath(xpath)).isSelected()) {
			// System.out.println(field+" is already selected");
			log.info(field + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));

		}
	}

	/**
	 * Author:Satya
	 * 
	 * @param field
	 */

	public void selectProcessedFor(String field) {

		String xpath = getXPath(check_Reference_ProcessedFor, field);

		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info(field + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));

		}
	}

	/**
	 * Author:Satya
	 * 
	 * @param text
	 */

	public void enterinputfields(String field, String text) {

		switch (field) {
		case "ProcessGroup":
			enterTextinTextbox(getElement(input_ProcessGroup), text);
			break;
		case "MaxLines":

			enterTextinTextbox(getElement(input_MaxLines), text);
			break;
		case "indentLeftValue":

			enterTextinTextbox(getElement(input_LeftIndent), text);
			break;
		case "indentRightValue":

			enterTextinTextbox(getElement(input_RightIndent), text);
			break;

		default:
			break;

		}

	}

	/**
	 * Author:Satya
	 */
	public void ClickOnPassButton() {

		getElement(btn_Pass).click();

	}
}
