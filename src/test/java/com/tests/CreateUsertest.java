package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUsertest {
  @Test
  public void f() {
	  Map<String,Object> payload= new HashMap();
	  payload.put("userId", 1);
	  payload.put("title","my first input");
	  payload.put("body","simple");
	  
	  Response response = RestAssured.given()
			  .contentType(ContentType.JSON).
			  body(payload).when().
			  post("https://jsonplaceholder.typicode.com/posts");
	  response.prettyPrint(); 
	  System.out.println("Status code : "+response.getStatusCode());
	  Assert.assertEquals(response.getStatusCode(), 201);
	  Assert.assertEquals(response.jsonPath().getString("title"),"my first input" );
	  

	  
  }
}
