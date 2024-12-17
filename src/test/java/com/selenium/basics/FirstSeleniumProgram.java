package com.selenium.basics;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebDriver;

public class FirstSeleniumProgram {
	
	
	public static void main(String[] args) {
	
//		1. Launch the Edge Browser
		//System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
//		2. Enter URL: https://www.selenium.dev/blog/
		driver.get("https://www.selenium.dev/blog/");
		
//		3. Close the browser
		driver.close();

	}

}
