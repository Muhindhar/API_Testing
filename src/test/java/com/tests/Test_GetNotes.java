package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_GetNotes extends BaseUrl {

    @BeforeClass
    public void login() {
        Test_Authentication ta = new Test_Authentication();
        ta.auth();
    }

    @Test
    public void getnotes() {
        String url = base_url();

        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + Test_Authentication.token)
                .when()
                .get(url + "getAll/notes");

        res.prettyPrint();
        System.out.println("Status Code: " + res.getStatusCode());

        res.then().statusCode(200);

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getBoolean("success"), true);
        Assert.assertNotNull(res.jsonPath().getString("data[0]._id"));
        Assert.assertNotNull(res.jsonPath().getString("data[0].title"));
        Assert.assertNotNull(res.jsonPath().getString("pagination.currentPage"));
    }

    @Test
    public void valid_withqparam1() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", 1);
        queryParams.put("limit", 50);
        queryParams.put("search", "Test");
        queryParams.put("tags", "[qa]");
        queryParams.put("isPinned", true);
        queryParams.put("sortBy", "lastEdited");
        queryParams.put("sortOrder", "asc");

        String url = base_url();

        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + Test_Authentication.token)
                .queryParams(queryParams)
                .when()
                .get(url + "getAll/notes");

        res.prettyPrint();
        System.out.println("Status Code: " + res.getStatusCode());
        res.then().statusCode(200);
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getBoolean("success"), true);
        Assert.assertNotNull(res.jsonPath().getString("data"));
        Assert.assertEquals(res.jsonPath().getInt("pagination.currentPage"), 1);
    }

    @Test
    public void valid_withqparam2() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", 1);
        queryParams.put("limit", 50);
        queryParams.put("search", "Test");
        queryParams.put("tags", "[qa,demo]");
        queryParams.put("isPinned", false);
        queryParams.put("sortBy", "title");
        queryParams.put("sortOrder", "desc");

        String url = base_url();

        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + Test_Authentication.token)
                .queryParams(queryParams)
                .when()
                .get(url + "getAll/notes");

        res.prettyPrint();
        System.out.println("Status Code: " + res.getStatusCode());

        res.then().statusCode(200);

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getBoolean("success"), true);
        Assert.assertNotNull(res.jsonPath().getString("data"));
        Assert.assertEquals(res.jsonPath().getInt("pagination.currentPage"), 1);
    }
}