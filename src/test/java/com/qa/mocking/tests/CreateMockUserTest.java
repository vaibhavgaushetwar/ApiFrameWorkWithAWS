package com.qa.mocking.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.mocking.APIMocks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class CreateMockUserTest extends BaseTest {
    @Test
    public void createDummyUserTest() {
        APIMocks.createDummyUser();


        String dummyJson = "{\"name\": \"Tom\"}";
        Response response = restClient.post(BASE_URL_LOCALHOST_PORT, "/api/users", dummyJson, null, null, AuthType.NO_AUTH, ContentType.JSON);
        response.then()
                .assertThat()
                .statusCode(201)
                .statusLine(equalTo("HTTP/1.1 201 user is created"))
                .body("id", equalTo(1));

    }



    @Test
    public void createDummyUserWithJsonFileTest() throws IOException {
        APIMocks.createDummyUserWithJsonFile();

        String dummyJson = "{\"name\": \"api\"}";
        Response response = restClient.post(BASE_URL_LOCALHOST_PORT, "/api/users", dummyJson, null, null, AuthType.NO_AUTH, ContentType.JSON);
        response.then()
                .assertThat()
                .statusCode(201)
                .statusLine(equalTo("HTTP/1.1 201 user is created"))
                .body("id", equalTo(101))
                .body("name",equalTo("Robin"));

    }
}
