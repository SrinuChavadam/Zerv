package com.zerv.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class JournalActivationPage extends Webutils {

	ReadProperty read = new ReadProperty();

	Logger log = Logger.getLogger(JournalActivationPage.class);

	@SuppressWarnings("unused")
	private By ReviewProcess = By.xpath("//span[text()[normalize-space()='Review Process']]");

	private By Reject = By.xpath("//span[text()[normalize-space()='Reject']]");

	private String radioBtnValue = "//div[text()[normalize-space()='%s']]/preceding-sibling::div/input[@type='radio']";

	private By findelem = By.xpath("//div[@class='mat-radio-label-content']");

	private By textArea_Comment = By.xpath("//html/body/p");
	private By Comment_field = By.xpath(" //*[@id='cke_15']/span[1]");
	// private By Comment_field = By.xpath("//body[@spellcheck='false']");

	private By YesRejectBtn = By.xpath("(//*[@class='dialog-reject-button mat-raised-button'])[2]");

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

	public JournalActivationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void clickOnReviewProcess() {

		getElement(ReviewProcess).click();

	}

	public void clikOnRejectButtton() {

		getElement(Reject).click();
	}

	public void clickOnjournalActivationReject(String RejectValue) {

		String xpath = getXPath(radioBtnValue, RejectValue);
		clickJS(getElement(By.xpath(xpath)));

	}

	public List<String> getRadioBtnList() {

		List<String> li = new ArrayList<String>();

		List<WebElement> elements = getElements(findelem);
		System.out.println("elements-->" + elements);

		for (WebElement webElement : elements) {
			String text = webElement.getText();
			System.out.println(text);
			li.add(text);

		}
		return li;
	}

	public void enterTextInTemplate(String text) {

		//enterTextJS(textArea_Comment, text);
		enterTextinTextbox(getElement(textArea_Comment), text);
		//clickJS(getElement(Comment_field));
	}

	public void clickOnBold() {

		clickJS(getElement(Comment_field));
	}

	public void clikOnYesRejectButtton() {

		getElement(YesRejectBtn).click();
		System.out.println("YesRejectBtn is clicked");
	}

}
