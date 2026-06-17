package com.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class GetTest {

	@Test
	public void get() {
		given().when().get("http://localhost:3000/users/1").then().statusCode(200).body("name", equalTo("Muhii"))
				.body("id", equalTo(1)).log().all();
	}
}