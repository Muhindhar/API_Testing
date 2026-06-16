package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Get {
  @Test
  public void get() {
	  Response re = RestAssured.given().when().get("http://localhost:3000/users/1");
	  System.out.println("Statuscode : "+re.getStatusCode());
	  String name = re.jsonPath().getString("name");
	  Assert.assertEquals(name, "Muhii");
  }
}
