package com.zerv.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class TaskChecklistPage extends Webutils {

	ReadProperty read = new ReadProperty();
	Logger log = Logger.getLogger(TaskChecklistPage.class);
	private String TaskChecklist = "//span[text()[normalize-space()='%s']]/preceding-sibling::div/input[contains(@id,'mat-checkbox')]";
	private By btn_Pass = By.xpath("//button/span[text()[normalize-space()='Pass']]");
	private By btn_Start = By.xpath("//span[contains(.,'Passarrow_forward')]");
	private By evaluation_textArea = By.xpath("//p[text()=' Evaluation result comments']/..//textarea");
	private By btn_Complete = By.xpath("//button/span[text()[normalize-space()='Complete']]");
	private By btn_Cancel = By.xpath("//button/span[text()[normalize-space()='Cancel']]");
	private By textArea_Comment = By.xpath("//html/body/p");
	private By Comment_field = By.xpath("//span[contains(text(),'Keyboard shortcut Ctrl+B')]/preceding-sibling::span[1]");
	//private By Comment_field = By.xpath("//*[@id='cke_15']/span[1]");
	
	
	// TaskChecklist
	public void taskChecklistValue(String option) {
		String[] str = option.split(",");
		for (int i = 0; i < str.length; i++) {
			String xpath = getXPath(TaskChecklist, str[i]);
			if (getElement(By.xpath(xpath)).isSelected()) {
				log.info(" Task CheckList Value " + str[i] + " is already selected");
			} else {
				clickJS(getElement(By.xpath(xpath)));
				log.info(" Task CheckList Value " + str[i] + " is selected");
			}
		}
	}

	public TaskChecklistPage(WebDriver driver) {
		super(driver);
	}

	public void ClickOnPassButton() {

		getElement(btn_Pass).click();
		log.info(" Pass Button is selected");
	}

	public void ClickOnStartButton() {

		getElement(btn_Start).click();
		log.info(" Start Button is selected");
	}

	public void EnterEvaluationTextArea(String value) {
		enterTextinTextbox(getElement(evaluation_textArea), value);

	}

	public void ClickOnCompleteButton() {

		getElement(btn_Complete).click();
		log.info(" Complete button is selected");
	}

	public void ClickOnCancelButton() {

		getElement(btn_Cancel).click();
	}
	
	public void enterTextInTemplate(String text) {
		/*Actions a=new Actions(getDriver());
		a.moveToElement(getElement(textArea_Comment)).sendKeys("sampletest").build().perform();
		*/
		clickJS(getElement(textArea_Comment));
		waitForMilliSeconds(1000);
		enterTextJS(textArea_Comment, text);
		clickJS(getElement(textArea_Comment));
		waitForMilliSeconds(1000);
	}
	public void clickOnBold() {

		clickJS(getElement(Comment_field));
	}
	
	public void pressEnter() throws AWTException
	{
		Robot robot = new Robot(); 
		pause(2000);	
		
        robot.keyPress(KeyEvent.VK_TAB);	
        robot.keyRelease(KeyEvent.VK_TAB);
        pause(2000);
        robot.keyPress(KeyEvent.VK_TAB);	
        robot.keyRelease(KeyEvent.VK_TAB);	
        pause(2000);	
        robot.keyPress(KeyEvent.VK_ENTER);	
        robot.keyRelease(KeyEvent.VK_ENTER);
		//getElement(textArea_Comment).sendKeys(Keys.ENTER);
		
	}
	
	
}
