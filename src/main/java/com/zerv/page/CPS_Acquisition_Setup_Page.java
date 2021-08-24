package com.zerv.page;



import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class CPS_Acquisition_Setup_Page extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(CPS_Acquisition_Setup_Page.class);
	private By link_CPSAcquisition = By.xpath("//span[text()[normalize-space()='CPS Input Form']]");
	private By txtarea_Path=By.xpath("//textarea[@formcontrolname='receiptUrl']");
	private By input_Username=By.xpath("//input[@formcontrolname='username']");
	private By input_password=By.xpath("//input[@formcontrolname='password']");
	//private By editor_comment=By.xpath("//ckeditor[@formcontrolname='additionalInfo']/textarea");
	private By textArea_Comment=By.xpath("//html/body/p");
	private By btn_Pass = By.xpath("//button/span[text()[normalize-space()='Pass']]");
	
	 
		public CPS_Acquisition_Setup_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
		/**
		 * Author:satya
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
		public void clickOnCPSAcquisitionLink()

		{
			clickJS(getElement(link_CPSAcquisition));
			
		}
		
				
		/**
		 * Author:Satya
		 * @param text
		 */
		
		public void enterInputfields(String field,String text) {
			
			switch (field) {
			case "username":
				enterTextinTextbox(getElement(input_Username), text);
				break;
			case "password":
				
				enterTextinTextbox(getElement(input_password), text);
				break;
            case "path":
				
				enterTextinTextbox(getElement(txtarea_Path), text);
				break;    
            case "comment":
            	switchToFrame("iframe");
            		enterTextJS(textArea_Comment,text);
            		switchToDefault();
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

	
	
	
		
	
		
	
	
	
	
	
	
	

