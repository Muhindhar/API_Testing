package com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_GetCourse extends BaseUrl{
  @BeforeClass
  public void login() {
	  Test_Authentication ta = new Test_Authentication();
	  ta.auth();
	  
  }
  
  @Test
	  public void role() {
		  String url = base_url();
		  Response head = RestAssured.given().header("Authorization","Bearer "+Test_Authentication.token).when().get(url+"courses-structure/getAll");
		  head.prettyPrint();
	  }
  
}
