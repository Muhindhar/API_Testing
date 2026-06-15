package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ApiValidationTest {
  @Test
  public void f() {
	  String email = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/users/1").jsonPath().getString("email");
	  System.out.println("Email : "+email);
	  Assert.assertEquals(email, "Sincere@april.biz");
	  
	  String zipcode = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/users/1").jsonPath().getString("address.zipcode");
	  System.out.println("Zipcode : "+zipcode);
	  Assert.assertEquals(zipcode, "92998-3874");
	  
	  String lat = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/users/1").jsonPath().getString("address.geo.lat");
	  System.out.println("Latitude : "+lat);
	  Assert.assertEquals(lat, "-37.3159");
	  
  }
}
