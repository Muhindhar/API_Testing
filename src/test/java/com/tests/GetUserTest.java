package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUserTest {

	@Test
	public void f() {

		Response response = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/users");

		System.out.println("status code : " + response.getStatusCode());

		Assert.assertEquals(response.getStatusCode(), 200);

		String name = response.jsonPath().getString("[0].name");
		Assert.assertEquals(name, "Leanne Graham");

		String uname = response.jsonPath().getString("[0].username");
		Assert.assertEquals(uname, "Bret");

		String email = response.jsonPath().getString("[0].email");
		Assert.assertEquals(email, "Sincere@april.biz");
	}
}