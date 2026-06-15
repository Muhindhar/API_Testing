package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUserTest {
  @Test
  public void f() {
	  
	  Response response = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/users");
	  System.out.println("status code : "+response.getStatusCode());
	  response.prettyPrint();
	  Assert.assertEquals(response.getStatusCode(),200);
	  
	  String name = response.jsonPath().getString("name");
	  System.out.println(name);
	  Assert.assertEquals(name, "Leanne Graham");
	  String uname = response.jsonPath().getString("username");
	  System.out.println(uname);
	  Assert.assertEquals(uname, "Bret");
	  String email = response.jsonPath().getString("email");
	  System.out.println(email);
	  Assert.assertEquals(email, "Sincere@april.biz");
	  
  }
}
