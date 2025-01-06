package com.qa.reqres.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqResTest extends BaseTest{
    @Test
    public void getSingleUserTest() {
        Response response=restClient.get(REQRES_BASE_URL,"/api/users?page=2", null, null, AuthType.NO_AUTH, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
