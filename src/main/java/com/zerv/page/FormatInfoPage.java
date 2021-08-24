package com.zerv.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class FormatInfoPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(FormatInfoPage.class);
	private By Format_Info = By.xpath("(//span[text()[normalize-space()='Format Information']])[1]");
	//private By Format_Info = By.xpath("//*[@id='cdk-step-label-0-0']");		
	private String Available_Format="//span[text()[normalize-space()='%s']]/preceding-sibling::div/input[@type='checkbox']";
	private String processed_Format = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[@type='radio']";
	private By NextButton = By.xpath("//span[contains(@class,'mat-button-wrapper')]");	
			

	public FormatInfoPage(WebDriver driver) {
		super(driver);
		
	}

	public void launchOnboardingUrl(String env,String role,String Publicaton_ID) {
		switch (env) {
		case "QA":
			navigateUrl(read.getProperty("Stable_URL")+role+"/"+Publicaton_ID);
			break;
		case "DEV":
			navigateUrl(read.getProperty("Dev_URL")+role+"/"+Publicaton_ID);
			break;
		default:
			navigateUrl(read.getProperty("Onboarding"));
			break;
		}

	}

	public void clickOnFormatTab()

	{
		//getElement(Format_Info).click();
		clickJS(getElement(Format_Info));
		String text = getElement(Format_Info).getText();
		System.out.println(text);
	}
	
	//AvailableFormat
	public void AvailableFormatValue(String option) {
		String[] str=option.split(",");
		for (int i = 0; i < str.length; i++) {
			
		String xpath = getXPath(Available_Format, str[i]);		
		if(getElement(By.xpath(xpath)).isSelected())
		{
			log.info(" Available Format Value " +str[i]+ " is already selected");
		}
		else
		{
			clickJS(getElement(By.xpath(xpath))); 
			log.info(" Available Format Value " +str[i]+ " has been selected");
		}
		}
	}
	
	
	// Processed Format
	public void processedFormatValue(String option) {
				
		String xpath = getXPath(processed_Format, option);
		
		if(getElement(By.xpath(xpath)).isSelected())
		{
			log.info("Processed Format Value " +option+ " is already selected");
		}
		else
		{
			clickJS(getElement(By.xpath(xpath)));
			log.info("Processed Format Value " +option+ " is has been selected");
		}
		
	}
	
	//xml Only
	public void xmlOnlyValue(String option) {
		
		String xpath = getXPath(processed_Format, option);
		
		if(getElement(By.xpath(xpath)).isSelected())
		{
			log.info(" xml Only Value " +option+ " is already selected");
		}
		else
		{
			clickJS(getElement(By.xpath(xpath)));
			log.info(" xml Only Value " +option+ " has been selected");
		}
		
	}
			
	public void ClickOnNextButton() {

		getElement(NextButton).click();
		log.info(" Next Button has been selected");
	}

}