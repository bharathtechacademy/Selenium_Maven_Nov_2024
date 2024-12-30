package com.selenium.webactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebActions4 {

	public static void main(String[] args) {
		
//		0. Change browser configurations/options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--allow-notifications");
		options.addArguments("--lang=en-US");
		options.addArguments("--ignore-certificate-error");		
		
//      1.Launch Chrome Browser
		WebDriver driver = new ChromeDriver(options);
	       
//      2.Maximize the browser window
		driver.manage().window().maximize();
		
//      3.Launch the application
		driver.get("https://www.aajtak.in/");
		
//		4. Close browser window
		driver.close();
				
		
		
	}

}
