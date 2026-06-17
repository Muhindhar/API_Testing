package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.containsString;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_Get extends BaseUrl{
  @Test
  public void get() {
	  Test_Get tg = new Test_Get();
	  Response res = RestAssured.given()
			  .when().
			  get(tg.base_url());
	  res.prettyPrint();
	  Assert.assertEquals(res.getStatusCode(), 200);
	  res.then().
	  body(containsString("API Running"));
  }
}
