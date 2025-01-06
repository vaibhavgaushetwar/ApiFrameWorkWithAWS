package com.qa.api.schemaValidation.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PrdoctsApiSchemaTest extends BaseTest {
    @Test
public void productsApiSchemaTest(){
        Response response = restClient.get(BASE_URL_PRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.ANY);

        Assert.assertTrue(SchemaValidator.validateSchema(response,"schema/productschema.json"));

    }

}
