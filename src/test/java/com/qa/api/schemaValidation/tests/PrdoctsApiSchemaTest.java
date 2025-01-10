package com.qa.api.schemaValidation.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PrdoctsApiSchemaTest extends BaseTest {
    @Test
public void productsApiSchemaTest() throws IOException {

        Response response = restClient.get(BASE_URL_PRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
        String jsonBody = new String(Files.readAllBytes(Paths.get("C:/Users/Vaibhav Gaushetwar/eclipse-workspace/ApiFrameWork/src/test/resource/schema/productschema.json")));
        jsonBody = jsonBody.replace("\r", "").replace("\n", "");
        Assert.assertTrue(SchemaValidator.validateSchema( response,jsonBody));

    }

}
