package com.qa.mocking.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.mocking.APIMocks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class MockGetApiProductTest extends BaseTest {

    @Test
    public void getDummyUserProductTestWithFile() throws IOException {
        RestAssured.defaultParser = Parser.JSON;
        APIMocks.getDummyUserWithProductJsonFile();
        //  RestAssured.defaultParser = Parser.JSON;
        Response response = restClient.get(BASE_URL_LOCALHOST_PORT, "/api/products", null, null, AuthType.NO_AUTH, ContentType.JSON);

        response.then().log().all().assertThat().statusCode(200);
    }
}

