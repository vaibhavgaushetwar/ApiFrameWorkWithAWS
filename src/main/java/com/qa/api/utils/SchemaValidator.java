package com.qa.api.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator {

    public static boolean validateSchema(Response response, String schemaFileName) {

        try {

        System.out.println("schema file name=====>" +schemaFileName);
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFileName));
            System.out.println("Schema validation passed");
            return true;
        } catch (Exception e) {
            System.out.println("Schema validation failed " + e.getMessage());
            return false;
        }

    }


}