package com.framework.concepts;

public class WebCommons {
	
	public void startReporting() {
		System.out.println("Reporting process started.....");
	}
	
	public void stopReporting() {
		System.out.println("Reporting process stopped and Report generated.....");
	}
	
	public void launchBrowserAndApplication(String browser, String url) {
		System.out.println(browser+" is launched...");
		System.out.println(url+" is launched...");
	}
	
	public void closeBrowser() {
		System.out.println("Browser is closed....");
	}

}
