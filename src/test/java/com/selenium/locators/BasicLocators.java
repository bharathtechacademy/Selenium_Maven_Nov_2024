package com.selenium.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasicLocators {

	public static void main(String[] args) {
		
	//Basic Locators 
		//1.id ==> id attribute value of element
		//2.name ==> name attribute value of element
		//3.className ==> class attribute value of element
		//4.tagName ==> HtmlTag value of element
		//5.linkText ==> will work only for hyper-links ==> Text value of Hyperlink
		//6.partialLinkText ==> will work only for hyper-links ==> Partial text value of Hyperlink
		
	//WebElement ==> The element displayed in the UI ==> DataType : WebElement		
	//Locator ==> The Selenium method used for locating WebElement ==> DataType: By 
		
			
//			1. Launch the Browser window (Browser = Chrome) 
			WebDriver driver = new ChromeDriver();
			
//			2. Maximize the browser window  
			driver.manage().window().maximize();
			
//			3. Delete all browser cookies 
			driver.manage().deleteAllCookies();
			
//			4. Enter URL and Launch the Application (https://www.google.co.in/)   
			driver.get("https://www.google.co.in/");
			
//			Locate the WebElement Google Search Textbox using 'id' locator
			driver.findElement(By.id("APjFqb"));
			
//			Locate the WebElement Google Search Textbox using 'name' locator
			driver.findElement(By.name("q"));		
			
//			Locate the WebElement Google Search Textbox using 'className' locator
			driver.findElement(By.className("gLFyf"));
			
//			Locate the WebElement Google Search Textbox using 'tagName' locator
			driver.findElement(By.tagName("textarea"));
			
//			Locate the WebElement 'How Search Works' HyperLink using 'linkText' locator	
			driver.findElement(By.linkText(" How Search works "));
			
//			Locate the WebElement 'How Search Works' HyperLink using 'partialLinkText' locator	
			driver.findElement(By.partialLinkText("How Search"));

	}

}
