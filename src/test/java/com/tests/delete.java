package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class delete {
  @Test
  public void f() {
	  Response res = RestAssured.given().when().delete("http://localhost:3000/users/27");
	  res.prettyPrint();
	  System.out.println("Status code : "+res.getStatusCode());
  }
}
