package com.zerv.page;
import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;

public class TaskInbox extends Webutils {

	private String tasktable_textboxes = "(//table/thead//input[@id='%s'])[2]";

	private String taskTableRows = "//table/tbody/tr";

	private By dropDownArrow = By.xpath("//button[@id='wui-dropdown__toggle-18']/span[2]");

	private String dropDownOptions = "//button[@id='wui-dropdown__toggle-18']/following-sibling::ul/li/a[text()='%s']";

	private By task_spinner = By.xpath("(//*[contains(@class,'spinner__circle')])[2]");

	private By alert_popup = By.xpath("//h3[text()='Proceed with caution!']");

	private By popupContinue = By.xpath("//button[text()='Continue']");

	private By searchId = By.xpath("(//input[@id='Workflow Identifier'])[2]");

	private By taskTextXpath = By.xpath("//span[text()='Task Inbox - new']");

	private By btn_refresh = By.xpath("//i[contains(@class,'fa-refresh')]");

	private By new_tasks = By.xpath("//button[text()='New Tasks ']");
	
	private String table_header="//table/thead/tr[1]/th[text()='%s']";
	
	private By link_Signout = By.xpath("//button/a[text()='Sign out']");
	private String taskTableFirstRow = "//table/tbody/tr[1]";
	

	ReadProperty read = new ReadProperty();

	Logger log = Logger.getLogger(TaskInbox.class);

	public TaskInbox(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void launchTaskInbox(String env) {
		switch (env) {
		case "QA":
			navigate(read.getProperty("taskinbox_qa"));
			break;
		case "DEV":
			navigate(read.getProperty("taskinbox_dev"));
			break;
		default:
			navigate(read.getProperty("taskinbox_qa"));
			break;
		}

	}

	public void enterWorkflowId(String wflowId) {
		
		//pause() has been given to handle synchronisation issues
		waitTillObjectDisappears(task_spinner);
		
		
		log.info("Clearing values from Business Process");
		String xpath1 = getXPath(tasktable_textboxes, "Business Process");
		enterTextinTextbox( getElement(By.xpath(xpath1)), "");
		pause(200);
		
		log.info("Clearing values from Title");
		String xpath2 = getXPath(tasktable_textboxes, "Title");
		enterTextinTextbox( getElement(By.xpath(xpath2)), "");
		
		pause(200);
		String wflow = getXPath(tasktable_textboxes, "Workflow Identifier");
		WebElement wflowText = getElement(By.xpath(wflow));
		enterTextinTextbox(wflowText, wflowId);
		pause(200);
		
		log.info("Clearing values from Task Type");
		String xpath3 = getXPath(tasktable_textboxes, "Task Type");
		pause(200);
		enterTextinTextbox( getElement(By.xpath(xpath3)), "");
		
		//pause(300);
		//waitTillObjectDisappears(task_spinner);
	}

	public void enterProcess(String process, String tasktype) {
		log.info("Retrieving " + process + " and " + tasktype);
		waitTillObjectDisappears(task_spinner);
		String xpath1 = getXPath(tasktable_textboxes, "Task Type");
		enterTextinTextbox(getElement(By.xpath(xpath1)), tasktype);
		pause(200);
		String xpath = getXPath(tasktable_textboxes, "Business Process");
		pause(200);
		enterTextinTextbox(getElement(By.xpath(xpath)), process);

		// pause(300);
		waitTillObjectDisappears(task_spinner);
	}


	public void enterProcess(String process, String tasktype,String submissiontitle) {
		log.info("Retrieving " + process + " and " + tasktype + " title "+submissiontitle);
		waitTillObjectDisappears(task_spinner);
		String xpath1 = getXPath(tasktable_textboxes, "Task Type");
		enterTextinTextbox(getElement(By.xpath(xpath1)), tasktype);
		pause(200);
		String xpath = getXPath(tasktable_textboxes, "Business Process");
		pause(200);
		enterTextinTextbox(getElement(By.xpath(xpath)), process);
		pause(200);
		String xpath2 = getXPath(tasktable_textboxes, "Title");
		pause(200);
		enterTextinTextbox(getElement(By.xpath(xpath2)), submissiontitle);
		pause(200);
		// pause(300);
		waitTillObjectDisappears(task_spinner);
	}

	
	public void clearWorkflowId() {
		log.info("Clearing Workflow Id entry");
		String xpath = getXPath(tasktable_textboxes, "Workflow Identifier");
		getElement(By.xpath(xpath)).clear();
		waitTillObjectDisappears(task_spinner);
	}

	public void clickNewTasks() {
		log.info("Click New Tasks");
		getElement(new_tasks).click();
		waitTillObjectDisappears(task_spinner);
	}

	public void clickrefreshTasks() {
		getElement(btn_refresh).click();
		waitTillObjectDisappears(task_spinner);
	}

	public List<WebElement> getTaskTableRows() {
		clickrefreshTasks();
		return getElements(By.xpath(taskTableRows));
	}

	public String getExpRowByWorkflowId(String workflowID) {
		String taskXPath;
		int i = 1;
		pause(1000);
		for (i = 1; i <= getTaskTableRows().size(); i++) {
			WebElement wflow = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[3]"));
			if (wflow.getText().trim().contains(workflowID)) {
				break;
			}
		}
		taskXPath = taskTableRows + "[" + i + "]";
		return taskXPath;
	}

	public String getExpRowByWorkflowId(String workflowID, String tasktype) {
		String taskXPath;
		pause(1000);
		int i = 1;
		for (i = 1; i <= getTaskTableRows().size(); i++) {
			String wflow = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[3]/span")).getText()
					.trim();
			String task = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[4]/span")).getText()
					.trim();
			if (wflow.contains(workflowID) && task.contains(tasktype)) {
				break;
			}
		}
		taskXPath = taskTableRows + "[" + i + "]";
		return taskXPath;
	}

	public String getExpRowByTaskType(String process, String tasktype) {
		String taskXPath;
		pause(1000);
		int i = 1;
		for (i = 1; i <= getTaskTableRows().size(); i++) {
			String busProcess = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[2]/span"))
					.getText().trim();
			String task = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[4]/span")).getText()
					.trim();
			if (busProcess.contains(process) && task.contains(tasktype)) {
				break;
			}
		}
		taskXPath = taskTableRows + "[" + i + "]";
		return taskXPath;
	}
	
	public String getExpRowByTaskType(String process, String tasktype,String submissiontitle) {
		String taskXPath;
		//pause(1000);
		int i = 1;
		for (i = 1; i <= getTaskTableRows().size(); i++) {
			String busProcess = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[2]/span"))
					.getText().trim();
			String task = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[4]/span")).getText()
					.trim();
			String title = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[1]/span")).getText()
					.trim();
			if (busProcess.contains(process) && task.contains(tasktype) && title.contains(submissiontitle)) {
				break;
			}
		}
		taskXPath = taskTableRows + "[" + i + "]";
		return taskXPath;
	}

	public String getTaskType(String workflowID) {
		String expXpath = getExpRowByWorkflowId(workflowID) + "/td[4]";
		String taskType = getElement(By.xpath(expXpath)).getText().trim();
		return taskType;

	}

	public String getTaskType(String workflowID, String tasktype) {
		String expXpath = getExpRowByWorkflowId(workflowID, tasktype) + "/td[4]";
		String taskType = getElement(By.xpath(expXpath)).getText().trim();
		return taskType;

	}

	public List<String> getTaskTypes(String workflowID) {
		List<String> tasks = new ArrayList<String>();
		enterWorkflowId(workflowID);
		clickrefreshTasks();
		int noOfRows = getTaskTableRows().size();
		for (int i = 1; i <= noOfRows; i++) {
			WebElement wflow = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[3]"));
			if (wflow.getText().trim().contains(workflowID)) {
				String task = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[4]")).getText()
						.trim();
				tasks.add(task);
			}
		}
		return tasks;
	}

	public void selectTaskTypeDropDown(String taskType) {
		getElement(dropDownArrow).click();
		String xpath = getXPath(dropDownOptions, taskType);
		getElement(By.xpath(xpath)).click();
		waitTillObjectDisappears(task_spinner);
	}

	public void closeTaskInbox() {
		getDriver().close();
	}

	public void selectWorkflowRow(String workflowid) {
		enterWorkflowId(workflowid);
		String expRowByWorkflowId = getExpRowByWorkflowId(workflowid);
		WebElement expRow = getElement(By.xpath(expRowByWorkflowId + "/td[3]"));
		expRow.click();
		doubleClickJS(expRow);
		if (getPopUpMessage() != null) {
			getElementShort(popupContinue).click();

		}
	}

	public void selectWorkflowRow(String workflowid, String tasktype) {
		clickrefreshTasks();
		enterWorkflowId(workflowid);
		String expRowByWorkflowId = getExpRowByWorkflowId(workflowid, tasktype);
		WebElement expRow = getElement(By.xpath(expRowByWorkflowId + "/td[3]"));
		expRow.click();
		expRow.click();
		doubleClickJS(expRow);
		if (getPopUpMessage() != null) {
			getElementShort(popupContinue).click();

		}
		 pause(100);
	}

	public List<String> selectWorkflowBytask(String process, String tasktype) {
		List<String> journalDetails = new ArrayList<String>();
		String PermId, title;
		clickrefreshTasks();
		enterProcess(process, tasktype);
		String expRowByWorkflowId = getExpRowByTaskType(process, tasktype);
		WebElement expRow = getElement(By.xpath(expRowByWorkflowId + "/td[1]"));
		expRow.click();
		// expRow.click();
		doubleClickJS(expRow);
		if (getPopUpMessage() != null) {
			getElementShort(popupContinue).click();

		}
		PermId = getElement(By.xpath(expRowByWorkflowId + "/td[3]")).getText().trim();
		title = getElement(By.xpath(expRowByWorkflowId + "/td[1]")).getText().trim();
		journalDetails.add(PermId);
		journalDetails.add(title);
		return journalDetails;
	}

	public List<String> selectWorkflowBytask(String process, String tasktype,String submissiontitle) {
		List<String> journalDetails = new ArrayList<String>();
		String PermId, title;
		clickrefreshTasks();
		enterProcess(process, tasktype,submissiontitle);
		String expRowByWorkflowId = getExpRowByTaskType(process, tasktype,submissiontitle);
		WebElement expRow = getElement(By.xpath(expRowByWorkflowId + "/td[1]"));
		expRow.click();
		// expRow.click();
		doubleClickJS(expRow);
		if (getPopUpMessage() != null) {
			getElementShort(popupContinue).click();

		}
		PermId = getElement(By.xpath(expRowByWorkflowId + "/td[3]")).getText().trim();
		title = getElement(By.xpath(expRowByWorkflowId + "/td[1]")).getText().trim();
		journalDetails.add(PermId);
		journalDetails.add(title);
		return journalDetails;
	}
	
	public int getNumberOfRows(String workflowid) {
		enterWorkflowId(workflowid);
		return getTaskTableRows().size();

	}

	public String getTitle(String workflowid) {
		String title;
		enterWorkflowId(workflowid);
		String expRowByWorkflowId = getExpRowByWorkflowId(workflowid);
		WebElement expRow = getElement(By.xpath(expRowByWorkflowId + "/td[1]"));
		title = expRow.getText().trim();
		return title;
	}

	public WebElement getPopUpMessage() {
		WebElement popup = getElementShort(alert_popup);
		return popup;
	}

	public void switchToTaskInbox() {
		switchToWindow("Task Inbox");
		waitForPageToLoad(5);
	}

	public void refreshTaskTable() {
	//	pause(500);
		clearWorkflowId();
		clickrefreshTasks();
	}

	public boolean getTaskTextXpath() {
		boolean element = false;
		element = getElement(taskTextXpath).isDisplayed();
		return element;
	}

	public List<String> listgetTaskType(String workflowID) {
		List<String> listgetTaskType = new ArrayList<String>();
		int i = 1;
		for (i = 1; i <= getTaskTableRows().size(); i++) {
			WebElement wflow = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[3]"));
			if (wflow.getText().trim().equals(workflowID)) {
				String task = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[4]")).getText()
						.trim();
				listgetTaskType.add(task);
			}
		}
		return listgetTaskType;

	}
	
	public void sortCreationTimeDesc()
	{
		String expXPath=getXPath(table_header,"Creation Time");
		WebElement header=getElement(By.xpath(expXPath));
		String sortProperty="";
		sortProperty=getElement(By.xpath(expXPath)).getAttribute("aria-sort");
		System.out.println(sortProperty);
		if(sortProperty.equals("none"))
		{
			log.info("clicking Creation time");
			header.click();
			pause(2000);
			header.click();
			pause(2000);
		}
		if(sortProperty.equals("descending"))
		{
			log.info("clicking Creation time");
			header.click();
			pause(2000);
			
		}
		sortProperty=getElement(By.xpath(expXPath)).getAttribute("aria-sort");
		  System.out.println(sortProperty);
		 log.info("Sort Attribute"+sortProperty);
		
		
	}

	//Satya
	
	/**
	 * Author:Satya
	 * @param taskType
	 */
	public void getTaskTypeandClick(String taskType) {
		int i = 1;
		for (i = 1; i <= getTaskTableRows().size(); i++) {
			String task = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[4]/span")).getText().trim();
			if (task.equalsIgnoreCase(taskType)) {
				break;
			}
		}
		String expXpath = taskTableRows + "[" + i + "]" + "/td[4]";
		WebElement expRow =getElement(By.xpath(expXpath));
		expRow.click();
		doubleClickJS(expRow);

	}
	
	/**
	 * Author:Satya
	 * @param taskType
	 * @return
	 */
	public String getCreator(String taskType)
	{
		int i = 1;
		for (i = 1; i <= getTaskTableRows().size(); i++) {
			String task = getDriver().findElement(By.xpath(taskTableRows + "[" + i + "]" + "/td[4]/span")).getText().trim();
			if (task.equalsIgnoreCase(taskType)) {
				break;
			}
		}
		String expXpath = taskTableRows + "[" + i + "]" + "/td[5]";
		WebElement expRow =getElement(By.xpath(expXpath));
		return expRow.getText();
	}
	
	
	
	public void clickOnContinue()
	{
		if(getElement(popupContinue).isDisplayed())
		{
			getElement(popupContinue).click();
			pause(2000);
		}
	}
	
	
	
		public void enterTaskType(String tasktype) {
			log.info("Retrieving " + tasktype);
			String xpath1 = getXPath(tasktable_textboxes, "Task Type");
			enterTextinTextbox(getElement(By.xpath(xpath1)), tasktype);
			pause(2000);
			clickNewTasks();
			
		}
		
		public void selectWorkflowRow() {
			WebElement expRow = getElement(By.xpath(taskTableFirstRow + "/td[4]"));
			expRow.click();
			doubleClickJS(expRow);
			/*if (getPopUpMessage() != null) {
				getElementShort(popupContinue).click();

			}*/
			 pause(100);
		}
}
