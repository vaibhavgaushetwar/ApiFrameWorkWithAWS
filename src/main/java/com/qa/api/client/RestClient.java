package com.qa.api.client;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkException;
import com.qa.api.manager.ConfigManager;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
public class RestClient {



////RequestSpecification  request;
//	//Define response spec
//
	private ResponseSpecification spec200 = expect().statusCode(200);
	private ResponseSpecification spec200or404 =  expect().log().all().statusCode(anyOf(equalTo(200), equalTo(404)));
	private ResponseSpecification spec200or201 =  expect().log().all().statusCode(anyOf(equalTo(200), equalTo(201)));
	private ResponseSpecification spec201 = expect().statusCode(201);
	private ResponseSpecification spec204 = expect().statusCode(204);
	private ResponseSpecification spec400 = expect().statusCode(400);
	private ResponseSpecification spec401 = expect().statusCode(401);
	private ResponseSpecification spec404 = expect().statusCode(404);
	private ResponseSpecification spec422 = expect().statusCode(422);
	private ResponseSpecification spec500 = expect().statusCode(500);
	private ResponseSpecification spec501 = expect().statusCode(501);
	//
//
//	private String baseurl = ConfigManager.get("baseUrl");
//
//
//private  String  baseUrl= ConfigManager.get("baseUrl");
	private RequestSpecification setupRequest(String baseUrl,AuthType authType, ContentType contentType) {

	//System.out.println("Using Base URL: " + baseUrl);
		RequestSpecification request = RestAssured
				.given().log().all()
				.baseUri(baseUrl)
				.contentType(contentType)
				.accept(contentType);

		switch (authType) {
			case BEARER_TOKEN:
				request.header("Authorization", "Bearer " + ConfigManager.get("bearerToken"));
				break;
			case BEARER_TOKEN_GOREST:
				request.header("Authorization", "Bearer " + ConfigManager.get("bearer_Token_gorest"));
				break;
			case OAUTH2:
				request.header("Authorization", "Bearer " + generateOAUTh2Token());
				break;
			case BASIC_AUTH:
				request.header("Authorization", "Basic " +generateBasicToken());
				break;
			case API_KEY:
				request.header("x-api-key", ConfigManager.get("apiKey"));
				break;
			case NO_AUTH:
				System.out.println("No api key needed....");
				break;
			default:
				System.out.println("This auth is not supported ...please pass right AuthType");
				throw new FrameworkException("NOT AUTH SUPPORTED");
		}
		System.out.println(baseUrl);
		return request;

	}
private String generateOAUTh2Token(){
		ConfigManager.set("clientId","ykAzxTipsPeh6CVO4EB0K4tzP3Wyf2nt");
	    ConfigManager.set("grantType","client_credentials");
	    ConfigManager.set("clientSecret","8hmVwGqOaQLDdmgX");
	    ConfigManager.set("tokenUrl","https://test.api.amadeus.com/v1/security/oauth2/token");
		String resopnse=RestAssured.given()
				.log().all()
				.formParam("client_id",ConfigManager.get("clientId"))
				.formParam("client_secret",ConfigManager.get("clientSecret"))
				.formParam("grant_type",ConfigManager.get("grantType"))
				.post(ConfigManager.get("tokenUrl"))
				.then()
				.log().all()
				.extract()
				.path("access_token");
		System.out.println("access_token is ===========>" +resopnse);
		return resopnse;
}
private String generateBasicToken(){
		ConfigManager.set("basicUsernmae","admin");
	ConfigManager.set("basicPassword","admin");
		String Cred=ConfigManager.get("basicUsernmae") +":" +ConfigManager.get("basicPassword");
		return Base64.getEncoder().encodeToString(Cred.getBytes(StandardCharsets.UTF_8));
}

public Response get(String baseUrl, String endPoint ,
				Map<String ,String>queryParam,
				Map<String,String>pathParam,
				AuthType authType,
				ContentType contentType){
	RequestSpecification request=setUpAuthAndContentType(baseUrl,authType, contentType);

	applyParams(request,queryParam,pathParam);
	Response response= request.get(endPoint).then().spec(spec200or404).extract().response();
	response.prettyPrint();
		return response;
}
	/**
	 * This method is used to call post Api fot T body
	 * @param <T>
	 * @param endpoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return It return post Api Response
	 */


	public <T> Response post(String baseUrl,String endpoint, T body,
							 Map<String, String> queryParam,
							 Map<String, String> pathParam,
							 AuthType authType,
							 ContentType contentType) {
		RequestSpecification request=setUpAuthAndContentType(baseUrl,authType, contentType);

         applyParams(request,queryParam,pathParam);

		Response response= request.body(body).post(endpoint).then().spec(spec200or201).extract().response();
		response.prettyPrint();
		return response;
	}


	/**
	 * This method is used to call post Api for json file body
	 * @param endpoint
	 * @param file
	 * @param queryParam
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return It return post Api Response
	 */
	public Response post(String baseUrl,String endpoint, File file,
							 Map<String, String> queryParam,
							 Map<String, String> pathParam,
							 AuthType authType,
							 ContentType contentType) {
		RequestSpecification request=setUpAuthAndContentType(baseUrl,authType, contentType);

		applyParams(request,queryParam,pathParam);

		Response response= request.body(file).post(endpoint).then().spec(spec201).extract().response();
		response.prettyPrint();
		return response;
	}

	public<T> Response put(String baseUrl,String endpoint, T body,
						Map<String, String> queryParam,
						Map<String, String> pathParam,
						AuthType authType,
						ContentType contentType) {
		RequestSpecification request=setUpAuthAndContentType(baseUrl,authType, contentType);

		applyParams(request,queryParam,pathParam);

		Response response= request.body(body).put(endpoint).then().spec(spec200or201).extract().response();
		response.prettyPrint();
		return response;
	}

	public<T> Response patch(String baseUrl,String endpoint, T body,
						Map<String, String> queryParam,
						Map<String, String> pathParam,
						AuthType authType,
						ContentType contentType) {
		RequestSpecification request=setUpAuthAndContentType(baseUrl,authType, contentType);

		applyParams(request,queryParam,pathParam);

		Response response= request.body(body).put(endpoint).then().spec(spec200).extract().response();
		response.prettyPrint();
		return response;
	}

	public Response delete(String baseUrl,String endpoint,
						Map<String, String> queryParam,
						Map<String, String> pathParam,
						AuthType authType,
						ContentType contentType) {
		RequestSpecification request=setUpAuthAndContentType(baseUrl,authType, contentType);

		applyParams(request,queryParam,pathParam);

		Response response= request.delete(endpoint).then().spec(spec204).extract().response();
		response.prettyPrint();
		return response;
	}



	private RequestSpecification setUpAuthAndContentType(String baseUrl,AuthType authType, ContentType contentType) {
		return setupRequest(baseUrl,authType, contentType);
	}

	private void applyParams(RequestSpecification request, Map<String, String> queryParam,
			Map<String, String> pathParam) {
		if (queryParam != null) {
			request.queryParams(queryParam);
		}
		if (pathParam != null) {
			request.pathParams(pathParam);
		}

}
}

