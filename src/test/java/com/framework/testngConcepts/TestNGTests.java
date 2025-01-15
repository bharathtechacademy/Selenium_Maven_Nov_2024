package com.framework.testngConcepts;

import org.testng.Assert;
import org.testng.annotations.Test;

// Prioritize
// Group
// Dependency
// In-built reporting
// Execute Specific test cases from runner file
// Disable test cases
// Listeners to monitor the execution
// Parallel testing & Cross browser testing

public class TestNGTests extends TestNGCommons {
	
	@Test(priority=1,groups= {"Sanity"},retryAnalyzer=RetryTest.class)
	public void TestCase1() {
		Assert.fail("Test Case 1 Failed");
		System.out.println("Test Case 1 - Home Page Executed Successfully...");
	}
	
	@Test(priority=2,groups= {"Regression"}, dependsOnMethods= {"TestCase1","TestCase3"}, enabled=false)
	public void TestCase2() {
		System.out.println("Test Case 2 - Fund Transfer Executed Successfully...");
	}
	
	@Test(dataProvider="testdata",priority=-1,groups= {"Sanity","Regression"})
	public void TestCase3(String username, String password) {
		System.out.println("Test Case 3 - Login Executed Successfully with..."+username+" ,"+password);
	}

	@Test(groups= {"Regression"})
	public void TestCase4() {
		System.out.println("Test Case 4 - Credit Card Executed Successfully...");
	}
}
