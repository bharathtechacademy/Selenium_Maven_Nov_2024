package com.framework.testngConcepts;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGTests extends TestNGCommons {
	
	@Test
	public void TestCase1() {
		Assert.fail("Test Case 1 Failed");
		System.out.println("Test Case 1 - Home Page Executed Successfully...");
	}
	
	@Test
	public void TestCase2() {
		System.out.println("Test Case 2 - Fund Transfer Executed Successfully...");
	}
	
	@Test(dataProvider="testdata")
	public void TestCase3(String username, String password) {
		System.out.println("Test Case 3 - Login Executed Successfully with..."+username+" ,"+password);
	}

}
