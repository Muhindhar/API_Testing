package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_GetRole extends BaseUrl{
	
	
  @BeforeClass
  public void login() {
	  Test_Authentication ta = new Test_Authentication();
	  ta.auth();
  }
  
  @Test
  public void role() {
	  String url = base_url();
	  Response head = RestAssured.given().header("Authorization","Bearer "+Test_Authentication.token).when().get(url+"roles/getAll");
	  head.prettyPrint();
	  head.then().statusCode(200);
	  head.prettyPrint();
	  Assert.assertEquals(head.getStatusCode(), 200);
	  Assert.assertEquals(
			  head.jsonPath().getString("message[0].key"),"success");
	    Assert.assertEquals(
	    		head.jsonPath().getString("message[0].value"),"Role Retrieved successfully");
	    Assert.assertEquals(
	    		head.jsonPath().getString("roles[0].originalRole"), "Admin");
	}
  @Test
	public void get_role_invalid_token() {
	    String url=base_url();
	    Response res = RestAssured.given()
	            .header("Authorization", "Bearer invalidtoken123")
	            .when()
	            .get(url + "roles/getAll");
	    res.prettyPrint();
	    Assert.assertEquals(res.getStatusCode(), 401);
	}
  @Test
	public void get_role_invalid_endpoint() {
	    String url=base_url();
	    Response res = RestAssured.given()
	            .header("Authorization", "Bearer "+url)
	            .when()
	            .get(url + "roles/getAlll");
	    res.prettyPrint();
	    Assert.assertEquals(res.getStatusCode(), 404);
	}
  }