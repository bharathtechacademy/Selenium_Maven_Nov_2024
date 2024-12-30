package com.selenium.webactions;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActions3 {

	public static void main(String[] args) {
		
//      1.Launch Chrome Browser
		WebDriver driver = new ChromeDriver();
	       
//      2.Maximize the browser window
		driver.manage().window().maximize();
		
//      3.Launch the application
		driver.get("https://demoqa.com/alerts");
     
//      4.Locate Alert buttons
		WebElement infoAlert = driver.findElement(By.xpath("//button[@id='alertButton']"));
		WebElement confirmationAlert = driver.findElement(By.xpath("//button[@id='confirmButton']"));
		WebElement promptAlert = driver.findElement(By.xpath("//button[@id='promtButton']"));
     
//      5.Launch Information alert
		infoAlert.click();
		
		//wait for the alert
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		
		//switch to alert
		Alert alert = driver.switchTo().alert();
     
//      6.Print Alert message
		System.out.println(alert.getText());
     
//      7.Click on OK button
		alert.accept();
     
//      8.Launch confirmation alert
		confirmationAlert.click();
		
		//wait for the alert
		wait.until(ExpectedConditions.alertIsPresent());
		
		//switch to alert
		alert = driver.switchTo().alert();

//      9.Print Alert message
		System.out.println(alert.getText());

//      10.Click on Cancel button
		alert.dismiss();
     
//      11.Launch prompt alert with dialog box alert
		promptAlert.click();
		
		//wait for the alert
		wait.until(ExpectedConditions.alertIsPresent());
				
		//switch to alert
		alert = driver.switchTo().alert();

//      12.Print message
		System.out.println(alert.getText());

//      13.Enter Text in Alert
		alert.sendKeys("Dev - Approved");
     
//      14.Click on OK button
		alert.accept();
		
//		15. Close window
		driver.close();

	}

}
