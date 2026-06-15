package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Header {
  @Test
  public void f() {
	  Response resp = RestAssured.given().when().head("https://jsonplaceholder.typicode.com/posts/1");
	  resp.prettyPrint();
  }
}
