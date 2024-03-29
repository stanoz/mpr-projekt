package com.example.mpr_backend.controllers;

import com.example.mpr_backend.dtos.Person;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestAssureControllerTest {

    private static final String URI = "http://localhost:8080/";
    @Order(1)
    @Test
    public void getPersonByIdShouldReturnCode200(){
        when()
                .get(URI + "person/id/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .body("name", equalTo("Jan"))
                .body("login", equalTo("jamjan"))
                .body("email", equalTo("jan@gmail.com"))
                .body("password", equalTo("jan123"))
                .body("age", equalTo(50))
                .log()
                .body();
    }
    @Order(2)
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
    @Order(3)
    @Test
    public void getPersonByIdShouldReturnCode404(){
        when()
                .get(URI + "person/id/100")
                .then()
                .statusCode(404)
                .log()
                .body();
    }
    @Order(4)
    @Test
    public void getPersonByNameShouldReturnCode200(){
        when().
                get(URI + "person/name/Jan")
                .then()
                .statusCode(200)
                .assertThat()
                .body("[0].id", equalTo(1))
                .body("[0].name", equalTo("Jan"))
                .body("[0].login", equalTo("jamjan"))
                .body("[0].email", equalTo("jan@gmail.com"))
                .body("[0].password", equalTo("jan123"))
                .body("[0].age", equalTo(50))
                .log()
                .body();
    }
    @Order(5)
    @Test
    public void getPersonByNameShouldReturnCode404(){
        when().
                get(URI + "person/name/notExistingName")
                .then()
                .statusCode(404)
                .log()
                .body();
    }
    @Test
    @Order(6)
    public void getPersonByLoginShouldReturnCode200(){
        when()
                .get(URI + "person/login/jamjan")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .body("name", equalTo("Jan"))
                .body("login", equalTo("jamjan"))
                .body("email", equalTo("jan@gmail.com"))
                .body("password", equalTo("jan123"))
                .body("age", equalTo(50))
                .log()
                .body();
    }
    @Order(7)
    @Test
    public void getPersonByLoginShouldReturnCode404(){
        when()
                .get(URI + "person/login/notExistingLogin")
                .then()
                .statusCode(404)
                .log()
                .body();
    }
    @Order(8)
    @Test
    public void getPersonByEmailShouldReturnCode200() {
        when()
                .get(URI + "person/email/jan@gmail.com")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .body("name", equalTo("Jan"))
                .body("login", equalTo("jamjan"))
                .body("email", equalTo("jan@gmail.com"))
                .body("password", equalTo("jan123"))
                .body("age", equalTo(50))
                .log()
                .body();
    }
    @Order(9)
    @Test
    public void getPersonByEmailShouldReturnCode404(){
        when()
                .get(URI + "person/email/notExistingEmail")
                .then()
                .statusCode(404)
                .log()
                .body();
    }
    @Order(10)
    @Test
    public void getPersonByAgeShouldReturnCode200(){
        when()
                .get(URI + "person/age/50")
                .then()
                .statusCode(200)
                .assertThat()
                .body("[0].id", equalTo(1))
                .body("[0].name", equalTo("Jan"))
                .body("[0].login", equalTo("jamjan"))
                .body("[0].email", equalTo("jan@gmail.com"))
                .body("[0].password", equalTo("jan123"))
                .body("[0].age", equalTo(50))
                .log()
                .body();
    }
    @Order(11)
    @Test
    public void getPersonByAgeShouldReturnCode400(){
        when()
                .get(URI + "person/age/0")
                .then()
                .assertThat()
                .statusCode(400)
                .log()
                .body();
    }
    @Order(12)
    @Test
    public void getPersonByAgeShouldReturnCode404(){
        when()
                .get(URI + "person/age/10000")
                .then()
                .assertThat()
                .statusCode(404)
                .log()
                .body();
    }
    @Order(13)
    @Test
    public void getAllShouldReturnCode200(){
        when()
                .get(URI + "person/getAll")
                .then()
                .statusCode(200)
                .assertThat()
                .body("[0].id", equalTo(1))
                .body("[0].name", equalTo("Jan"))
                .body("[0].login", equalTo("jamjan"))
                .body("[0].email", equalTo("jan@gmail.com"))
                .body("[0].password", equalTo("jan123"))
                .body("[0].age", equalTo(50))
                .body("[1].id", equalTo(2))
                .body("[1].name", equalTo("Piotr"))
                .body("[1].login", equalTo("jampiotr"))
                .body("[1].email", equalTo("piotr@gmail.com"))
                .body("[1].password", equalTo("piotr123"))
                .body("[1].age", equalTo(35))
                .log()
                .body();
    }
    @Order(14)
    @Test
    public void getAllBySubNameShouldReturnCode200(){
        when()
                .get(URI + "person/filter/Ja")
                .then()
                .statusCode(200)
                .assertThat()
                .body("[0].id", equalTo(1))
                .body("[0].name", equalTo("Jan"))
                .body("[0].login", equalTo("jamjan"))
                .body("[0].email", equalTo("jan@gmail.com"))
                .body("[0].password", equalTo("jan123"))
                .body("[0].age", equalTo(50))
                .log()
                .body();
    }
    @Order(15)
    @Test
    public void getAllBySubNameShouldReturnCode404(){
        when()
                .get(URI + "person/filter/notExistingSubName")
                .then()
                .statusCode(404)
                .log()
                .body();
    }
    @Order(16)
    @Test
    public void checkIfPersonExistsShouldReturnCode200WhenPersonFound(){
        when()
                .get(URI + "person/check/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("true"))
                .log()
                .body();
    }
    @Order(17)
    @Test
    public void checkIfPersonExistsShouldReturnCode200WhenPersonNotFound(){
        when()
                .get(URI + "person/check/100")
                .then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("false"))
                .log()
                .body();
    }
    @Order(18)
    @Test
    public void addPersonShouldReturnCode202(){
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
                .body("login", equalTo("jamgort"))
                .body("email", equalTo("gort@gmail.com"))
                .body("password", equalTo("gort123"))
                .body("age", equalTo(10))
                .statusCode(202)
                .log()
                .body();
    }
    @Order(19)
    @Test
    public void addPersonShouldReturnCode400WhenLoginIsTaken(){
        Person person = new Person("Gort","jamgort","gort@gmail.com","gort123",10);
        with()
                .body(person)
                .contentType("application/json")
                .post(URI + "person/add")
                .then()
                .assertThat()
                .body(equalTo("This email is taken!"))
                .statusCode(400)
                .log()
                .body();
    }
    @Order(20)
    @Test
    public void addPersonShouldReturnCode400WhenEmailIsTaken() {
        Person person = new Person("Gort", "jamgort2", "gort@gmail.com", "gort123", 10);
        with()
                .body(person)
                .contentType("application/json")
                .post(URI + "person/add")
                .then()
                .assertThat()
                .body(equalTo("This email is taken!"))
                .statusCode(400)
                .log()
                .body();
    }
    @Order(21)
    @Test
    public void addPersonShouldReturnCode400WhenInvalidAge() {
        Person person = new Person("Gort","jamgort2","gort2@gmail.com","gort123",0);
        with()
                .body(person)
                .contentType("application/json")
                .post(URI + "person/add")
                .then()
                .assertThat()
                .body(equalTo("Invalid age!"))
                .statusCode(400)
                .log()
                .body();
    }
    @Order(22)
    @Test
    public void addPersonShouldReturnCode400WhenInvalidLogin() {
        Person person = new Person("Gort"," ","gort2@gmail.com","gort123",10);
        with()
                .body(person)
                .contentType("application/json")
                .post(URI + "person/add")
                .then()
                .assertThat()
                .body(equalTo("Invalid login!"))
                .statusCode(400)
                .log()
                .body();
    }
    @Order(23)
    @Test
    public void addPersonShouldReturnCode400WhenInvalidEmail() {
        Person person = new Person("Gort","jamgort2"," ","gort123",10);
        with()
                .body(person)
                .contentType("application/json")
                .post(URI + "person/add")
                .then()
                .assertThat()
                .body(equalTo("Invalid email!"))
                .statusCode(400)
                .log()
                .body();
    }
    @Order(24)
    @Test
    public void addPersonShouldReturnCode400WhenInvalidEmailNotMatchesRegex() {
        Person person = new Person("Gort","jamgort2","invalidEmail","gort123",10);
        with()
                .body(person)
                .contentType("application/json")
                .post(URI + "person/add")
                .then()
                .assertThat()
                .body(equalTo("Invalid email!"))
                .statusCode(400)
                .log()
                .body();
    }
    @Order(25)
    @Test
    public void addPersonShouldReturnCode400WhenInvalidName() {
        Person person = new Person(" ", "jamgort2", "gort2@gmail.com", "gort123", 10);
        with()
                .body(person)
                .contentType("application/json")
                .post(URI + "person/add")
                .then()
                .assertThat()
                .body(equalTo("Invalid name!"))
                .statusCode(400)
                .log()
                .body();
    }
    @Order(26)
    @Test
    public void addPersonShouldReturnCode400WhenInvalidPassword(){
        Person person = new Person("Gort","jamgort2","gort2@gmail.com"," ",10);
        with()
                .body(person)
                .contentType("application/json")
                .post(URI + "person/add")
                .then()
                .assertThat()
                .statusCode(400)
                .body(equalTo("Invalid password!"))
                .log()
                .body();
    }
    @Order(35)
    @Test
    public void deletePersonByEmailShouldReturnCode400WhenInvalidEmail(){
        when()
                .delete(URI + "person/delete-by-email/wrongEmail")
                .then()
                .assertThat()
                .statusCode(400)
                .log()
                .body();
    }
    @Order(36)
    @Test
    public void deletePersonByEmailShouldReturnCode404WhenPersonNotFound(){
        when()
                .delete(URI + "person/delete-by-email/not@gmail.com")
                .then()
                .assertThat()
                .statusCode(404)
                .log()
                .body();
    }
    @Order(38)
    @Test
    public void deletePersonByEmailShouldReturnCode204WhenDeletedPerson(){
        when()
                .delete(URI + "person/delete-by-email/jan@gmail.com")
                .then()
                .assertThat()
                .statusCode(204)
                .log()
                .body();
    }
    @Order(37)
    @Test
    public void deletePersonByIdShouldReturnCode404WhenPersonNotFound(){
        when()
                .delete(URI + "person/delete-by-id/0")
                .then()
                .assertThat()
                .statusCode(404)
                .log()
                .body();
    }
    @Order(39)
    @Test
    public void deletePersonByIdShouldReturnCode204WhenDeletedPerson(){
        when()
                .delete(URI + "person/delete-by-id/2")
                .then()
                .assertThat()
                .statusCode(204)
                .log()
                .body();
    }
    @Order(27)
    @Test
    public void editPersonShouldReturnCode404WhenPersonNotFound(){
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 50);
        person.setId(0L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .assertThat()
                .statusCode(404)
                .log()
                .body();
    }
    @Order(28)
    @Test
    public void editPersonShouldReturnCode400WhenPersonSendWithNotChangedData(){
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 50);
        person.setId(1L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .assertThat()
                .statusCode(400)
                .body(equalTo("Person with exactly same given data already exists!"))
                .log()
                .body();
    }
    @Order(29)
    @Test
    public void editPersonShouldReturnCode400WhenInvalidLogin() {
        Person person = new Person("Jan", " ", "jan@gmail.com", "jan123", 50);
        person.setId(1L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .assertThat()
                .statusCode(400)
                .body(equalTo("Invalid login!"))
                .log()
                .body();
    }
    @Order(30)
    @Test
    public void editPersonShouldReturnCode400WhenInvalidEmail() {
        Person person = new Person("Jan", "jamjan", " ", "jan123", 50);
        person.setId(1L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .assertThat()
                .statusCode(400)
                .body(equalTo("Invalid email!"))
                .log()
                .body();
    }
    @Order(31)
    @Test
    public void editPersonShouldReturnCode400WhenInvalidEmailNotMatchesRegex() {
        Person person = new Person("Jan", "jamjan", "invalidEmail ", "jan123", 50);
        person.setId(1L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .assertThat()
                .statusCode(400)
                .body(equalTo("Invalid email!"))
                .log()
                .body();
    }
    @Order(32)
    @Test
    public void editPersonShouldReturnCode400WhenInvalidName() {
        Person person = new Person(" ", "jamjan", "jan@gmail.com", "jan123", 50);
        person.setId(1L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .assertThat()
                .statusCode(400)
                .body(equalTo("Invalid name!"))
                .log()
                .body();
    }
    @Order(32)
    @Test
    public void editPersonShouldReturnCode400WhenInvalidPassword() {
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", " ", 50);
        person.setId(1L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .assertThat()
                .statusCode(400)
                .body(equalTo("Invalid password!"))
                .log()
                .body();
    }
    @Order(33)
    @Test
    public void editPersonShouldReturnCode400WhenAgeIs0() {
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 0);
        person.setId(1L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .assertThat()
                .statusCode(400)
                .body(equalTo("Invalid age!"))
                .log()
                .body();
    }
    @Order(34)
    @Test
    public void editPersonShouldReturnCode400WhenAgeIsLowerThanPrevious() {
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 40);
        person.setId(1L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .statusCode(400)
                .body(equalTo("Invalid age!"))
                .log()
                .body();
    }
    @Order(34)
    @Test
    public void editPersonShouldReturnCode200WhenEditedPerson(){
        Person person = new Person("Jan", "jamjanek", "jan@gmail.com", "jan123", 50);
        person.setId(1L);
        with()
                .body(person)
                .contentType("application/json")
                .put(URI + "person/edit")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Jan"))
                .body("login", equalTo("jamjanek"))
                .body("email", equalTo("jan@gmail.com"))
                .body("password", equalTo("jan123"))
                .body("age", equalTo(50))
                .log()
                .body();
    }
}
