package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Patch {
  @Test
  public void patch() {
	  Map<String, Object> payload = new HashMap<>();
      payload.put("name", "DEMOUSER");
      Response res =RestAssured.given().contentType(ContentType.JSON).body(payload).when().patch("http://localhost:3000/users/101");	
      res.prettyPrint();
      System.out.println("Status code : " + res.getStatusCode());
      Assert.assertEquals(res.getStatusCode(), 200);
      Assert.assertEquals(res.jsonPath().getString("name"),"DEMOUSER");
  }
}
