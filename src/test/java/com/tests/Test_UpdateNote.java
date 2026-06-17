package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
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
        Response getRes = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(base_url() + "getAll/notes");

        getRes.prettyPrint();
        Response updateRes = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(payload)
                .when()
                .put(base_url() + "update/notes/" + noteId);

        getRes.then().statusCode(200);
    }
}