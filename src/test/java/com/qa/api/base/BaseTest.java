package com.qa.api.base;

import com.qa.api.manager.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.*;

import com.qa.api.client.RestClient;

public class BaseTest {
    protected   final static String GOREST_BASE_URL="https://gorest.co.in/";
    protected   final static String REQRES_BASE_URL="https://reqres.in/";
    protected   final static String Basic_Auth_BASE_URL="https://the-internet.herokuapp.com/";
    protected   final static String BASE_URL_AMADEUS="https://test.api.amadeus.com";
    protected   final static String BASE_URL_PRODUCT="https://fakestoreapi.com";
    protected RestClient restClient;

@BeforeSuite
public void setUpReport(){
    RestAssured.filters(new AllureRestAssured());
}

    @BeforeMethod
    public void setUp() {
         ConfigManager.set("bearer_Token_gorest","93a0527fadf307a198da47751854ca6894638c7812776568d108fe41d4d2e645");
        restClient = new RestClient();
    }

}
