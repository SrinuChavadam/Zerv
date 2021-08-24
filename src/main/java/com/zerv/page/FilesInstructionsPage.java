package com.zerv.page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class FilesInstructionsPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(FilesInstructionsPage.class);
	
	 //Delivered Folder
	private String TOC_Basepath="//mat-panel-title[text()[normalize-space()='Delivered Folder : TOC']]/ancestor::mat-expansion-panel";
	private By btn_DF_AddFileType=By.xpath(TOC_Basepath+"//button/span[text()[normalize-space()='+Add File Type']]");
	private By rbtn_DF_FileType=By.xpath(TOC_Basepath+"//input[@formcontrolname='fileName']");
	private By rbtn_DF_FileTypeOptions=By.xpath("//div[@role='listbox']/mat-option/span");
	private By rbtn_DF_Check=By.xpath(TOC_Basepath+"//mat-icon[text()[normalize-space()='check']]");
	private By rbtn_DF_Close=By.xpath(TOC_Basepath+"//mat-icon[text()[normalize-space()='close']]");
	private By rbtn_DF_Transform=By.xpath(TOC_Basepath+"//mat-radio-button[@value='transform']");
	 private By rbtn_DF_Ignore=By.xpath(TOC_Basepath+"//mat-radio-button[@value='ignore']");
	 private By icon_DF_Clear=By.xpath(TOC_Basepath+"//mat-icon[text()[normalize-space()='clear']]");
     
	
    //Delivered Folder : Article/Source
     
	 private String Source_Basepath="//mat-panel-title[text()[normalize-space()='Delivered Folder : Article/Source']]/ancestor::mat-expansion-panel";
	 private By btn_Source_AddFileType=By.xpath(Source_Basepath+"//button/span[text()[normalize-space()='+Add File Type']]");
	 private By rbtn_FileType_Source=By.xpath(Source_Basepath+"//input[@formcontrolname='fileName']");
	 private By rbtn_FileTypeOptions_Source=By.xpath("//div[@role='listbox']/mat-option/span");
	 private By rbtn_Check_Source=By.xpath(Source_Basepath+"//mat-icon[text()[normalize-space()='check']]");
	 private By rbtn_Close_Source=By.xpath(Source_Basepath+"//mat-icon[text()[normalize-space()='close']]");
	 private By rbtn_Transform_Source=By.xpath(Source_Basepath+"//mat-radio-button[@value='transform']");
	 private By rbtn_Ignore_Source=By.xpath(Source_Basepath+"//mat-radio-button[@value='ignore']");
	 private By icon_Clear_Source=By.xpath(Source_Basepath+"//mat-icon[text()[normalize-space()='clear']]");
    
	 //Delivered Folder : Article/Supplement
	 private String Supplement_Basepath="//mat-panel-title[text()[normalize-space()='Delivered Folder : Article/Supplement']]/ancestor::mat-expansion-panel";
	 private By btn_Supplement_AddFileType=By.xpath(Supplement_Basepath+"//button/span[text()[normalize-space()='+Add File Type']]");
	 private By rbtn_FileType_Supplement=By.xpath(Supplement_Basepath+"//input[@formcontrolname='fileName']");
	 private By rbtn_FileTypeOptions_Supplement=By.xpath("//div[@role='listbox']/mat-option/span");
	 private By rbtn_Check_Supplement=By.xpath(Supplement_Basepath+"//mat-icon[text()[normalize-space()='check']]");
	 private By rbtn_Close_Supplement=By.xpath(Supplement_Basepath+"//mat-icon[text()[normalize-space()='close']]");
	 private By rbtn_Transform_Supplement=By.xpath(Supplement_Basepath+"//mat-radio-button[@value='transform']");
	 private By rbtn_Ignore_Supplement=By.xpath(Supplement_Basepath+"//mat-radio-button[@value='ignore']");
	 private By icon_Clear_Supplement=By.xpath(Supplement_Basepath+"//mat-icon[text()[normalize-space()='clear']]");
    
	 //Delivered Folder : Others
	 private String Others_Basepath="//mat-panel-title[text()[normalize-space()='Delivered Folder : Others']]/ancestor::mat-expansion-panel";
		
	 private By btn_Others_AddFileType=By.xpath(Others_Basepath+"//button/span[text()[normalize-space()='+Add File Type']]");
	 private By rbtn_FileType_Others=By.xpath(Others_Basepath+"//input[@formcontrolname='fileName']");
	 private By rbtn_FileTypeOptions_Others=By.xpath("//div[@role='listbox']/mat-option/span");
	 private By rbtn_Check_Others=By.xpath(Others_Basepath+"//mat-icon[text()[normalize-space()='check']]");
	 private By rbtn_Close_Others=By.xpath(Others_Basepath+"//mat-icon[text()[normalize-space()='close']]");
	 private By rbtn_Transform_Others=By.xpath(Others_Basepath+"//mat-radio-button[@value='transform']");
	 private By rbtn_Ignore_Others=By.xpath(Others_Basepath+"//mat-radio-button[@value='ignore']");
	 private By icon_Clear_Others=By.xpath(Others_Basepath+"//mat-icon[text()[normalize-space()='clear']]");
    
	 //Delivered Folder : Images
	 private String Images_Basepath="//mat-panel-title[text()[normalize-space()='Delivered Folder : Images']]/ancestor::mat-expansion-panel";
		
	 private By btn_Images_AddFileType=By.xpath(Images_Basepath+"//button/span[text()[normalize-space()='+Add File Type']]");
	 private By rbtn_FileType_Images=By.xpath(Images_Basepath+"//input[@formcontrolname='fileName']");
	 private By rbtn_FileTypeOptions_Images=By.xpath("//div[@role='listbox']/mat-option/span");
	 private By rbtn_Check_Images=By.xpath(Images_Basepath+"//mat-icon[text()[normalize-space()='check']]");
	 private By rbtn_Close_Images=By.xpath(Images_Basepath+"//mat-icon[text()[normalize-space()='close']]");
	 private By rbtn_Transform_Images=By.xpath(Images_Basepath+"//mat-radio-button[@value='transform']");
	 private By rbtn_Ignore_Images=By.xpath(Images_Basepath+"//mat-radio-button[@value='ignore']");
	 private By icon_Clear_Images=By.xpath(Images_Basepath+"//mat-icon[text()[normalize-space()='clear']]");
    
	 //Supplemental files
	 
	 private By chk_FilesIncluded=By.xpath("//mat-checkbox[@formcontrolname='filesIncluded']");
	 private By chk_SupplementalFiles=By.xpath("//mat-checkbox[@formcontrolname='filesIncluded']//input[@type='checkbox']");
	 private By textArea_FileTypes=By.xpath("//textarea[@formcontrolname='fileTypes']");
	 private By btn_Pass=By.xpath("//button/span[text()[normalize-space()='Pass']]");
	
		public FilesInstructionsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

		
	/**
	 * Author:Satya
	 */

	public void ClickOnClearIcon_DF() {

		getElement(icon_DF_Clear).click();

	}

	/**
	 * Author:Satya
	 */

	public void clickOn_AddFileType_DF() {

		getElement(btn_DF_AddFileType).click();

	}

	/**
	 * 
	 * @param option
	 */
	public void AddFileType_DF(String option) {

		getElement(rbtn_DF_FileType).click();
		pause(2000);
		List<WebElement> options = getElements(rbtn_DF_FileTypeOptions);
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains(option)) {
				options.get(i).click();
				break;
			}

		}

	}

	/**
	 * Author:Satya
	 */
	public void ClickOnCheckIcon_DF() {

		getElement(rbtn_DF_Check).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnClose_DF() {

		getElement(rbtn_DF_Close).click();
	}
		
		
	/**
	 * Author:Satya
	 */
	public void ClickOnTransform_DF() {

		getElement(rbtn_DF_Transform).click();

	}

	/**
	 * Author:Satya
	 */
	public void ClickOnIgnore_DF() {

		getElement(rbtn_DF_Ignore).click();

	}
		//*******************************************************Article/Source
		
		/**
		 * Author:Satya
		 */
		
		public void ClickOnClearIcon_Source() {
			
            getElement(icon_Clear_Source).click();
						
		}
		/**
		 *  Author:Satya
		 */
		
		public void clickOn_AddFileType_Source() {
			
			getElement(btn_Source_AddFileType).click();
						
		}
		/**
		 *  Author:Satya
		 * @param option
		 */
		public void AddFileType_Source(String option) {
			

			getElement(rbtn_FileType_Source).click();
			pause(2000);
			List<WebElement> options=getElements(rbtn_FileTypeOptions_Source);
			for (int i=0; i<options.size();i++){
				if(options.get(i).getText().contains(option))
				{
					options.get(i).click();
					break;
				}
			      
			    }
			
			}
						
		
	/**
	 * Author:Satya
	 */
	public void ClickOnCheckIcon_Source() {

		getElement(rbtn_Check_Source).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnClose_Source() {

		getElement(rbtn_Close_Source).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnTransform_Source() {

		getElement(rbtn_Transform_Source).click();

	}

	/**
	 * Author:Satya
	 */
	public void ClickOnIgnore_Source() {

		getElement(rbtn_Ignore_Source).click();
	}
		
		
		//************************************************************************Supplements
		/**
		 *  Author:Satya
		 */
		
		public void ClickOnClearIcon_Supplement() {
			
            getElement(icon_Clear_Supplement).click();
						
		}
		/**
		 *  Author:Satya
		 */
		
		public void clickOn_AddFileType_Supplement() {
			

			getElement(btn_Supplement_AddFileType).click();
			}
			
		/**
		 *  Author:Satya
		 * @param option
		 */
		public void AddFileType_Supplement(String option) {
			
			getElement(rbtn_FileType_Supplement).click();
			pause(2000);
			List<WebElement> options=getElements(rbtn_FileTypeOptions_Supplement);
			for (int i=0; i<options.size();i++){
				if(options.get(i).getText().contains(option))
				{
					options.get(i).click();
					break;
				}
			      
			    }
			
			}
						
		
	/**
	 * Author:Satya
	 */
	public void ClickOnCheckIcon_Supplement() {

		getElement(rbtn_Check_Supplement).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnClose_Supplement() {

		getElement(rbtn_Close_Supplement).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnTransform_Supplement() {

		getElement(rbtn_Transform_Supplement).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnIgnore_Supplement() {

		getElement(rbtn_Ignore_Supplement).click();
	}

		//*********************************************************Others
		
		/**
		 * Author:Satya
		 */
		
		public void ClickOnClearIcon_Others() {
			
           	getElement(icon_Clear_Others).click();
			}
			
		/**
		 * Author:Satya
		 */
		
		public void clickOn_AddFileType_Others() {
			
			getElement(btn_Others_AddFileType).click();
						
		}
		/**
		 * Author:Satya
		 * @param option
		 */
		public void AddFileType_Others(String option) {
			
			getElement(rbtn_FileType_Others).click();
			pause(2000);
			List<WebElement> options=getElements(rbtn_FileTypeOptions_Others);
			for (int i=0; i<options.size();i++){
				if(options.get(i).getText().contains(option))
				{
					options.get(i).click();
					break;
				}
			      
			    }
			
			}
			
	/**
	 * Author:Satya
	 */
	public void ClickOnCheckIcon_Others() {

		getElement(rbtn_Check_Others).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnClose_Others() {

		getElement(rbtn_Close_Others).click();

	}

	/**
	 * Author:Satya
	 */
	public void ClickOnTransform_Others() {

		getElement(rbtn_Transform_Others).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnIgnore_Others() {

		getElement(rbtn_Ignore_Others).click();
	}

	// **********************************************************Images
	/**
	 * Author:Satya
	 */

	public void ClickOnClearIcon_Images() {

		getElement(icon_Clear_Images).click();
	}

	/**
	 * Author:Satya
	 */

	public void clickOn_AddFileType_Images() {

		getElement(btn_Images_AddFileType).click();
	}

	/**
	 * Author:Satya
	 * 
	 * @param option
	 */
	public void AddFileType_Images(String option) {

		getElement(rbtn_FileType_Images).click();
		pause(2000);
		List<WebElement> options = getElements(rbtn_FileTypeOptions_Images);
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains(option)) {
				options.get(i).click();
				break;
			}

		}

	}

	/**
	 * Author:Satya
	 */
	public void ClickOnCheckIcon_Images() {

		getElement(rbtn_Check_Images).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnClose_Images() {

		getElement(rbtn_Close_Images).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnTransform_Images() {

		getElement(rbtn_Transform_Images).click();
	}

	/**
	 * Author:Satya
	 */
	public void ClickOnIgnore_Images() {

		getElement(rbtn_Ignore_Images).click();
	}

	// ***************************************************

	/**
	 * Author:Satya
	 */

	public void ClickOnSupplementalFiles() {
		if(getElement(chk_SupplementalFiles).isSelected())
		{
			log.info("SupplementalFiles is already checked");
		}
		else
		{
		getElement(chk_FilesIncluded).click();
		}
	}

	/**
	 * Author:Satya
	 * 
	 * @param text
	 */

	public void enterFileTypes(String text) {
		enterTextinTextbox(getElement(textArea_FileTypes), text);
	}

	/**
	 * Author:Satya
	 */

	public void ClickOnPassButton() {

		getElement(btn_Pass).click();
	}
}
	

