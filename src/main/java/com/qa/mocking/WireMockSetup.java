package com.qa.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WireMockSetup {
    private static WireMockServer server;
    public static void CreateMockserver(){
         server = new WireMockServer(8089);
         WireMock.configureFor("localhost",8089);
          server.start();;
          System.out.println("========================================================================= Server started==============================================");
    }
    public static void StopWireMockserver(){
        System.out.println("========================================================================= Server stopped=============================================");
        server.stop();
    }
}
