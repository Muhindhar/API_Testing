package com.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_GetInstitution {
  @Test
  public void post() {
	  Response res = RestAssured.given()
			  .when().
			  get("https://lms-server-3-wedg.onrender.com/getAll/institution");
	  res.prettyPrint();
	  System.out.println(res.getStatusCode());
	  res.then().statusCode(200);
  }
  @Test
  public void invalid() {
	  Response res = RestAssured.given().when()
			  .get("https://lms-server-3-wedg.onrender.com/getAll");
	  res.prettyPrint();
	  res.then().statusCode(404);
	  System.out.println("Status Code: " + res.getStatusCode());
	  res.prettyPrint();
  }
}
