package com.selenium.webactions;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WebActions1 {

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
		
//      6. Verify application logo
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//img[@class='logo']"), 0));
		logger.info("INFO: Logo displayed successfully");	
		
//      7. Verify application caption (Experience the difference)
//      8. Enter Invalid credentials in Username and Password textboxes
//      9. Verify button label (LOG IN) and Click on Login Button
//      10. Verify error message is coming
//      11. Click on Admin page link
//      12. Wait for admin page
//      13. Select Data access mode as ' SOAP'
//      14. Scroll-down till Loan provider
//      15. Select Loan provider as 'Web Service'
//      16. Click on Submit button
//      17. wait for Successful submission message
//      18. Click on Services Link
//      19. Wait for Services page
//      20. Scroll-down till Bookstore services
//      21. Get total rows, columns in the bookstore service table
//      22. Get Column headers of book store services table
//      23. Get all the data from book store service table
//      24. Close browser window

	}

}
