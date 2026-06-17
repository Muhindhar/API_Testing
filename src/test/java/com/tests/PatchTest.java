package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchTest {
  @Test
  public void patch() {

      Map<String,Object> create = new HashMap<>();
      create.put("id",27);
      create.put("name","Muhindhar");

      RestAssured.given()
              .contentType(ContentType.JSON)
              .body(create)
              .post("http://localhost:3000/users");

      Map<String,Object> patch = new HashMap<>();
      patch.put("company","TCS");

      Response res = RestAssured.given()
              .contentType(ContentType.JSON)
              .body(patch)
              .patch("http://localhost:3000/users/27");

      Assert.assertEquals(res.getStatusCode(),200);
  }
}