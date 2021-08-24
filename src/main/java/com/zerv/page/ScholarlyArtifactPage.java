package com.zerv.page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class ScholarlyArtifactPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(ScholarlyArtifactPage.class);
	private By lbl_ScholarlyArtifact_Info = By
			.xpath("//span[contains(.,'Scholarly Artifact Classification Descriptive text')]");
	private String Add = "(//span[@style='color:#5798E4'][contains(.,'+ Add')])[%d]";
	private String textBox = "(//*[@formcontrolname='providerDocTypeVal'])[%d]";
	private String ClarivateDocType_textarea = "//textarea[@selenium-id='%s']";
	private String ProvidedDocType_textarea = "//textarea[@selenium-id='%s']";
	private String btn_Check_Article = "(//*[@selenium-id='clarivateDocType-0']/ancestor::div[@fxlayout='row']//*[@role='img'][contains(text(),'check')])[%d]";
	private String btn_Check_Letter = "(//*[@selenium-id='clarivateDocType-2']/ancestor::div[@fxlayout='row']//*[@role='img'][contains(text(),'check')])[%d]";
	private String btn_Check_Editotial = "(//*[@selenium-id='clarivateDocType-3']/ancestor::div[@fxlayout='row']//*[@role='img'][contains(text(),'check')])[%d]";
	private String btn_Check_ClarivateDoc = "(//*[@selenium-id='providerDocType-32']/ancestor::div[@fxlayout='row']//*[@role='img'][contains(text(),'check')])[%d]";
	private String ClarivateDocumentType = "//*[@selenium-id='%s']/ancestor::div[@fxlayout='row']//*[@formcontrolname='providerDocTypeVal']";
	private By btn_AddClarivate = By.xpath("//label[contains(.,'Add Clarivate Document Type')]");

	private String btn_Check = "//*[@selenium-id='%s']/ancestor::div[@fxlayout='row']//*[@role='img'][contains(text(),'check')]";

	private By btn_Pass = By.xpath("//span[contains(.,'arrow_forward Pass')]");

	private By btn_complete = By.xpath("//span[contains(.,'Complete')]");

	public ScholarlyArtifactPage(WebDriver driver) {
		super(driver);

	}

	public void clickOnArticleValue() {
		String xpath1 = getXPath(ClarivateDocumentType, "clarivateDocType-0");
		List<WebElement> elements1 = getElements(By.xpath(xpath1));
		for (int i = 0; i < elements1.size(); i++) {
			elements1.get(i).click();
			ClickOnArticleCheckButton(i + 1);
		}
	}

	public void clickOnLetterValue() {
		String xpath1 = getXPath(ClarivateDocumentType, "clarivateDocType-2");
		List<WebElement> elements1 = getElements(By.xpath(xpath1));
		for (int i = 0; i < elements1.size(); i++) {
			elements1.get(i).click();
			ClickOnLetterCheckButton(i + 1);
		}
	}

	public void clickOnEditorialMaterial() {
		String xpath1 = getXPath(ClarivateDocumentType, "clarivateDocType-3");
		List<WebElement> elements1 = getElements(By.xpath(xpath1));
		for (int i = 0; i < elements1.size(); i++) {
			elements1.get(i).click();
			ClickOnEditorialMatCheckBtn(i + 1);
		}
	}

	public void clickOnScholarlyArtifactLink()

	{

		getElement(lbl_ScholarlyArtifact_Info).click();

	}

	public void ClickOnPass() {
		getElement(btn_Pass).click();

	}

	public void clickOnComplete() {
		getElement(btn_complete).click();

	}

	public void ClickAddButton(int value) {
		String xpath = getXPath(Add, value);
		getElement(By.xpath(xpath)).click();

	}

	public void enterProvidedValue(String str, int number) {

		String xpath = getXPath(textBox, number);
		enterTextinTextbox(getElement(By.xpath(xpath)), str);
	}

	public void ClickOnProvidedValue(int number) {

		String xpath = getXPath(textBox, number);
		getElement(By.xpath(xpath)).click();
	}

	public String ProvidedValue(int number) {

		String xpath = getXPath(textBox, number);
		String str = getElement(By.xpath(xpath)).getText();
		return str;
	}

	public void ClickOnArticleCheckButton(int value) {
		String xpath = getXPath(btn_Check_Article, value);
		getElement(By.xpath(xpath)).click();

	}

	public void ClickOnLetterCheckButton(int value) {
		String xpath = getXPath(btn_Check_Letter, value);
		getElement(By.xpath(xpath)).click();

	}

	public void ClickOnEditorialMatCheckBtn(int value) {
		String xpath = getXPath(btn_Check_Editotial, value);
		getElement(By.xpath(xpath)).click();

	}

	public void ClickOnClarivateCheckBtn(int value) {
		String xpath = getXPath(btn_Check_ClarivateDoc, value);
		getElement(By.xpath(xpath)).click();
	}

	public void ClickOnCheckBtn(String value) {
		String xpath = getXPath(btn_Check, value);
		getElement(By.xpath(xpath)).click();
	}

	public void addClarivateDocTypeBtn() {

		getElement(btn_AddClarivate).click();

	}

	public void enterClarivateDocTypeArea1(String value) {
		String xpath = getXPath(ClarivateDocType_textarea, "clarivateDocType-32");
		enterTextinTextbox(getElement(By.xpath(xpath)), value);
		ClickOnClarivateCheckBtn(1);
	}

	public void enterProvidedDocumentTypeArea1(String value) {
		String xpath = getXPath(ProvidedDocType_textarea, "providerDocType-32");
		enterTextinTextbox(getElement(By.xpath(xpath)), value);
		getElement(By.xpath(xpath)).click();
		ClickOnClarivateCheckBtn(2);

	}

	public void enterReprint(String value) {
		String xpath = getXPath(ProvidedDocType_textarea, "providerDocType-13");
		enterTextinTextbox(getElement(By.xpath(xpath)), value);
		ClickOnCheckBtn("providerDocType-13");

	}

}
