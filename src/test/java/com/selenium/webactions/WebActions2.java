package com.selenium.webactions;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActions2 {
	
	static WebDriver driver = null;

	public static void main(String[] args) {
//		0. Logger to log messages
		Logger logger = Logger.getLogger("MyLogger");
		
//      1. Launch browser window(Chrome)    
		driver = new ChromeDriver();
		
//      2. Maximize the browser window 
		driver.manage().window().maximize();
		
//      3. Delete all the cookies     
		driver.manage().deleteAllCookies();
     
//      4. Enter URL and Launch the application (https://demoqa.com/automation-practice-form)
		driver.get("https://demoqa.com/automation-practice-form");
     
//      5. Wait for Page-load
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//      6. Enter First name and Last name
		WebElement firstName = driver.findElement(By.xpath("//input[@id='firstName']"));
		WebElement lastName = driver.findElement(By.xpath("//input[@id='lastName']"));
		
		firstName.sendKeys("Bharath");
		lastName.sendKeys("Reddy");
         
//      7. Enter Email
		WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));
		email.sendKeys("BharathTechAcademy@Gmail.com");
		
//      8. Select Gender (Male)
		selectgender("Male");
             
//      9. Enter mobile number
		WebElement mobile = driver.findElement(By.xpath("//input[@id='userNumber']"));
		mobile.sendKeys("9553220022");
		
//      10.Select DOB (1-Feb-1991)
        selectDOB("1", "February", "1991");
        
//      11.Search and Select Computer Science
		selectSubject("Computer Science");		
             
//      12.Select Hobbies as Sports and Reading
		String [] hobbies = {"Sports", "Reading"};
		selectHobbies(hobbies);
             
//      13.Upload photo
		WebElement uploadBtn = driver.findElement(By.xpath("//input[@id='uploadPicture']"));
		uploadBtn.sendKeys(System.getProperty("user.dir")+"\\Files\\1.png");
             
//      14.Submit Details
		WebElement submitBtn = driver.findElement(By.xpath("//button[@id='submit']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", submitBtn);
             
//      15. Close browser window
		driver.quit();

	}
	
	public static void selectgender(String option) {
		WebElement gender = driver.findElement(By.xpath("//label[text()='"+option+"']"));
		gender.click();
	}
	
	public static void selectDOB(String date, String month, String year) {
		WebElement dob = driver.findElement(By.xpath("//input[@id='dateOfBirthInput']"));
		dob.click();
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@class='react-datepicker__month-select']"), 0));
		
		WebElement monthDrp = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
		Select selectMonth = new Select(monthDrp);
		selectMonth.selectByVisibleText(month);
		
		WebElement yearDrp = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
		Select selectYear = new Select(yearDrp);
		selectYear.selectByVisibleText(year);
		
		WebElement dateInput = driver.findElement(By.xpath("//div[contains(@aria-label,'"+month+"') and text()='"+date+"']"));
		dateInput.click();
	}
	
	
	public static void selectSubject(String option) {
		WebElement subjects = driver.findElement(By.xpath("//div[contains(@class,'subjects-auto-complete__value')]"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", subjects);
		
		Actions action = new Actions(driver);
		action.sendKeys(subjects,option).perform();
		
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class,'option') and text()='"+option+"']"), 0));
		
		WebElement subjectSuggestion = driver.findElement(By.xpath("//div[contains(@class,'option') and text()='"+option+"']"));
		subjectSuggestion.click();
	}
	
	public static void selectHobbies(String [] options) {		
		for (String option : options) {
			WebElement hobby = driver.findElement(By.xpath("//label[text()='"+option+"']"));
			hobby.click();
		}		
	}

}
