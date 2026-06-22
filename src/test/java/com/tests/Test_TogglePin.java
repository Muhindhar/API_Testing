package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
                .header("Authorization","Bearer "+ token)
                .when()
                .put(base_url()+"toggle-pin/notes/"+noteId);
        res.prettyPrint();
        System.out.println("Status Code : "+res.getStatusCode());
        res.then().statusCode(200);
    }
    
    @Test
    public void togglePin_invalidNoteId() {

        String url = base_url();
        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("id", "507f1f77bcf86cd79k*439011")
                .when()
                .put(url + "toggle-pin/notes/{id}");
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), 404);
        Assert.assertEquals(res.jsonPath().getString("message"),"Note not found");
    }
    @Test
    public void delete_invalidNoteIdFormat() {
        String url = base_url();
        Response res = RestAssured.given()
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .pathParam("id","abc123")
                .when()
                .delete(url +"delete/notes/ById/{id}");
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), 400);
        Assert.assertEquals(
                res.jsonPath().getString("message"),
                "Invalid note ID format"
        );
    }
}