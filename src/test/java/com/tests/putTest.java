package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class putTest {
  @Test
  public void put() {
	  Map<String,Object> payload = new HashMap<>();
	  payload.put("id", 27);
	  payload.put("name",	 "demo");
	  payload.put("email", "demo@gmail.com");
	  payload.put("company", "expleo");
	  Response res = RestAssured.given().contentType(ContentType.JSON).body(payload).when().put("http://localhost:3000/users/27");
	  res.prettyPrint();
	  System.out.println("Status code : "+res.statusCode());
	  Assert.assertEquals(res.statusCode(), 200);
  }
}
