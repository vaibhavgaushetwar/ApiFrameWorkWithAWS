package com.qa.api.tests;

import com.qa.api.manager.ConfigManager;
import com.qa.api.utils.StringUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

public class CreateUserTest extends BaseTest {

	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
				{"Naveenshet", "male", "active"},
				{"Abhimanav", "male", "inactive"},
				{"Kanchan", "female", "active"}
		};
	}
	@Test(dataProvider = "getUserData")
	public void createUserTest(String name, String gender, String status) {
		User user = new User( name, StringUtility.getRandomEmails(), gender, status);
		ConfigManager.set("bearer_Token_gorest","93a0527fadf307a198da47751854ca6894638c7812776568d108fe41d4d2e645");
		Response response = restClient.post(GOREST_BASE_URL, "/public/v2/users", user, null, null, AuthType.BEARER_TOKEN_GOREST, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}



	@Test(dataProvider ="getUserData")
	public void CreateUserTestWithBuilder(String name,String gender,String status) {

		User user=User.builder().name(name).email(StringUtility.getRandomEmails()).status(status).gender(gender).build();
		Response response=restClient.post(GOREST_BASE_URL,"/public/v2/users",user, null,null, AuthType.BEARER_TOKEN_GOREST, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
		String UserId =response.jsonPath().getString("id");
		System.out.println("UserId ========> " +UserId);
		ConfigManager.set("bearer_Token_gorest","93a0527fadf307a198da47751854ca6894638c7812776568d108fe41d4d2e645");
		Response responseGet=restClient.get(GOREST_BASE_URL,"/public/v2/users/"+UserId, null, null, AuthType.BEARER_TOKEN_GOREST, ContentType.JSON);
		System.out.println("Status code ========> " +responseGet.getStatusCode());
		Assert.assertEquals(responseGet.getStatusCode(), 200);

		String Id=responseGet.jsonPath().getString("id");
		Assert.assertEquals(Id,UserId);
		Assert.assertEquals(responseGet.jsonPath().getString("name"),user.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("email"),user.getEmail());
		Assert.assertEquals(responseGet.jsonPath().getString("status"),user.getStatus());
		Assert.assertEquals(responseGet.jsonPath().getString("gender"),user.getGender());




	}
	@Test(enabled = false)
	public void CreateUserUsingJsonFileTest() {

		File userJsonFile = new File("./src/test/resource/jsons/user.json");
		Response response=restClient.post(GOREST_BASE_URL,"/public/v2/users",userJsonFile, null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}
}
