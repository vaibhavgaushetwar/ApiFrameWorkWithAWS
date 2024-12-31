package com.qa.api.tests;

import com.qa.api.utils.StringUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

public class CreateUserTest extends BaseTest {

	@Test
	public void CreateUserTest() {
		User	user= new User("VaibhavGaushetwar1","vaibhavraj12@gmail.com","male","Active");
		Response response=restClient.post("/public/v2/users",user, null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 201);
	}



	@Test
	public void CreateUserTestWithBuilder() {

		User user=User.builder().name("Rajanvichare").email(StringUtility.getRandomEmails()).status("active").gender("male").build();
		Response response=restClient.post("/public/v2/users",user, null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
		String UserId =response.jsonPath().getString("id");
		System.out.println("UserId ========> " +UserId);

		Response responseGet=restClient.get("/public/v2/users/"+UserId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		System.out.println("Status code ========> " +responseGet.getStatusCode());
		Assert.assertEquals(responseGet.getStatusCode(), 200);

		String Id=responseGet.jsonPath().getString("id");
		Assert.assertEquals(Id,UserId);
		Assert.assertEquals(responseGet.jsonPath().getString("name"),user.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("email"),user.getEmail());
		Assert.assertEquals(responseGet.jsonPath().getString("status"),user.getStatus());
		Assert.assertEquals(responseGet.jsonPath().getString("gender"),user.getGender());




	}
	@Test
	public void CreateUserUsingJsonFileTest() {

		File userJsonFile = new File("./src/test/resource/jsons/user.json");
		Response response=restClient.post("/public/v2/users",userJsonFile, null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}
}
