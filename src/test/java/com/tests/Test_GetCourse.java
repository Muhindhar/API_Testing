package com.tests;

import org.testng.Assert;
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
		  Response head = RestAssured.given().
				  header("Authorization","Bearer "+Test_Authentication.token).
				  when().get(url+"courses-structure/getAll");
		  head.prettyPrint();
		  int status = head.getStatusCode();
		  head.then().statusCode(200);
		  head.prettyPrint();
		  Assert.assertEquals(status, 200);
		  Assert.assertNotNull(head.jsonPath().getString("data[0]._id"));
		  Assert.assertNotNull(head.jsonPath().getString("data[0].institution"));
		}
  @Test
	public void get_crse_without_token() {
	    String url = base_url();
	    Response res = RestAssured.given()
	            .when()
	            .get(url + "courses-structure/getAll");
	    Assert.assertEquals(res.getStatusCode(), 401);
	    res.prettyPrint();
	    res.then().statusCode(401);
		  
	  }
  
}
