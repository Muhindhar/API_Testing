package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteMethod {
  @Test
  public void delete() {
	  Response a = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/users/2");
	  a.prettyPrint();
	  Response b = RestAssured.given().when().delete("https://jsonplaceholder.typicode.com/users/2");
	  b.prettyPrint();
  }
}
