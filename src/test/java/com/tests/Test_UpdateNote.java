package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test_UpdateNote extends BaseUrl {

	String token;

	@BeforeClass
	public void login() {

		Test_Authentication ta = new Test_Authentication();
		ta.auth();

		token = Test_Authentication.token;
	}

	@Test
	public void updateNote() {

		String noteId = "YOUR_NOTE_ID";

		Map<String, Object> payload = new HashMap<>();
		payload.put("title", "API Test Note (edited)");
		payload.put("content", "Updated content");
		Response getRes = RestAssured.given().header("Authorization", "Bearer " + token).when()
				.get(base_url() + "getAll/notes");
		getRes.prettyPrint();
		Response updateRes = RestAssured.given().header("Authorization", "Bearer " + token)
				.contentType("application/json").body(payload).when().put(base_url() + "update/notes/" + noteId);
		getRes.then().statusCode(200);
	}

	@Test
	public void update_invalid_noteId() {

		String url = base_url();
		Map<String, Object> payload = new HashMap<>();
		payload.put("title", "Updated Title");
		Response res = RestAssured.given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.pathParam("id", "507f1f77bcf86cd799439011").body(payload).when().put(url + "update/notes/{id}");
		Assert.assertEquals(res.getStatusCode(), 404);
		Assert.assertEquals(res.jsonPath().getString("message"), "Note not found");
		res.prettyPrint();
	}
}