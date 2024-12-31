package com.selenium.webactions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

// White Paper 
// Printer
// Ink

public class WebActions6 {	
	
	public static ExtentHtmlReporter html = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationReport.html"); //empty html report
	public static ExtentReports extent = new ExtentReports(); //printer
	public static ExtentTest logger = null;
	

	public static void main(String[] args) throws IOException {		

		extent.attachReporter(html);//inserting white paper into printer
		logger = extent.createTest("TC_001: Verify Logo And Caption");		
		
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
		WebElement logo = driver.findElement(By.xpath("//img[@class='logo']"));
		logger.pass("INFO: Logo displayed successfully");	
		
//      7. Verify application caption (Experience the difference)
		WebElement caption = driver.findElement(By.xpath("//p[@class='caption']"));
		String expectedCaption ="Experience the difference";
		String actualCaption = caption.getText();
		Assert.assertEquals(actualCaption, expectedCaption);
		logger.pass("INFO: Caption displayed successfully");	
		logger.addScreenCaptureFromPath(takeElementScreenshot("Logo", logo));
		
		extent.flush();
		
//      8. Enter Invalid credentials in Username and Password textboxes
		
		logger = extent.createTest("TC_002: Verify Login");
		
		WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
		username.clear();
		username.sendKeys("Invalid User");
		
		password.clear();
		password.sendKeys("Invalid Password");
		logger.info("Entered Credentials");
		
//      9. Verify button label (LOG IN) and Click on Login Button
		WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		String expLabel = "LOG IN";
		String actualLabel = loginBtn.getAttribute("value").toUpperCase();
		Assert.assertEquals(actualLabel, expLabel);
		logger.info("Login Label is matching and working as expected");

		loginBtn.click();
		logger.info("Clicked on Login button");

		
//      10. Verify error message is coming
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//p[@class='error']"), 0));
		logger.fail("Login Failed");
		logger.addScreenCaptureFromPath(takeWindowScreenshot("Login Error", driver));
		
		extent.flush();
		
//      11. Click on Admin page link
		WebElement adminPageLink = driver.findElement(By.xpath("//a[text()='Admin Page']"));
		adminPageLink.click();
		
//      12. Wait for admin page
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//h1[contains(text(),'Administration')]"), 0));
		
//      13. Select Data access mode as ' SOAP'
		WebElement dataAccessMode = driver.findElement(dbaMode("soap"));
		dataAccessMode.click();
		
//      14. Scroll-down till Loan provider
		WebElement loanProviderDropdown = driver.findElement(By.xpath("//select[@id='loanProvider']"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", loanProviderDropdown);
					
//      15. Select Loan provider as 'Web Service'
		Select s = new Select(loanProviderDropdown);
		s.selectByVisibleText("Web Service");
		
//      16. Click on Submit button
		WebElement submitBtn = driver.findElement(By.xpath("//input[@value='Submit']"));
		submitBtn.click();
		
//      17. wait for Successful submission message
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//b[text()='Settings saved successfully.']"), 0));
		
//      18. Click on Services Link
		WebElement servicesLink = driver.findElement(By.xpath("//ul[@class='leftmenu']//a[text()='Services']"));
		servicesLink.click();
		
//      19. Wait for Services page
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//span[@class='heading' and text()='Bookstore services:']"), 0));
		
//      20. Scroll-down till Bookstore services
		WebElement bookStoreServicesHeader = driver.findElement(By.xpath("//span[@class='heading' and text()='Bookstore services:']"));
		js.executeScript("arguments[0].scrollIntoView()", bookStoreServicesHeader);
		
//      21. Get total rows, columns in the bookstore service table
		List<WebElement> rowElements = driver.findElements(By.xpath("//span[@class='heading' and text()='Bookstore services:']/following-sibling::table[1]//tbody//tr"));
		List<WebElement> columnElements = driver.findElements(By.xpath("//span[@class='heading' and text()='Bookstore services:']/following-sibling::table[1]//tbody//tr[1]//td"));

		int totalRows = rowElements.size();
		int totalColumns = columnElements.size();
		
//      22. Get Column headers of book store services table
		for(WebElement columnHeader : columnElements) {
			System.out.println(columnHeader.getText());
		}
		
//      23. Get all the data from book store service table
		for (int r =1; r<=totalRows ; r++) {			
			for (int c=1; c<=totalColumns ; c++) {				
				WebElement cell = driver.findElement(By.xpath("//span[@class='heading' and text()='Bookstore services:']/following-sibling::table[1]//tbody//tr["+r+"]//td["+c+"]"));
				System.out.println("Cell Value of Row "+r+" Column "+c+" is : "+cell.getText());
			}
			
		}
		
		
//      24. Close browser window
		driver.quit();


	}
	
	public static By dbaMode(String value) {
		return By.xpath("//input[@value='"+value+"']");
	}
	
	
	public static String takeWindowScreenshot(String screenshotName, WebDriver driver) throws IOException {
		String filePath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+".png";
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(filePath));
		return filePath;
	}
	
	public static String takeElementScreenshot(String screenshotName, WebElement element) throws IOException {
		String filePath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+".png";
		File screenshotFile = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(filePath));
		return filePath;
	}

}
