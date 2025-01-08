package com.api.test;

import com.api.commons.ApiCommons;
import com.api.commons.ApiPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.response.Response;

public class APITest extends ApiCommons {

	static String repoName = "";
	static String owner = prop.getProperty("owner");
	
	public static ExtentHtmlReporter html = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationReport.html"); //empty html report
	public static ExtentReports extent = new ExtentReports(); //printer
	public static ExtentTest logger = null;

	public static void main(String[] args) {
		
		extent.attachReporter(html);
		
		logger = extent.createTest("Create a repository for the valid user");		

		// Create a repository for the valid user
		repoName = "SampleRestAssuredRepo";
		Response response = getResponse("POST", "/user/repos", ApiPage.createRepositoryRequestBody(repoName, true));
		verifyStatusCode(response, 201);
		verifyStatusMessage(response, "Created");
		verifyResponseTime(response, 2);
		verifyResponseBody(response, "name", repoName);
		verifyResponseBody(response, "private", "true");
		logger.info(response.getBody().asPrettyString());
		logger.pass("Create a repository for the valid user Request is Successful");
		extent.flush();

		logger = extent.createTest("Update a repository for the valid user");
		// Update a repository for the valid user
		response = getResponse("PATCH", "/repos/" + owner + "/" + repoName, ApiPage.updateRepositoryRequestBody(false));
		verifyStatusCode(response, 200);
		verifyStatusMessage(response, "OK");
		verifyResponseTime(response, 2);
		verifyResponseBody(response, "name", "SampleRestAssuredRepo");
		verifyResponseBody(response, "private", "false");
		logger.info(response.getBody().asPrettyString());
		logger.pass("Update a repository for the valid user Request is Successful");
		extent.flush();

		logger = extent.createTest("Get a repository for the valid user");	
		// Get a repository for the valid user
		response = getResponse("GET", "/repos/" + owner + "/" + repoName, "");
		verifyStatusCode(response, 200);
		verifyStatusMessage(response, "OK");
		verifyResponseTime(response, 2);
		verifyResponseBody(response, "name", "SampleRestAssuredRepo");
		verifyResponseBody(response, "private", "false");
		logger.info(response.getBody().asPrettyString());
		logger.pass("Get a repository for the valid user Request is Successful");
		extent.flush();
		
		logger = extent.createTest("Delete a repository for the valid user");	
		// Delete a repository for the valid user
		response = getResponse("DELETE", "/repos/" + owner + "/" + repoName, "");
		verifyStatusCode(response, 204);
		verifyStatusMessage(response, "No Content");
		verifyResponseTime(response, 2);
		logger.info(response.getBody().asPrettyString());
		logger.pass("Delete a repository for the valid user Request is Successful");
		extent.flush();

	}

}
