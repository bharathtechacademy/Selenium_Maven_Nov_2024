package com.selenium.basics;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.browsingcontext.UserPromptType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class BrowserActions {

	public static void main(String[] args) {
		
//		0. Change browser configurations/options
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--incognito");
		options.addArguments("--disable-notifications");
		options.addArguments("--lang=en-US");
		options.addArguments("--ignore-certificate-error");
		
//		1. Launch the Browser window (Browser = Chrome) 
		WebDriver driver = new ChromeDriver(options);
		
//		2. Minimize browser window    
		driver.manage().window().minimize();
		
//		3. Maximize to specific resolution(430x932) 
		driver.manage().window().setSize(new Dimension(430,932));
		
//		4. Maximize the browser window  
		driver.manage().window().maximize();
		
//		5. Delete all browser cookies 
		driver.manage().deleteAllCookies();
		
//		6. Enter URL and Launch the Application (https://www.google.co.in/)   
		driver.get("https://www.google.co.in/");
				
//		7. Verify the application title (Google) 
		String actualTitle = driver.getTitle();
		String expTitle ="Google";
		Assert.assertEquals(actualTitle, expTitle);
		
//		8. Navigate to Different application (https://workspace.google.com/intl/en-US/gmail/)
		driver.navigate().to("https://workspace.google.com/intl/en-US/gmail/");
		
//		9. Go back to previous application
		driver.navigate().back();
		
//		10. Move forward to next application 
		driver.navigate().forward();
		
//		11. Refresh the application  
		driver.navigate().refresh();
		
//		12.collect the main window id     
		String mainWindowHandleId = driver.getWindowHandle();
		System.out.println(mainWindowHandleId);
		
//		13.Launch new tab and Launch the application in new tab (https://in.search.yahoo.com/)   
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://in.search.yahoo.com/");
		System.out.println(driver.getWindowHandle());
		
//		14. Switch back to the main window
		driver.switchTo().window(mainWindowHandleId);
		
//		15.Launch new window and Launch the application in new window (https://parabank.parasoft.com/parabank/index.htm)  
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
		System.out.println(driver.getWindowHandle());
		
		System.out.println(driver.getWindowHandles());
		
//		16.Switch back to main window  
		driver.switchTo().window(mainWindowHandleId);
		
//		17.Print browser window URL  
		String currentBrowserUrl = driver.getCurrentUrl();
		System.out.println(currentBrowserUrl);
		
//		18. Get the Size of window   
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();		
		System.out.println(width+"x"+height);
		
//		19. Close Current Window   
		driver.close();
		
//		20. Close all remaining windows
		driver.quit();
	
	}

}
