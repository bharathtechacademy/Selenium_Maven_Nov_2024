package com.selenium.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class XPathLocators {
	
	/*
	 * Absolute Xpath : Starts with / and it will consider all html tags from html to target element (/html/body/div[1]/div[2]/ul[1]/li[3]/a)
	 * 
	 * Relative Xpath : Starts with // and Will locate element based on target element only
	 * 
	 */
	
	
	/**
	 * Basic XPATH  (Level 1)
	 * 
	 * Syntax 1: //tagName[@Attribute='Attribute-Value']
	 * Syntax 2: //tagName[text()='Text-Value']
	 * 
	 * 
	 *Level 2 : contains or starts-with
	 *
	 *Syntax 3: //tagName[contains(@Attribute,'partial-Attribute-Value')]
	 *Syntax 4: //tagName[contains(text(),'partial-Text-Value')]
	 * 
	 *Syntax 5: //tagName[starts-with(@Attribute,'partial-Attribute-Value')]
	 *Syntax 6: //tagName[starts-with(text(),'partial-Text-Value')]
	 * 
	 * 
	 * 
	 * Level 3: combine multiple attributes and text values
	 * 
	 * Syntax 7: //tagName[ @Attribute1='Attribute-Value' and @Attribute2='Attribute-Value' and text()='Text-Value']
	 * 
	 * 
	 * Level 4: Advanced Xpath with relationships
	 * 
	 * Syntax 7: refElementXpath/relationship::targetElementXpath
	 * 
	 * child
	 * parent
	 * ancestor
	 * following-sibling
	 * preceding-sibling
	 * following
	 * preceding
	 * / (with in the family)
	 * 
	 */
	
	// target > target's sibling > parent > parent's sibling > parent's parent 
	
	//parent's parent : //ul[@class='leftmenu']
	//parent : //li
	//target : //a[text()='Services']
	
	//ul[@class='leftmenu']/child::li/child::a[text()='Services']
	//ul[@class='leftmenu']//a[text()='Services']
	
	
	//parent's sibling : //li[text()='Solutions']
	//parent : //li
	//target : //a[text()='Services']
	
	//li[text()='Solutions']/following-sibling::li/child::a[text()='Services']
	
	public static void main(String[] args) {
		
//		1. Launch the Browser window (Browser = Chrome) 
		WebDriver driver = new ChromeDriver();
		
//		2. Maximize the browser window  
		driver.manage().window().maximize();
		
//		3. Delete all browser cookies 
		driver.manage().deleteAllCookies();
		
//		4. Enter URL and Launch the Application (https://parabank.parasoft.com/parabank/index.htm)   
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
		
//		Locate the WebElement Logo using 'xpath' locator Syntax 1
		driver.findElement(By.xpath("//img[@class='logo']"));
		
//		Locate the WebElement caption using 'xpath' locator Syntax 2
		driver.findElement(By.xpath("//p[text()='Experience the difference']"));
		
//		Locate the WebElement Logo using 'xpath' locator Syntax 3
		driver.findElement(By.xpath("//img[contains(@src,'logo')]"));
		
//		Locate the WebElement caption using 'xpath' locator Syntax 4
		driver.findElement(By.xpath("//p[contains(text(),'difference')]"));
		
//		Locate the WebElement Logo using 'xpath' locator Syntax 5
		driver.findElement(By.xpath("//img[starts-with(@src,'images/logo')]"));
		
//		Locate the WebElement caption using 'xpath' locator Syntax 6
		driver.findElement(By.xpath("//p[contains(text(),'Experience')]"));

//		Locate the WebElement logo using 'xpath' locator Syntax 7
		driver.findElement(By.xpath("//img[@class='logo' and @alt='ParaBank' and @title='ParaBank']"));
		
//		Locate the WebElement services using 'xpath' locator Syntax 8
		driver.findElement(By.xpath("//ul[@class='leftmenu']//a[text()='Services']"));

	}

}
