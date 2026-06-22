package com.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_DeleteNote extends BaseUrl {

    String token;

    @BeforeClass
    public void login() {
        Test_Authentication ta = new Test_Authentication();
        ta.auth();
        token = Test_Authentication.token;
        System.out.println("Token : " + token);
    }

    @Test
    public void deleteNote() {
        Response getNotes = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(base_url() + "getAll/notes");
        getNotes.prettyPrint();
        String noteId =getNotes.jsonPath().getString("data[0]._id");
        System.out.println("Note ID : " + noteId);
        Response deleteRes = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(base_url() + "delete/notes/ById/" + noteId);
        deleteRes.prettyPrint();

        System.out.println("Status Code : "+ deleteRes.getStatusCode());
        deleteRes.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message",
                        equalTo("Notes deleted successfully"))
                .body("deletedCount", equalTo(1));
    }
}