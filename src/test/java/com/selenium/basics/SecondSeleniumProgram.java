package com.selenium.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SecondSeleniumProgram {

	public static void main(String[] args) {
//		1. Launch the Chrome Browser
		WebDriver driver = new ChromeDriver();
		
//		2. Enter URL: https://www.selenium.dev/blog/
		driver.get("https://www.selenium.dev/blog/");
		
//		3. Close the browser
		driver.close();

	}

}
