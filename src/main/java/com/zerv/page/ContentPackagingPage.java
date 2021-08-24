package com.zerv.page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class ContentPackagingPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(ContentPackagingPage.class);
	
	private By drpdwn_PackagingFormat = By.xpath("//mat-select[@formcontrolname='formatLookupValue']");
	private By PackagingFormat_Options = By.xpath("//div[@class='cdk-overlay-pane']//mat-option/span");
	
	private By input_ZipName=By.xpath("//input[@formcontrolname='zipNameRegular']");
	private By input_ZipNameEarlyAccess=By.xpath("//input[@formcontrolname='zipNameEarlyAccess']");
	
	//Zip file naming convention
	
	private By input_JournalTitle=By.xpath("//mat-form-field[@floatlabel='always']//input)[3]");
	
	private By input_Volume=By.xpath("//input[@formcontrolname='volume']");
	private By input_Issue=By.xpath("//input[@formcontrolname='issue']");
	private By editor_Notes=By.xpath("//ckeditor[@formcontrolname='notes']/textarea");
	
	//Content Zip File
	
	private By input_FolderStructure=By.xpath("//input[@formcontrolname='folderStructure']");
	
	private By input_ZipContent=By.xpath("//input[@formcontrolname='zipContent']");
	
	//AddFiles
	private By btn_AddFolder=By.xpath("//button/span/span[text()[normalize-space()='+ Add Folder']]");
	private By input_FolderType=By.xpath("//input[@formcontrolname='folderType']");
	private By input_FolderNaming=By.xpath("//input[@formcontrolname='folderNaming']");
	private By input_FolderContents=By.xpath("//input[@formcontrolname='folderContents']");
	
	private By input_FolderType_Article=By.xpath("//span[text()[normalize-space()='Article']]");
	private By input_FolderType_Issue=By.xpath("//span[text()[normalize-space()='Issue']]");
	private By btn_Clear=By.xpath("//div[@formarrayname='folders']//mat-icon[text()[normalize-space()='clear']]");
	//Content packaging
		
	private By btn_AddFileType=By.xpath("//button/span/span[text()[normalize-space()='+ Add File Type']]");
	private By input_fileNaming=By.xpath("//input[@formcontrolname='fileNaming']");
	private By list_fileNaming=By.xpath("//div[@role='listbox']/mat-option/span");
	private By input_nameConvention=By.xpath("//input[@formcontrolname='nameConvention']");
	private By btn_FilesClear=By.xpath("//div[@formarrayname='files']//mat-icon[text()[normalize-space()='clear']]");
	
	 //Attachments
	 
	 private By attachments=By.xpath("//span[text()[normalize-space()='Attachments (optional)']]");
	 
	 //Buttons
	 
	 private By btn_Pass=By.xpath("//button/span[text()[normalize-space()='Pass']]");
	 
	 //passed popup
	 private By textarea_Comment=By.xpath("//ckeditor/textarea");
	 private By btn_Complete=By.xpath("//button/span[text()[normalize-space()='Complete']]");
	 private By btn_Cancel=By.xpath("//button/span[text()[normalize-space()='Cancel']]");
	 private By textArea_Comment=By.xpath("//html/body/p");
	 
	 
		public ContentPackagingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

				
	/**
	 * Authot : Satya 
	 * Desc :to select packaging format value
	 * 
	 * @param option
	 */
	public void selectPackagingFormat(String option) {

		getElement(drpdwn_PackagingFormat).click();
		pause(2000);
		List<WebElement> options = getElements(PackagingFormat_Options);
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains(option)) {
				options.get(i).click();
				break;
			}

		}

	}

	/**
	 * Author : Satya 
	 * Desc : to fill ZipNameRegular field
	 * 
	 * @param text
	 */

	public void enterZipNameRegular(String text) {

		enterTextinTextbox(getElement(input_ZipName), text);

	}

	/**
	 * Author : Satya
	 * 
	 * @param text
	 */
	public void enterZipNameEarlyAccess(String text) {

		enterTextinTextbox(getElement(input_ZipNameEarlyAccess), text);

	}

	/**
	 * Author : Satya
	 * @param text
	 */
	public void enterVolume(String text) {

		enterTextinTextbox(getElement(input_Volume), text);

	}

	/**
	 * Author : Satya
	 * @param text
	 */
	public void enterIssue(String text) {

		enterTextinTextbox(getElement(input_Issue), text);

	}

	/**
	 * Author : Satya
	 * @param text
	 */
	public void enterNotes(String text) {

		
		switchToFrame("iframe");
		enterTextJS(textArea_Comment,text);
		switchToDefault();
		pause(1000);

	}
	
	/**
	 * Author:Satya
	 * @param text
	 */
     public String getNotes() {

		switchToFrame("iframe");
		String notes=getText(textArea_Comment);
		switchToDefault();
		pause(1000);
        return notes;
	}

	/**
	 * Author : Satya
	 * @param text
	 */
	public void enterFolderStructure(String text) {

		enterTextinTextbox(getElement(input_FolderStructure), text);

	}

	/**
	 * Author : Satya
	 * @param text
	 */
	public void enterZipContent(String text) {

		enterTextinTextbox(getElement(input_ZipContent), text);

	}

	/**
	 * Author : Satya
	 */
	public void clickOnAddFolderButton() {

		getElement(btn_AddFolder).click();

	}

	
	/**
	 * Author : Satya
	 */
	public void selectFolderTypeArticle() {

		getElement(input_FolderType).click();
		getElement(input_FolderType_Article).click();
		pause(1000);

	}

	/**
	 * Author : Satya
	 * 			 */
	public void selectFolderTypeIssue() {

		getElement(input_FolderType).click();
		getElement(input_FolderType_Issue).click();
		pause(1000);

	}

	/**
	 * Author : Satya
	 * @param text
	 */
	public void enterFolderNaming(String text) {

		enterTextinTextbox(getElement(input_FolderNaming), text);

	}

	/**
	 * Author : Satya
	 * @param text
	 */
	public void enterFoldeContents(String text) {

		enterTextinTextbox(getElement(input_FolderContents), text);

	}

	/**
	 * Author : Satya
	 */
	public void clickOnAddFileTypeButton() {

		getElement(btn_AddFileType).click();

	}

	/**
	 * Author : Satya
	 */

	public void selectFileType(String option) {

		getElement(input_fileNaming).click();
		pause(2000);
		List<WebElement> options = getElements(list_fileNaming);
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains(option)) {
				options.get(i).click();
				break;
			}

		}

	}
/**
 * Author : Satya
 * 
 * @param text
 */
	public void enternaming(String text) {

		enterTextinTextbox(getElement(input_nameConvention), text);

	}

	
	
	/**
	 * Author:Satya
	 * @param field
	 */
public void clickOnFolderClearIcon() {
		
		List<WebElement> options = getElements(btn_Clear);
		if(options.size()>1)
		{
		for (int i = 1; i <= options.size(); i++) {
			
				options.get(i).click();
				pause(1000);
				options = getElements(btn_Clear);
				
				
			}
		
		}
	}


public void clickOnFilesClearIcon() {
	
	List<WebElement> options = getElements(btn_FilesClear);
	if(options.size()>1)
	{
	for (int i = 1; i <= options.size(); i++) {
		
			options.get(i).click();
			pause(1000);
			options = getElements(btn_FilesClear);
			
		}
	}
}
}
	
		
	
	
	
	
	
	
	

