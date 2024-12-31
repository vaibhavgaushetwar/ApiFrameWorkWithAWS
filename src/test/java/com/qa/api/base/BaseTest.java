package com.qa.api.base;

import org.testng.annotations.BeforeMethod;

import com.qa.api.client.RestClient;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	protected RestClient restClient;
	
	
	@BeforeMethod
	public void setUp() {

		restClient=new RestClient();
	}

}
