package com.github.containerdemo;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;


public class UserApiWithPostgresTest extends BaseIntegrationTest{

    @Test
    void shouldCreateAndRetrieveUser() {
        String baseUrl = appManager.getBaseUrl();

        // 1️⃣ POST a new user
        given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body("{\"name\": \"Alice\", \"email\": \"alice@example.com\"}")
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .body("name", equalTo("Alice"))
                .body("email", equalTo("alice@example.com"));

        // 2️⃣ GET all users and verify the new user exists
        given()
                .baseUri(baseUrl)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("name", hasItem("Alice"))
                .body("email", hasItem("alice@example.com"));

        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/users");

        response.then()
                .statusCode(200)
                .body("name", hasItem("Alice"))
                .body("email", hasItem("alice@example.com"));

        System.out.println("Response body:");
        response.prettyPrint();
    }
    }

