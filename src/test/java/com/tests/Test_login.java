package com.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test_login {
	@Test
	public void login() {
		Map<String, Object> l = new HashMap<>();
		l.put("email", "sam@gmail.com");
		l.put("password", "123");
		Response res = RestAssured.given().
				contentType(ContentType.JSON).body(l).when()
				.post("https://lms-server-3-wedg.onrender.com/user/login");
		res.prettyPrint();
		System.out.println("Status code : " + res.getStatusCode());
		res.then().statusCode(201);
		Assert.assertEquals(res.getStatusCode(), 201);
		Assert.assertEquals(res.jsonPath().getString("user.email"),"sam@gmail.com");
		Assert.assertEquals(res.jsonPath().getString("user.status"),"active");
		Assert.assertEquals(res.jsonPath().getString("message[0].value"),"Admin logged in successfully");
	}
	@Test
	public void invalidLogin() {
		Map<String, Object> m = new HashMap<>();
		m.put("email", "muhi@gmail.com");
		m.put("password", "123");
		Response re = RestAssured.given().
				contentType(ContentType.JSON).body(m).when()
				.post("https://lms-server-3-wedg.onrender.com/user/login");
		re.prettyPrint();
		System.out.println("Status code :"+re.getStatusCode());
		re.then().statusCode(400);
		Assert.assertEquals(re.getStatusCode(),400);
		Assert.assertEquals(re.jsonPath().getString("message[0].value"),"Email is invalid");
	}
	@Test
	public void wrongpass() {
		Map<String, Object> m = new HashMap<>();
		m.put("email","sam@gmail.com");
		m.put("password","12355");
		Response re = RestAssured.given()
				.contentType(ContentType.JSON).body(m).when()
				.post("https://lms-server-3-wedg.onrender.com/user/login");
		re.prettyPrint();
		System.out.println("Status code : " + re.getStatusCode());
		re.then().statusCode(400);
		Assert.assertEquals(re.getStatusCode(),400);
		Assert.assertEquals(re.jsonPath().getString("message[0].value"),"Password is incorrect");
	}

	@Test
	public void fields() {
		Map<String, Object> m = new HashMap<>();
		m.put("email", "");
		m.put("password", "");
		Response re = RestAssured.given().
				contentType(ContentType.JSON).body(m).when()
				.post("https://lms-server-3-wedg.onrender.com/user/login");
		re.prettyPrint();
		System.out.println("Status code : " + re.getStatusCode());
		re.then().statusCode(400);
		Assert.assertEquals(re.getStatusCode(), 400);
		Assert.assertEquals(re.jsonPath().getString("message[0].value"), "All fields are required");
	}
}