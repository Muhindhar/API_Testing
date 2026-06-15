package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ParticularaData {
  @Test
  public void particulardata() {
	  Response response = RestAssured.given().pathParam("id", 1).when().get("https://jsonplaceholder.typicode.com/posts/{id}");
	  response.prettyPrint();
  }
}
