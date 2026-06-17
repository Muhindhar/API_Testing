package com.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class Test_CreateNode extends BaseUrl {
	 @BeforeClass
	  public void login() {
		  Test_Authentication ta = new Test_Authentication();
		  ta.auth();
	  }
  @Test
  public void testCreateNote() {
      String url = base_url();
      
      Map<String, Object> payload = new HashMap<>();
      payload.put("title", "API Test Note");
      payload.put("content", "Created by tester");
      
      List<String> tagsList = new ArrayList<>();
      tagsList.add("qa");
      tagsList.add("demo");
      payload.put("tags", tagsList);
      
      payload.put("color", "#ffeb3b");
      payload.put("isPinned", false);

      Response res = RestAssured.given()
              .log().all()
              .header("Authorization", "Bearer " + Test_Authentication.token)
              .contentType(ContentType.JSON)
              .body(payload)
          .when()
              .post(url + "create/notes");
      res.prettyPrint();

      res.then()
         .statusCode(201)
         .body("success",equalTo(true))
         .body("message",equalTo("Note created successfully"))
         .body("data.title",equalTo("API Test Note"))
         .body("data.isPinned",equalTo(false));
      String noteId = res.jsonPath().getString("data._id");
      System.out.println("Extracted Note ID: " + noteId);
  }
}
