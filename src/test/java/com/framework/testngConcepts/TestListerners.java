package com.framework.testngConcepts;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListerners implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("Test Execution Started for the Test "+result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Execution is Successful for the Test "+result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Execution is Failed for the Test "+result.getMethod().getMethodName());
		System.out.println("Test Execution is Failed due to : "+result.getThrowable().getLocalizedMessage());
	}

}
