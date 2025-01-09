package com.qa.mocking;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class APIMocks {

    public static void getDummyUser() {
        stubFor(get(urlEqualTo("/api/vaibhav"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("ContentType", "application/json")
                        .withBody("{\n" +
                                "    \"name\": \"VaibhavSingh1\"\n" +
                                "}")
                )
        );
    }

    public static void getDummyUserWithJsonFile() throws IOException {

        String jsonBody = new String(Files.readAllBytes(Paths.get("C:/Users/Vaibhav Gaushetwar/eclipse-workspace/ApiFrameWork/src/test/resource/__files/user.json")));


        jsonBody = jsonBody.replace("\r", "").replace("\n", "");


        stubFor(get(urlEqualTo("/api/vaibhav"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json") // Corrected header name
                        .withBody(jsonBody) // Use the JSON content directly
                )
        );
    }
    public static void getDummyUserWithProductJsonFile() throws IOException {

        String jsonBody = new String(Files.readAllBytes(Paths.get("C:/Users/Vaibhav Gaushetwar/eclipse-workspace/ApiFrameWork/src/test/resource/__files/products.json")));
        jsonBody = jsonBody.replace("\r", "").replace("\n", "");

        stubFor(get(urlEqualTo("/api/products"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonBody)
                )
        );

    }

        //********************************* Create Mock Server **********************************************************************
        public static void createDummyUser() {
            stubFor(post(urlEqualTo("/api/users"))
                    .withHeader("Content-Type", equalTo("application/json"))
                    .willReturn(aResponse()
                            .withStatus(201)
                            .withHeader("Content-Type", "application/json")
                            .withStatusMessage("user is created")
                            .withBody("{\"id\": 1,\"name\": \"Tom\"}")
                    ));
        }

    public static void createDummyUserWithJsonFile() throws IOException {
        String jsonBody = new String(Files.readAllBytes(Paths.get("C:/Users/Vaibhav Gaushetwar/eclipse-workspace/ApiFrameWork/src/test/resource/__files/user.json")));

        jsonBody = jsonBody.replace("\r", "").replace("\n", "");
        stubFor(post(urlEqualTo("/api/users"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withStatusMessage("user is created")
                        .withBody(jsonBody)
                ));
    }



}