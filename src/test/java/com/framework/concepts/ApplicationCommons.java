package com.framework.concepts;

import org.testng.Assert;

public class ApplicationCommons {
	
	public void TestCase1() {
		Assert.fail("Test Case 1 Failed");
		System.out.println("Test Case 1 - Home Page Executed Successfully...");
	}
	
	public void TestCase2() {
		System.out.println("Test Case 2 - Fund Transfer Executed Successfully...");
	}
	
	public void TestCase3(String username, String password) {
		System.out.println("Test Case 3 - Login Executed Successfully with..."+username+" ,"+password);
	}

}
