package com.example.mpr_backend.controllers;

import com.example.mpr_backend.dtos.Person;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssureControllerTest {

    private static final String URI = "http://localhost:8080/";
    @Test
    public void testGetPerson(){
        when()
                .get(URI + "person/id/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .body("name", equalTo("Jan"))
                .log()
                .body();
    }
    @Test
    public void testGetPerson2(){
       Person person = when()
                .get(URI + "person/id/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Person.class);
       assertEquals(1,person.getId());
       assertEquals("Jan",person.getName());
    }
    @Test
    public void testPostPerson(){
        Person person = new Person("Gort","jamgort","gort@gmail.com","gort123",10);
        with()
                .body(person)
                .contentType("application/json")
                .post(URI + "person/add")
//                request("POST, URI + "person/add")
                .then()
                .assertThat()
                .body("id", greaterThan(2))
                .body("name", equalTo("Gort"))
                .statusCode(202);
    }
}
