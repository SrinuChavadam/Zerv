package com.zerv.web.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SingletonBrowser {
	
	// instance of singleton class
		private static SingletonBrowser instanceOfSingletonBrowser=null;
		private WebDriver driver;
	   	Logger logger = Logger.getLogger(Webutils.class);
		public ConfigClass config = ConfigClass.getConfigFile();

		
	    // Constructor
	    private SingletonBrowser(){
	    	try
	    	{
	    	switch(config.getBrowser())
			{
			case "ie" :
			System.setProperty("webdriver.ie.driver","./drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.get(config.getUrl());
			break;
			case "chrome" :
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			break;
			case "firefox":
			driver = new FirefoxDriver();
			driver.get(config.getUrl());
			break;
			}
	    	}
	    	catch(WebDriverException ex)
	    	{
	    		ex.printStackTrace();
	    	}
	    }

	    // TO create instance of class
	    public static SingletonBrowser getInstanceOfSingletonBrowserClass(){
	        if(instanceOfSingletonBrowser==null){
	        	instanceOfSingletonBrowser = new SingletonBrowser();
	        }
	        return instanceOfSingletonBrowser;
	    }
	    
	    /**
	     * 
	     * @return
	     */
	    // To get driver
	    public WebDriver getDriver()
	    {
	    	return driver;
	    }
	    

	   

}
