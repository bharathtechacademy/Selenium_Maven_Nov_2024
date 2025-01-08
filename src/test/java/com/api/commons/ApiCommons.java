package com.api.commons;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.framework.utils.ReadProp;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiCommons {

	public static Properties prop = ReadProp.readData("Config.properties");

	// Method to get the response
	public static Response getResponse(String requestType, String endPoint, String requestBody) {
		Response response = null;
		RestAssured.baseURI = prop.getProperty("baseurl");
		String token = "Bearer " + prop.getProperty("token");

		switch (requestType) {

		case "GET":
			response = given().headers("Authorization", token).when().get(endPoint);
			break;
		case "DELETE":
			response = given().headers("Authorization", token).when().delete(endPoint);
			break;
		case "POST":
			response = given().headers("Authorization", token).body(requestBody).when().post(endPoint);
			break;
		case "PATCH":
			response = given().headers("Authorization", token).body(requestBody).when().patch(endPoint);
			break;
		case "PUT":
			response = given().headers("Authorization", token).body(requestBody).when().put(endPoint);
			break;
		default:
			Assert.fail("Invalid Request Type");
		}

		return response;
	}

	// Method to verify status code
	public static void verifyStatusCode(Response response, int expStatusCode) {
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, expStatusCode);
		System.out.println();
	}

	// Method to verify status message
	public static void verifyStatusMessage(Response response, String expStatusMessage) {
		String actualStatus = response.getStatusLine();
		Assert.assertTrue(actualStatus.contains(expStatusMessage));
	}

	// Method to verify Response time
	public static void verifyResponseTime(Response response, long expResponseTime) {
		long actualResponseTime = response.getTimeIn(TimeUnit.SECONDS);
		Assert.assertTrue(actualResponseTime <= expResponseTime);
	}

	// Method to verify response body
	public static void verifyResponseBody(Response response, String key, String expValue) {
		String actualValue = response.getBody().jsonPath().getString(key);
		Assert.assertEquals(actualValue, expValue);
	}

	// Method to verify key
	public static void verifyResponseKey(Response response, String key) {
		String status = response.getBody().jsonPath().get(key);
		Assert.assertNotNull(status);
	}
	
	// Method to verify response headers
	public void verifyResponseHeaders(Response response, String headerKey, String expHeaderValue) {
		String actualValue = response.getHeader(headerKey);
		Assert.assertEquals(actualValue, expHeaderValue);
	}

}
