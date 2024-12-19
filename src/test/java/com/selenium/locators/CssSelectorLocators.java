package com.selenium.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CssSelectorLocators {
	
	//Syntax 1: tagName#id
	//Syntax 2: tagName.className
	//Syntax 3: tagName[attribute='attributeValue']
	
	//Syntax 4: advanced css with relationships ==> 

	public static void main(String[] args) {
	
//		1. Launch the Browser window (Browser = Chrome) 
		WebDriver driver = new ChromeDriver();
		
//		2. Maximize the browser window  
		driver.manage().window().maximize();
		
//		3. Delete all browser cookies 
		driver.manage().deleteAllCookies();
		
//		4. Enter URL and Launch the Application (https://www.google.co.in/)   
		driver.get("https://www.google.co.in/");
		
//		Locate the WebElement Google Search Textbox using 'cssSelector' locator Syntax 1
		driver.findElement(By.cssSelector("textarea#APjFqb"));
		
//		Locate the WebElement Google Search Textbox using 'cssSelector' locator Syntax 2
		driver.findElement(By.cssSelector("textarea.gLFyf"));
		
//		Locate the WebElement Google Search Textbox using 'cssSelector' locator Syntax 3
		driver.findElement(By.cssSelector("textarea[title='Search']"));
		

	}

}
