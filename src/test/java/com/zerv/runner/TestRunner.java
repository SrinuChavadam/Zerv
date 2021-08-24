package com.zerv.runner;
import java.io.File;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(

glue = {"com.zerv.stepdefinition"},
features = {"src/test/resources/com/zerv/wip"},
tags = {"@zervTest"},
plugin = { "json:target/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:output/Zerv_Report.html"}

)

public class TestRunner {
	 
	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File("src/main/resources/config/extent-config.xml"));
	    Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
	    Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
	    Reporter.setSystemInfo("Machine", 	"Windows 10" + "64 Bit");
	    Reporter.setSystemInfo("Selenium", "3.7.0");
	    Reporter.setSystemInfo("Maven", "3.5.2");
	    Reporter.setSystemInfo("Java Version", "1.8.0_151");
	    Reporter.setSystemInfo("Environment", System.getProperty("env"));
	  
	}
	
	
}
