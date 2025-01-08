package com.api.commons;

import org.json.JSONObject;

public class ApiPage {

	public static String createRepositoryRequestBody(String repoName, boolean visibility) {
		JSONObject jo = new JSONObject();
		jo.put("name", repoName);
		jo.put("description", "Sample Repository Description");
		jo.put("private", visibility);
		return jo.toString();		
	}
	
	public static String updateRepositoryRequestBody(boolean visibility) {
		JSONObject jo = new JSONObject();
		jo.put("private", visibility);
		return jo.toString();		
	}


}
