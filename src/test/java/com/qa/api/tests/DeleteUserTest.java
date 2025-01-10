package com.qa.api.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserTest  extends BaseTest {

    @Test
    public void DeleteUserTestWithBuilder() {

     //   User user = User.builder().name("Rajanvichare").email(StringUtility.getRandomEmails()).status("active").gender("male").build();
        User	user=	new User("VaibhavGaushetgdwar1",StringUtility.getRandomEmails(),"male","active");
        Response response = restClient.post(GOREST_BASE_URL,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN_GOREST, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 201);
        String UserId = response.jsonPath().getString("id");
        System.out.println("UserId ========> " + UserId);

        Response responseGet=restClient.get(GOREST_BASE_URL,"/public/v2/users/"+UserId, null, null, AuthType.BEARER_TOKEN_GOREST, ContentType.JSON);
        System.out.println("Status code ========> " +responseGet.getStatusCode());
        Assert.assertEquals(responseGet.getStatusCode(), 200);
        String Id=responseGet.jsonPath().getString("id");
        Assert.assertEquals(Id,UserId);

        Response responsePatch = restClient.delete(GOREST_BASE_URL,"/public/v2/users/" +UserId, null, null, AuthType.BEARER_TOKEN_GOREST, ContentType.JSON);
        System.out.println("Status code ========> " +responsePatch.getStatusCode());
        Assert.assertEquals(responsePatch.getStatusCode(), 204);


        Response responseGetAfterDelete=restClient.get(GOREST_BASE_URL,"/public/v2/users/"+UserId, null, null, AuthType.BEARER_TOKEN_GOREST, ContentType.JSON);
        System.out.println("Status code ========> " +responseGet.getStatusCode());
        Assert.assertEquals(responseGetAfterDelete.getStatusCode(), 404);
        Assert.assertEquals(responseGetAfterDelete.jsonPath().getString("message"),"Resource not found");
    }
}
