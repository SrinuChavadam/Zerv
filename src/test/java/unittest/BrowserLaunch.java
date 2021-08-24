package unittest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.zerv.api.utils.ReadProperty;


public class BrowserLaunch {

	public static void main(String[] args) throws InterruptedException {
		ReadProperty prop=new ReadProperty();
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("-incognito");
		//options.setHeadless(true);
	
		options.addArguments("--headless");
		ChromeDriver driver = new ChromeDriver(options);	
		
		//WebDriver driver=new ChromeDriver();	
		driver.manage().window().maximize();
		driver.get("http://ccpublicationonboardingui.dev-stable.cc.clarivate.com/publication-onboarding/acquisition-setup/91604724547");
					
		JavascriptExecutor js=(JavascriptExecutor) driver;
		 js.executeScript(String.format("window.localStorage.setItem('%s','%s');", "ls.token", prop.getProperty("newsession")));
		
		 driver.navigate().to("http://ccpublicationonboardingui.dev-stable.cc.clarivate.com/publication-onboarding/acquisition-setup/91604724547");
		 
		 Thread.sleep(2000);
		// driver.getCurrentUrl());
		 
	String text = driver.findElement(By.xpath("//span[contains(.,'Format Information Descriptive text')]")).getText();
System.out.println(text);
				}

}
