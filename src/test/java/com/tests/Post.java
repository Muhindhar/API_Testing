package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Post {
  @Test
  public void post() {
	  Map<String,Object> payload = new HashMap<>();
	  payload.put("id", "104");
	  payload.put("name","krishna");
	  Response resp = RestAssured.given().contentType(ContentType.JSON).body(payload).when().post("http://localhost:3000/users");
	  resp.prettyPrint();
	  System.out.println("Status code : "+resp.getStatusCode());
	  Assert.assertEquals(resp.getStatusCode(), 201);
  }
}
