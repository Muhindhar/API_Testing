package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_GetNoteById extends BaseUrl {

	String token;

	@BeforeClass
	public void login() {

		Test_Authentication ta = new Test_Authentication();

		token = ta.auth();

		System.out.println(token);
	}

	@Test
	public void getNoteById() {
		String url = base_url();
		Response res = RestAssured.given().header("Authorization", "Bearer " + token).when()
				.get(base_url() + "getAll/notes");

		String noteId = res.jsonPath().getString("data[0]._id");
		System.out.println("Note ID: " + noteId);
		res.prettyPrint();
		System.out.println(res.getStatusCode());
		res.then().statusCode(200);
	}
	 @Test
	    public void invalid_noteId() {

	        String url = base_url();

	        Response res = RestAssured.given()
	                .header("Authorization", "Bearer " + token)
	                .pathParam("id", "507f1f77bcf86cd799439011")
	                .when()
	                .get(url + "getById/notes/{id}");

	        System.out.println("Status = " + res.getStatusCode());
	        res.prettyPrint();
	        Assert.assertEquals(res.getStatusCode(), 404);
	        Assert.assertFalse(res.jsonPath().getBoolean("success"));
	        Assert.assertEquals(
	                res.jsonPath().getString("message"),
	                "Note not found");
	    }
}