package com.qa.api.base;

import com.qa.api.manager.ConfigManager;
import org.testng.annotations.BeforeMethod;

import com.qa.api.client.RestClient;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected RestClient restClient;


    @Parameters({"baseUrl"})
    @BeforeMethod
    public void setUp(@Optional String baseUrl) {
        if (baseUrl != null) {
            ConfigManager.set("bearer_Token_gorest","93a0527fadf307a198da47751854ca6894638c7812776568d108fe41d4d2e645");
            ConfigManager.set("baseUrl", baseUrl);
        }
        restClient = new RestClient();
    }

}
