package com.zerv.page;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class Receipting_Rules_Page extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(Receipting_Rules_Page.class);
	int rule_Records = 0;

	private By link_ReceiptingRules = By.xpath("//span[text()[normalize-space()='Receipting Rules']]");

	private By link_AddRule = By.xpath("//div[text()[normalize-space()='+ Add Rule']]");
	private By link_Rules = By.xpath("//mat-expansion-panel//span[contains(text(),'Rule')]");
	private By link_Rule1 = By.xpath("//mat-panel-title/span[text()[normalize-space()='Rule #1']]");
	private String select_RuleHeader = "//mat-select[@formcontrolname='%s']";
	private By rules_Options = By.xpath("//mat-option[@role='option']/span");
	private By input_BeginDate = By.xpath("//input[@formcontrolname='begin']");
	private By input_EndDate = By.xpath("//input[@formcontrolname='end']");
	private String input_BeginDate1 = "//input[@formcontrolname='begin']";
	private String input_EndDate1 = "//input[@formcontrolname='end']";

	// Auto fields
	// private String select_AutoProcess =
	// "//mat-checkbox//span[text()[normalize-space()='Auto Process (optional)']]";
	private String lbl_AutoProcess = "//mat-checkbox//span[text()[normalize-space()='%s']]";
	private String chk_AutoProcess = "//mat-checkbox[@formcontrolname='%s']";
	private By btn_delete = By.xpath("//mat-icon[text()='delete']");
	private By btn_done = By.xpath("//button/span[text()[normalize-space()='Done']]");
	private By lbl_UpdatedBy = By.xpath("//div[text()='Updated By']/following-sibling::div");
	private By lbl_Errormsg = By
			.xpath("//mat-error[text()[normalize-space()='At most one rule should have an open ending']]");
	private By lbl_Errormsg1 = By
			.xpath("//mat-error[text()[normalize-space()='Begin value must be less than or equal to End value']]");

	public Receipting_Rules_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Author:satya
	 * 
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
	public void clickOnLinks(String field)

	{
		switch (field) {
		case "ReceiptingRules":
			clickJS(getElement(link_ReceiptingRules));
			pause(1000);
			break;
		case "AddRules":
			clickJS(getElement(link_AddRule));
			pause(1000);
			break;
		case "Rule1":
			clickJS(getElement(link_Rule1));
			pause(1000);
		default:
			break;

		}
	}

	/**
	 * Author:Satya
	 * 
	 * @param period
	 * @return
	 */
	public String getDate(int period) {
		// TODO Auto-generated constructor stub
		String date = "";
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, period);
		date = dateFormat.format(cal.getTime());
		return date;
	}

	/**
	 * Author:Satya
	 * 
	 * @param field
	 * @param value
	 */

	public void selectRules(String field, String value, String ruleNum) {
		String xpath = getXPath(select_RuleHeader, field);
		if (ruleNum.equals("Rule1")) {
			getElement(By.xpath(xpath)).click();
			pause(1000);
		} else {
			getElement(By.xpath("(" + xpath + ")[" + getRulesCount() + "]")).click();
			pause(1000);
		}
		List<WebElement> options = getElements(rules_Options);
		options.get(Integer.parseInt(value)).click();

	}

	/**
	 * Author:Satya
	 * 
	 * @param field
	 * @param value
	 */
	public void selectFiled(String field, String value, String ruleNum) {
		String xpath = getXPath(select_RuleHeader, field);
		if (ruleNum.equals("Rule1")) {
			getElement(By.xpath(xpath)).click();
			pause(1000);
		} else {
			getElement(By.xpath("(" + xpath + ")[" + getRulesCount() + "]")).click();
			pause(1000);
		}
		List<WebElement> options = getElements(rules_Options);
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals(value)) {
				options.get(i).click();
				break;
			}
		}
	}

	/**
	 * Author:Satya
	 * 
	 * @param field
	 * @param text
	 */

	public void enterInputfields(String field, String text, String ruleNum) {

		switch (field) {
		case "begin":
			if (ruleNum.equals("Rule1"))
				enterTextinTextbox(getElement(input_BeginDate), text);
			else
				pause(3000);
			enterTextinTextbox(getElement(By.xpath("(" + input_BeginDate1 + ")[" + getRulesCount() + "]")), text);
			break;
		case "end":
			if (ruleNum.equals("Rule1"))
				enterTextinTextbox(getElement(input_EndDate), text);
			else
				pause(3000);
			enterTextinTextbox(getElement(By.xpath("(" + input_EndDate1 + ")[" + getRulesCount() + "]")), text);
			break;
		default:
			break;
		}

	}

	/**
	 * Author:Satya
	 * 
	 * @param field
	 */
	public void checkAutoProcessfields(String field, String chk, String ruleNum) {

		pause(1000);
		String lblXpath = getXPath(lbl_AutoProcess, field);
		String chkXpath = getXPath(chk_AutoProcess, chk);

		if (ruleNum.equals("Rule1")) {
			if (getElement(By.xpath(chkXpath)).isSelected()) {
				log.info(field + " is already selected");
			} else {
				clickJS(getElement(By.xpath(lblXpath)));
			}
		} else {
			if (getElement(By.xpath("(" + chkXpath + ")[" + getRulesCount() + "]")).isSelected()) {
				log.info(field + " is already selected");
			} else {
				clickJS(getElement(By.xpath("(" + lblXpath + ")[" + getRulesCount() + "]")));
			}

		}

	}

	/**
	 * Author:Satya
	 */
	public void clickOnButtons(String field) {

		switch (field) {
		case "Done":
			getElement(btn_done).click();
			break;
		case "delete":

			getElement(btn_delete).click();
			break;
		default:
			break;

		}

	}

	/**
	 * Author:Satya
	 * 
	 * @return
	 */
	public String getUpdatedByValue() {
		return getText(lbl_UpdatedBy);

	}

	/**
	 * Author:Satya
	 * 
	 * @return
	 */
	public String getOpenEndErrorMessage() {

		return getText(lbl_Errormsg);

	}

	public String getEndValueErrorMessage() {

		return getText(lbl_Errormsg1);

	}

	public void clickOnRulelink() {
		List<WebElement> defects = getElements(link_Rules);
		// System.out.println(defects.size());
		defects.get(defects.size() - 1).click();
	}

	public int getRulesCount() {
		List<WebElement> defects = getElements(link_Rules);
		return defects.size();
	}

	public void deleteRule() {
		List<WebElement> deletes = getElements(btn_delete);
		if (deletes.size() >= 3) {
			for (int i = 2; i < deletes.size(); i++) {
				deletes.get(i).click();
				List<WebElement> deletes1 = getElements(btn_delete);
				if (deletes1.size() == deletes.size() - 1) {
					log.info("Rule is deleted successfully.");
				} else {
					log.info("Rule is not deleted.");
				}

			}
		} else {
			deletes.get(1).click();
			isElementExist(getElement(link_Rule1));
			log.info("Delete is successful");
		}

	}

	public void clickEnter() {
		getElement(input_EndDate).sendKeys(Keys.ENTER);
		pause(1000);
	}

}
