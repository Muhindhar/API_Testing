package com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_TogglePin extends BaseUrl {

    String token;

    @BeforeClass
    public void login() {

        Test_Authentication ta = new Test_Authentication();
        ta.auth();

        token = Test_Authentication.token;

        System.out.println("Token : " + token);
    }

    @Test
    public void togglePin() {
        Response getNotes = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(base_url() + "getAll/notes");
        getNotes.prettyPrint();
        String noteId = getNotes.jsonPath().getString("data[0]._id");
        System.out.println("Note ID : " + noteId);
               Response res = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .put(base_url() + "toggle-pin/notes/" + noteId);
        res.prettyPrint();
        System.out.println("Status Code : " + res.getStatusCode());
        res.then().statusCode(200);
    }
}