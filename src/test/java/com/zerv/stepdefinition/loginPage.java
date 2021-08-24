package com.zerv.stepdefinition;
import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;

import com.backup.stepdefinition.ValidateCPSInputFormUI;
import com.zerv.api.utils.ReadProperty;
import com.zerv.database.utils.DatabaseUtils;
import com.zerv.excel.utils.SpreadSheetBaseFunction;
import com.zerv.pages.ZervLogin;
import com.zerv.runner.TestRunner;
import com.zerv.web.utils.Webutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.*;

public class loginPage extends TestRunner {
	ZervLogin zl=new ZervLogin(Webutils.getDriver());
	String browser = "chrome";
	Logger log = Logger.getLogger(ValidateCPSInputFormUI.class);
	@Given("^am a zerv user launch chrome browser$")
	public void am_a_zerv_user_launch_chrome_browser() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		zl.initiateBrowser(browser);
	}

	@When("^user access zerv url \"(.*?)\"$")
	public void user_access_zerv_url(String url) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	  zl.navigate(url);
	  zl.pause(3000);
	}

	@When("^user enters username \"(.*?)\" and password \"(.*?)\"$")
	public void user_enters_username_and_password(String username, String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    zl.enterInputfields("username", username);
	    zl.enterInputfields("password",password);
	}

	@When("^click on Submit button$")
	public void click_on_Submit_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    zl.ClickOnSubmitButton();
	    zl.pause(2000);
	}

	@Then("^page title should contain \"(.*?)\"$")
	public void page_title_should_contain(String url) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    String currenturl=zl.getCurrentURL();
	    if(currenturl.contains(url))
	    {
	    	log.info("Login successful");
	    }
	    	else
	    	{
	    		log.info("Login is failed");
	    }
	}
	@Then("^close browser$")
	public void close_browser() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    zl.quitBrowsers();
	}

}
