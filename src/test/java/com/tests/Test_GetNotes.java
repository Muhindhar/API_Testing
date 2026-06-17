package com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_GetNotes extends BaseUrl {
	 @BeforeClass
	  public void login() {
		  Test_Authentication ta = new Test_Authentication();
		  ta.auth();
	  }
  @Test
  public void getnotes() {
	  String url = base_url();
	  Response res = RestAssured.given().
			  header("Authorization","Bearer "+Test_Authentication.token)
			  .when().get(url+"getAll/notes");
	  res.prettyPrint();
	  System.out.println(res.getStatusCode());
	  res.then().statusCode(200);
  }
}
