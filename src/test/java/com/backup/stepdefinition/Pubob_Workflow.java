package com.backup.stepdefinition;
import org.apache.log4j.Logger;

import com.zerv.api.utils.ReadProperty;
import com.zerv.crypto.utils.PasswordEncrypt;
import com.zerv.page.SingleSignOn;
import com.zerv.page.TaskInbox;
import com.zerv.runner.TestRunner;
import com.zerv.web.utils.Webutils;

import cucumber.api.java.en.*;

public class Pubob_Workflow extends TestRunner {


		private static SingleSignOn sso = new SingleSignOn(Webutils.getDriver());
		private static TaskInbox task = new TaskInbox(Webutils.getDriver());
		Logger log = Logger.getLogger(Pubob_Workflow.class);
		ReadProperty read = new ReadProperty();
		//String browser = System.getProperty("browser");
		//String env = System.getProperty("env");
		String browser = "chrome";
		String env = "QA";
		PasswordEncrypt encrypt = new PasswordEncrypt();
		String userName="";
		boolean user=true;
		@Given("^I navigate to Task Inbox\\.$")
		public void i_navigate_to_Task_Inbox() throws Throwable {
			log.info("Navigating to the task inbox in "+ env +" environment");
			String passwd = encrypt.decrypt(read.getProperty("password"));
			task.initiateBrowser(browser);
			task.launchTaskInbox(env);
			sso.enterUsername(read.getProperty("username"));
			sso.clickButton();
			sso.enterPasswd(passwd);
			sso.clickSignInButton();
			sso.clickChkBox();
			sso.clickButton();
		}
		
		@When("^I select publication onboarding option from application dropdown \"(.*?)\"$")
		public void i_select_publication_onboarding_option_from_application_dropdown(String application) throws Throwable {
			task.selectTaskTypeDropDown(application);
		}

		@When("^I search for Tasks based on Tasktype \"(.*?)\"\\.$")
		public void i_search_for_Tasks_based_on_Tasktype(String tasktype) throws Throwable {
			task.enterTaskType(tasktype);
			task.sortCreationTimeDesc();
			task.pause(8000);
			userName=task.getCreator(tasktype);
			
			if(userName.split(" ")[0].equalsIgnoreCase(read.getProperty("username").split("\\.")[0]))
			{
				user=true;
			}
			else
			{
				
			user=false;
			}
			
			task.getTaskTypeandClick(tasktype);
			
		}

				
		@When("^I get the Publication id and double cick on Task$")
		public void i_get_the_Publication_id_and_double_cick_on_Task() throws Throwable {
			task.pause(10000);
				
			if(user==false)
			{
			task.clickOnContinue();
			}
				    
		}
		@Then("^I should be navigated to Acquisition setup page$")
        public void i_should_be_navigated_to_Acquisition_setup_page() throws Throwable {
            task.pause(15000);
            task.switchToWindow("Publication Onboarding");
        }

		
		
		
}
