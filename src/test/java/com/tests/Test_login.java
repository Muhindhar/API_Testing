package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test_login {
  @Test
  public void login() {
	  Map<String,Object> l = new HashMap<>();
	  l.put("email", "sam@gmail.com");
	  l.put("password", "123");
	  
	  Response res = RestAssured.given().when().contentType(ContentType.JSON).body(l).
			  post("https://lms-server-3-wedg.onrender.com/user/login");
	  res.prettyPrint();
	  System.out.println(res.statusCode());
	  res.then().statusCode(201);
	  res.prettyPrint();
  }
}
