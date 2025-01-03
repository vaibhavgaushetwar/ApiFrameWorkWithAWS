package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import com.qa.api.manager.ConfigManager;
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
		queryParam.put("name", "Garud Nair");
		queryParam.put("status", "active");
		Response response=restClient.get("/public/v2/users",queryParam,null,AuthType.BEARER_TOKEN_GOREST,ContentType.JSON);
	//	ConfigManager.set("bearer_Token_gorest","93a0527fadf307a198da47751854ca6894638c7812776568d108fe41d4d2e645");
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void getSingleUserTest() {
		Response response=restClient.get("/public/v2/users/7609083", null, null, AuthType.BEARER_TOKEN_GOREST, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}