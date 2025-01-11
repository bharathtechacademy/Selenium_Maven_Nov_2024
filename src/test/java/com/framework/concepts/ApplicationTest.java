package com.framework.concepts;

public class ApplicationTest {

	public static void main(String[] args) {
		
		WebCommons commons = new WebCommons();
		ApplicationCommons app = new ApplicationCommons();		
		
		commons.startReporting();
		
		//Test Case 1
		commons.launchBrowserAndApplication("Chrome", "www.icici.com");
		app.TestCase1();
		commons.closeBrowser();
		
		//Test Case 2
		commons.launchBrowserAndApplication("Chrome", "www.icici.com");
		app.TestCase2();
		commons.closeBrowser();
		
		//Test Case 3
		commons.launchBrowserAndApplication("Chrome", "www.icici.com");
		app.TestCase3("Bharath","Bharath123");
		commons.closeBrowser();
		
		commons.launchBrowserAndApplication("Chrome", "www.icici.com");
		app.TestCase3("Invalid","Invalid123");
		commons.closeBrowser();
		
		commons.stopReporting();

	}

}
