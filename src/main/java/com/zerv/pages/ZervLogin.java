package com.zerv.pages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;



public class ZervLogin extends Webutils{
	Logger log = Logger.getLogger(ZervLogin.class);
	private By input_Username=By.xpath("//input[@name='email']");
	private By input_Password=By.xpath("//input[@name='password']");
	private By btn_Submit=By.xpath("//button[@type='submit']");
	
	
	public ZervLogin(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Author:Satya
	 * @param: field,text
	 */
	
	public void enterInputfields(String field,String text) {
		
		switch (field) {
		case "username":
			enterTextinTextbox(getElement(input_Username), text);
			break;
		case "password":
			
			enterTextinTextbox(getElement(input_Password), text);
			break;
        default:
        	 break;
		
		}

	}
	/**
	 * Author:Satya
	 */
	public void ClickOnSubmitButton() {

		getElement(btn_Submit).click();

	}


}
