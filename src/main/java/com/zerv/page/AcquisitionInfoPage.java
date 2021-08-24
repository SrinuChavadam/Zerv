package com.zerv.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class AcquisitionInfoPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(AcquisitionInfoPage.class);
	private By Acquisition_Info = By.xpath("//mat-card-title/span[text()[normalize-space()='Acquisition Info']]");
	private String PublisherCategory = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[@type='radio']";
	private String ReceiptMethod = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[contains(@id,'receiptMethod')]";
	private String TransmissionType = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[contains(@id,'transmissionType')]";
	private String FTPReceiptMethod = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[contains(@id,'mat-radio')]";
	private String BrowserType = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[contains(@id,'mat-radio')]";
	private String AcquisitionTrigger = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[contains(@id,'acquisitionTrigger')]";
	private String ContentSize = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[contains(@id,'contentSize')]";
	private By Priority_Select = By.xpath("//mat-select[starts-with(@selenium-id,'filePriority')]");
	private String PriorityValue = "//span[contains(.,'%s')]";
	private String SummaryInfo = "//span[text()[normalize-space()='%s']]/preceding-sibling::div/input[@type='checkbox']";
	private By Summary_Info = By.xpath("//mat-panel-title[contains(.,'Summary Information')]");
	private By Attachment_Info = By.xpath("//mat-panel-title[contains(.,'Attachments (optional)')]");
	private By Schedule_Info = By.xpath("//mat-panel-title[contains(.,'Schedule Information (optional)')]");
	private By Repeat_Everyday = By.xpath("//mat-select[contains(@formcontrolname,'repeatEvery')]");
	private String RepeatEveryday = "//span[contains(.,'%s')]";
	private By Email_Receipt_Method = By.xpath("(//input[@aria-required='false'])[5]");
	private By Web_Receipt_Method_SleepTime = By.xpath("(//input[contains(@aria-invalid,'false')])[5]");
	private By Trust_Certificates = By.xpath("(//div[@class='mat-checkbox-inner-container'])[1]");
	private By schemaOne = By.xpath("//input[@id='schema1']");
	private By schemaTwo = By.xpath("//input[@id='schema2']");
	private By schemaThree = By.xpath("//input[@id='schema3']");
	private By Batch_Processing = By.xpath("(//div[@class='mat-checkbox-inner-container'])[1]");
	private By FTP_Path = By.xpath("(//input[@aria-required='false'])[5]");
	private By User_Id = By.xpath("//input[@id='userId']");
	private By Password = By.xpath("//input[@id='password']");
	private By ReceiptUrl = By.xpath("//input[@id='receiptUrl']");
	private By Upload_File = By.xpath("//input[contains(@value,'Upload File(s)')]");
	private By NextButton_Acquisiation = By.xpath("//button/span[text()[normalize-space()='Next']]");
/**
 * Author:Manimaran
 * @param option
 */
	// PublisherCategory
	public void PublisherCategoryValue(String option) {

		String xpath = getXPath(PublisherCategory, option);
		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info(" Publisher Category Value " + option + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));
			log.info(" Publisher Category Value " + option + " is selected");
		}

	}
/**
 * Author:Manimaran
 * @param option
 */
	// ReceiptMethod
	public void ReceiptMethodValue(String option) {

		String xpath = getXPath(ReceiptMethod, option);
		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info(" Receipt Method Value " + option + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));
			log.info(" Receipt Method Value " + option + " is selected");
		}

	}
	/**
	 * Author:Manimaran
	 * @param option
	 */

	// TransmissionType
	public void TransmissionTypeValue(String option) {

		String xpath = getXPath(TransmissionType, option);
		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info(" Transmission Type Value " + option + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));
			log.info(" Transmission Type Value " + option + " is selected");
		}

	}
	/**
	 * Author:Manimaran
	 * @param option
	 */

	// FTPReceiptMethod
	public void FTPReceiptMethodValue(String option) {

		String xpath = getXPath(FTPReceiptMethod, option);
		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info(" FTP Receipt Method Value " + option + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));
			log.info(" FTP Receipt Method Value " + option + " is selected");
		}

	}
/**
 * Author:Manimaran
 * @param option
 */
	// Browser Type
	public void browserTypeValue(String option) {

		String xpath = getXPath(BrowserType, option);
		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info(" browser Type Value " + option + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));
			log.info(" browser Type Value " + option + " is selected");
		}

	}
    /**
     * Author:Manimaran
     * @param option
     */
	// AcquisitionTrigger

	public void acquisitionTriggerValue(String option) {

		String xpath = getXPath(AcquisitionTrigger, option);

		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info(" Acquisition Trigger Value " + option + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));
			log.info(" Acquisition Trigger Value " + option + " is selected");
		}

	}

	/**
	 * Author:Manimaran
	 * @param option
	 */
	// Content Size

	public void contentSizeValue(String option) {

		String xpath = getXPath(ContentSize, option);

		if (getElement(By.xpath(xpath)).isSelected()) {
			log.info(" Content Size Value " + option + " is already selected");
		} else {
			clickJS(getElement(By.xpath(xpath)));
			log.info(" Content Size Value " + option + " is selected");
		}

	}

	
	/**
	 * Author:Manimaran
	 * @param option
	 */
	// priorityValue
	public void priorityValue(String option) {

		String xpath = getXPath(PriorityValue, option);
		String xpath1 = getXPath(PriorityValue, "Normal-Small Files");
		clickJS(getElement(By.xpath(xpath1)));
		clickOnPriority();
		clickJS(getElement(By.xpath(xpath)));
		log.info(" priority Value " + option + " is selected");

	}
    /**
     * Author:Manimaran
     * @param option
     */
	// SummaryInfo

	public void summaryInfoValue(String option) {
		String[] str = option.split(",");
		for (int i = 0; i < str.length; i++) {
			String xpath = getXPath(SummaryInfo, str[i]);

			if (getElement(By.xpath(xpath)).isSelected()) {
				log.info(" Summary Info Value " + str[i] + " is already selected");
			} else {
				clickJS(getElement(By.xpath(xpath)));
				log.info(" Summary Info Value " + str[i] + " is selected");
			}
		}
	}

	/**
	 * Author:Manimaran
	 * @param option
	 */
	// RepeatEveryday

	public void repeatEverydayValue(String option) {

		String xpath = getXPath(RepeatEveryday, option);
		String xpath1 = getXPath(RepeatEveryday, "Yearly");
		clickJS(getElement(By.xpath(xpath1)));
		ClickOnRepeat_Everyday();
		clickJS(getElement(By.xpath(xpath)));
		log.info(" Repeat Everyday Value " + option + " is selected");

	}

	/**
	 * Author:Manimaran
	 * @param driver
	 */
	public AcquisitionInfoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Author:Manimaran
	 */

	public void clickOnAcquisitionTab()

	{
		getElement(Acquisition_Info).click();
		log.info(" Acquisition info Tab is selected");
	}

	
	/**
	 * Author:Manimaran
	 * @param text
	 */
	public void enterSchemaOne(String text) {
		enterTextinTextbox(getElement(schemaOne), text);
		log.info(" Text " + text + " is entered in SchemaOne");
	}
	
	/**
	 * Author:Manimaran
	 * @param text
	 */

	public void enterSchemaTwo(String text) {
		enterTextinTextbox(getElement(schemaTwo), text);
		log.info(" Text " + text + " is entered in SchemaTwo");
	}
	
	/**
	 * Author:Manimaran
	 * @param text
	 */

	public void enterSchemaThree(String text) {
		enterTextinTextbox(getElement(schemaThree), text);
		log.info(" Text " + text + " is entered in SchemaThree");
	}

	/**
	 * Author:Manimaran
	 */
	public void clickOnBatchProcessing() {
		if (getElement(Batch_Processing).isSelected()) {
			log.info("Batch Processing radio button is already selected");
		} else {
			getElement(Batch_Processing).click();
			log.info("Batch Processing radio button is selected");

		}
	}

	
	/**
	 * Author:Manimaran
	 * @param text
	 */
	public void enterOnFTPPath(String text) {

		enterTextinTextbox(getElement(FTP_Path), text);
		log.info("FTP path " + text + " is entered in FTP path input field");
	}

	
	/**
	 * Author:Manimaran
	 * @param text
	 */
	public void enterOnWebURL(String text) {

		enterTextinTextbox(getElement(ReceiptUrl), text);
		log.info("Web " + text + " is entered in WEB input field");
	}

	/**
	 * Author:Manimaran
	 * @param text
	 */
	public void enterOnUserId(String text) {

		enterTextinTextbox(getElement(User_Id), text);
		log.info("UserID " + text + " is entered in UserID input field");

	}

	/**
	 * Author:Manimaran
	 * @param text
	 */
	public void enterOnPassword(String text) {

		enterTextinTextbox(getElement(Password), text);
		log.info("Password " + text + " is entered in Password input field");

	}
/**
 * Author:Manimaran
 * @param text
 */
	public void enterOnEmailReceiptMethod(String text) {

		enterTextinTextbox(getElement(Email_Receipt_Method), text);
		log.info("Email " + text + " is entered in Email input field");

	}

	public void enterOnWebSleepTime(String text) {

		enterTextinTextbox(getElement(Web_Receipt_Method_SleepTime), text);

	}

	public void clickOnTrust_Certificates() {
		if (getElement(Trust_Certificates).isSelected()) {
			log.info("Trust_Certificates option is already selected");
		} else {
			clickJS(getElement(Trust_Certificates));
			log.info("Trust_Certificates option is selected");

		}
	}

	public void clickOnSummaryInfo() {

		clickJS(getElement(Summary_Info));
		log.info("Summary_Info option is selected");

	}

	public void clickOnAttachment() {

		clickJS(getElement(Attachment_Info));
		log.info("Attachment_Info option is selected");

	}

	public void clickOnPriority() {

		clickJS(getElement(Priority_Select));
		log.info("Priority_Select option is selected");

	}

	public void ClickOnNextButton_Acquisiation() {
		getElement(NextButton_Acquisiation).click();
		log.info("Clicked on NextButton");

	}

	public void ClickOnSchedule_Info() {
		getElement(Schedule_Info).click();
		log.info("Schedule_Info option is selected");
	}

	public void ClickOnRepeat_Everyday() {
		getElement(Repeat_Everyday).click();
		log.info("Repeat_Everyday option is selected");
	}

	public void ClickOnUploadFile() {
		clickJS(getElement(Upload_File));
		log.info("Upload File button is selected");
	}

}
