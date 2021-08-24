package com.zerv.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class TitlingAbbreviationsPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(TitlingAbbreviationsPage.class);
	private By lbl_Title_Info = By.xpath("(//div[contains(.,'Title Information Descriptive text')])[6]");
	private By btn_Add_Title = By.xpath("//a[contains(.,'+Add title')]");
	private String drop_down = "(//mat-select[starts-with(@id,'mat-select-')])[%d]";
	private String drop_down_value = "//span[contains(text(),'%s')]";
	private String PubTitleDropDownValue = "//span[@class='mat-option-text'][contains(.,'%s')]";
	private String textBox = "(//input[@formcontrolname='title'])[%d]";
	private By Publication_History = By.xpath("//div[contains(text(),' Publication Title History ')]");
	private By Title_Error = By
			.xpath("//mat-error[starts-with(@id,'mat-error-')][contains(text(),'Title type already exist')]");
	private By Full_title_Title_twenty = By.xpath(
			"//mat-error[starts-with(@id,'mat-error-')][contains(text(),' Title full and Title twenty are required')]");
	private By TitleEleven = By.xpath(
			"//mat-error[starts-with(@id,'mat-error-')][contains(text(),'Max length for selected title type is 11 characters')]");
	private By TitleTwentyError = By
			.xpath("//mat-error[starts-with(@id,'mat-error-')][contains(text(),'All the characters must be in Caps')]");
	private By TitleTwentyCharError = By
			.xpath("//mat-error[starts-with(@id,'mat-error-')][contains(text(),'Max length for selected')]");
	private By TitleFullError = By
			.xpath("//mat-error[starts-with(@id,'mat-error-')][contains(text(),'Title Case with initial capitals')]");
	private String TitleHistory = "(//*[@role='gridcell'])[%d]";
	private By Btn_Pass = By.xpath("//span[contains(.,'Passarrow_forward')]");
	private By TitleTypeDropDown = By.xpath("//div[@class='mat-form-field-infix'][contains(.,'All')]");
	private By FullTitle = By.xpath("//div[contains(text(),'Full Title')]//following-sibling::div");

	public TitlingAbbreviationsPage(WebDriver driver) {
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

	public void clickOnlbl_Title_InfoLink()

	{
		getElement(lbl_Title_Info).click();

	}

	public void clickOnAddTitle()

	{
		getElement(btn_Add_Title).click();

	}

	public void ClickOnDropDown(int value) {
		String xpath = getXPath(drop_down, value);
		getElement(By.xpath(xpath)).click();

	}

	public void enterDropDownValue(String value, int number) {
		String xpath = getXPath(textBox, number);
		enterTextinTextbox(getElement(By.xpath(xpath)), value);

	}

	public void SelectDropDownValue(String value) {
		String xpath = getXPath(drop_down_value, value);
		getElement(By.xpath(xpath)).click();

	}

	public String getPublicationHistory()

	{
		WebElement ele = getElement(Publication_History);
		String PublicationHistory = ele.getText();
		return PublicationHistory;

	}

	public void ClickOnPublicationHistory()

	{
		getElement(Publication_History).click();

	}

	public void ClickOnPassButton()

	{
		getElement(Btn_Pass).click();

	}

	public String getTitleError()

	{
		WebElement ele = getElement(Title_Error);
		String Title_Error = ele.getText();
		return Title_Error;

	}

	public String getTitleFullAndTwentyError()

	{
		WebElement ele = getElement(Full_title_Title_twenty);
		String FullAndTwentyError = ele.getText();
		return FullAndTwentyError;

	}

	public String getTitleTwentyCharError()

	{
		WebElement ele = getElement(TitleTwentyCharError);
		String FullAndTwentyError = ele.getText();
		return FullAndTwentyError;

	}

	public String getTitleElevenError()

	{
		WebElement ele = getElement(TitleEleven);
		String TitleElevenError = ele.getText();
		return TitleElevenError;

	}

	public String getTitleTwentyError()

	{
		WebElement ele = getElement(TitleTwentyError);
		String TitleTwentyError = ele.getText();
		return TitleTwentyError;

	}

	public String getTitleFullError()

	{
		WebElement ele = getElement(TitleFullError);
		String TitleFullError = ele.getText();
		return TitleFullError;

	}

	public String getFull_title_Title_twentyError()

	{
		WebElement ele = getElement(Full_title_Title_twenty);
		String Full_title_Title_twentyError = ele.getText();
		return Full_title_Title_twentyError;

	}

	public String getTitleHistoryValue(int value)

	{
		String xpath = getXPath(TitleHistory, value);
		WebElement ele = getElement(By.xpath(xpath));
		String str = ele.getText();
		return str;

	}

	public String getBeforeChangeValue(int value) {
		return getTitleHistoryValue(value);
	}

	public String getAfterChangeValue(int value) {
		return getTitleHistoryValue(value);
	}

	public String getTileTypeValue(int value) {
		return getTitleHistoryValue(value);
	}

	public String getDateChangedValue(int value) {
		return getTitleHistoryValue(value);
	}

	public String getChangedByValue(int value) {
		return getTitleHistoryValue(value);
	}

	public void ClickOnTitleTypeDropDown()

	{
		getElement(TitleTypeDropDown).click();

	}

	public void SelectPubDropDownValue(String value) {
		String xpath = getXPath(PubTitleDropDownValue, value);
		getElement(By.xpath(xpath)).click();

	}

	public String getFullTitleValue()

	{
		WebElement ele = getElement(FullTitle);
		String FullTitle = ele.getText();
		return FullTitle;

	}

}
