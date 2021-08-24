package com.zerv.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class PDFXMLElementInventoryPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(PDFXMLElementInventoryPage.class);

	private String options = "//*[@selenium-id='%s']//label/div/input[@type='checkbox']";
	private By btn_done = By.xpath("//mat-icon[contains(.,'arrow_forward')]");

	public PDFXMLElementInventoryPage(WebDriver driver) {
		super(driver);

	}
    /**
     * Author:Manimaran
     * @param option
     */
	public void selectPDFXMLElementInventoryValue(String option) {

		String xpath = getXPath(options, option);

		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info("PDF/XML Element Inventory  value for " + option + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));
			log.info("PDF/XML Element Inventory  value for " + option + " has been selected");
		}

	}

	public void clickOnDoneBtn() {
		getElement(btn_done).click();
	}

}
