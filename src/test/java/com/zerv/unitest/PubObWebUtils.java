package com.zerv.unitest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;


public class PubObWebUtils extends Webutils {
	ReadProperty read = new ReadProperty();
	private By Format_Info=By.xpath("//*[@id='cdk-step-label-0-0']");
	
	public PubObWebUtils(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void launchOnboardingUrl(String env) {
		switch (env) {
		case "QA":
			navigateUrl(read.getProperty("Stable_URL"));
			break;
		case "DEV":
			navigateUrl(read.getProperty("Dev_URL"));
			break;
		default:
			navigateUrl(read.getProperty("Onboarding"));
			break;
		}
		
		

	}
	
	public void clickOnFormatTab()
	{
		getElement(Format_Info).click();
	}
	
	
	

/*
	public static void main(String[] args) {
		Webutils wb=new Webutils(null);
		ReadProperty prop=new ReadProperty();
		wb.initiateBrowser("chrome");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("-incognito");
		options.addArguments("--disable-popup-blocking");
		ChromeDriver driver = new ChromeDriver(options);	
		
		//WebDriver driver=new ChromeDriver();	
		driver.manage().window().maximize();
		driver.get("http://ccpublicationonboardingui.dev-stable.cc.clarivate.com/publication-onboarding/pub-onboarding/91604679603");
					
		JavascriptExecutor js=(JavascriptExecutor) driver;
		 js.executeScript(String.format("window.localStorage.setItem('%s','%s');", "ls.token", prop.getProperty("newsession")));
		
		 driver.navigate().to("http://ccpublicationonboardingui.dev-stable.cc.clarivate.com/publication-onboarding/pub-onboarding/91604679603");
		 try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	driver.findElement(By.xpath("//*[@id='cdk-step-label-0-0']")).click();

				}*/

}
