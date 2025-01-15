package com.framework.testngConcepts;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {
	
	int count = 0;
	int maxRetries = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if (count < maxRetries) {
				count++;
				return true;
			}
		}

		return false;
	}

}
