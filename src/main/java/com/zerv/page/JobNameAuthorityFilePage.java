package com.zerv.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class JobNameAuthorityFilePage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(JobNameAuthorityFilePage.class);
	private By drpdown_JobNames = By.xpath("//input[@placeholder='Job Name']");
	private String options = "//span[contains(.,'%s')]";
	private By Templatevalue = By.xpath("//b[contains(.,'Template')]");
	private By textArea_Comment = By.xpath("//html/body/p");

	public JobNameAuthorityFilePage(WebDriver driver) {
		super(driver);

	}
   /**
    * Author:Manimaran
    */
	public void clickDropdownJobName() {
		getElement(drpdown_JobNames).click();
	}

	public void clickOnConfirmButton() {
		String xpath = getXPath(options, "Confirm");
		getElement(By.xpath(xpath)).click();
	}

	public void clickOnUpdateButton() {
		String xpath = getXPath(options, "Update");
		getElement(By.xpath(xpath)).click();
	}

	public void selectDropDownValue(String value) {
		String xpath = getXPath(options, value);
		getElement(By.xpath(xpath)).click();
	}

	public void clickOnNextButton() {
		String xpath = getXPath(options, "arrow_forward Next");
		getElement(By.xpath(xpath)).click();
	}

	public String getTemplateValue() {
		String TemplateValue = getElement(Templatevalue).getText();
		String str = TemplateValue.substring(TemplateValue.indexOf(":") + 2, TemplateValue.length());
		return str;
	}

	public void enterTextInTemplate(String text) {
		enterTextJS(textArea_Comment, text);
	}

}
