package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{

	@Test
	public void getAllUsersTest() {
		Map<String ,String>queryParam= new HashMap<String,String>();
		queryParam.put("name", "Advaya Kaul");
		queryParam.put("status", "inactive");
		Response response=restClient.get("/public/v2/users",queryParam,null,AuthType.BEARER_TOKEN,ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void getSingleUserTest() {
		Response response=restClient.get("/public/v2/users/7604989", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}