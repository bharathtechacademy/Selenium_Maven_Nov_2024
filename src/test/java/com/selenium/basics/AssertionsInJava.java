package com.selenium.basics;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssertionsInJava {

	public static void main(String[] args) {
		//Hard Assertions ==> Will fail the program immediately when there is mismatch
		Assert.assertEquals("Barath", "Barath");
		Assert.assertNotEquals("Barath", "Sarath");
		Assert.assertTrue(5>4);
		Assert.assertFalse(5<4);
		Assert.assertNull(null);
		Assert.assertNotNull(123);
		//Assert.fail("Fail");
		
		//Soft Assertions ==> Will fail the program at the end of the execution even when there are multiple mismatches
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals("Barath", "Sarath");
		softAssert.assertNotEquals("Barath", "Barath");
		softAssert.assertTrue(5<4);
		softAssert.assertFalse(5>4);
		softAssert.assertNull(123);
		softAssert.assertNotNull(null);
		softAssert.fail("Failed");
		softAssert.assertAll();
		
		System.out.println("Execution Completed");
		
	}

}
