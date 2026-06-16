package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Login {
	@Test
	public void loginTest() {
		Map<String, Object> log = new HashMap<>();
		log.put("username", "admin");
		log.put("password", "admin123");
		Response res = RestAssured.given().contentType(ContentType.JSON).body(log).when().post("http://localhost:5000/login");
		res.prettyPrint();
		System.out.println("Status code : " + res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
		String token = res.jsonPath().getString("token");
		Assert.assertEquals(token, "SDET_TOKEN_25");
	}
}