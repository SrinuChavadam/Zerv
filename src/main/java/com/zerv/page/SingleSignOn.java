package com.zerv.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.web.utils.Webutils;
public class SingleSignOn extends Webutils {

	private By username = By.name("loginfmt");
	private By password = By.name("passwd");
	private By btn_next = By.id("idSIButton9");
	private By checkBox = By.id("KmsiCheckboxField");
	private By btn_signIn=By.xpath("//*[@value='Sign in']") ;

	public SingleSignOn(WebDriver driver) {
		super(driver);
	}

	public void enterUsername(String user) {
		enterTextinTextbox(getElement(username), user);
	}

	public void enterPasswd(String passwd) {
		enterTextinTextbox(getElement(password), passwd);
	}

	public void clickButton() {
		clickElement(getElement(btn_next));
	}

	public void clickChkBox() {
		clickElement(getElement(checkBox));

	}
	
	public void clickSignInButton() {
		clickElement(getElement(btn_signIn));
	}

}