package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class optionsTest {
	@Test
	public void opt() {
		Response res = RestAssured.given().when().options("http://localhost:3000/users");
		System.out.println("Status Code:" +res.getStatusCode());
		System.out.println("Allow Header:" +res.getHeader("Allow"));
		res.prettyPrint();
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertTrue(res.getHeader("Allow").contains("GET"));
	}
}
