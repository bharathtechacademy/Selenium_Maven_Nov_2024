package com.selenium.webactions;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WaitMethodsInSelenium {
	
	//1. Implicit wait ==> It will wait for the next line until max specified time 
	//2. Explicit wait ==> Exclusively wait for specific element or condition
	//3. Fluent wait ==> Exclusively wait for specific element or condition and also control the interval to check condition

	public static void main(String[] args) {
		
//		0. Logger to log messages
		Logger logger = Logger.getLogger("MyLogger");
		
//      1. Launch browser window(Chrome)    
		WebDriver driver = new ChromeDriver();
		
//      2. Maximize the browser window 
		driver.manage().window().maximize();
		
//      3. Delete all the cookies     
		driver.manage().deleteAllCookies();
		
//      4. Enter URL and Launch the application (https://parabank.parasoft.com/parabank/index.htm) 
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
		
//      5. Verify application title (ParaBank | Welcome | Online Banking)
		String expTitle = "ParaBank | Welcome | Online Banking";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expTitle);
		logger.info("INFO: Actual title is matching with Expected title");		
		
//      6. wait for element
		
		//1. Implicit wait ==> It will wait for the next line until max specified time 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//2. Explicit wait ==> Exclusively wait for specific element or condition
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		explicitWait.until(ExpectedConditions.alertIsPresent());
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(""), 0));
		explicitWait.until(ExpectedConditions.visibilityOf(null));
		explicitWait.until(ExpectedConditions.invisibilityOf(null));
		
		//3. Fluent wait ==> Exclusively wait for specific element or condition and also control the interval to check condition
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(5));
		fluentWait.until(ExpectedConditions.alertIsPresent());
		fluentWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(""), 0));
		fluentWait.until(ExpectedConditions.visibilityOf(null));
		fluentWait.until(ExpectedConditions.invisibilityOf(null));
	}

}
