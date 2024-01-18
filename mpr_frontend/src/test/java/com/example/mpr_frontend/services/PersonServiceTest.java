package com.example.mpr_frontend.services;

import com.example.mpr_frontend.dtos.Person;
import com.example.mpr_frontend.exception_handlers.PersonExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.MockServerRestClientCustomizer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestClientTest
class PersonServiceTest {

    MockServerRestClientCustomizer customizer = new MockServerRestClientCustomizer();
    RestClient.Builder builder = RestClient.builder();
    PersonService personService;
    private final static String BASE_URL = "http://localhost:8080/";
    @BeforeEach
    public void setUp(){
        customizer.customize(builder);
        personService = new PersonService(builder.build());
    }
    @Test
    public void getPersonByIdTest() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/id/1"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        "{\"id\":1," +
                                "\"name\":\"Jan\"," +
                                "\"age\":20," +
                                "\"email\":\"jan2@gmail.com\"," +
                                "\"login\":\"jan2\"," +
                                "\"password\":\"jan2\"}",
                        MediaType.APPLICATION_JSON));
        Person person = personService.getPersonById(1L);
        assertAll(
                () -> assertEquals(1L, person.getId()),
                () -> assertEquals("Jan", person.getName()),
                () -> assertEquals(20, person.getAge()),
                () -> assertEquals("jan2@gmail.com", person.getEmail()),
                () -> assertEquals("jan2", person.getLogin()),
                () -> assertEquals("jan2", person.getPassword())
        );
    }
    @Test
    public void getPersonByIdTestShouldThrowException() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/id/1"))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));
        Assertions.assertThrows(HttpClientErrorException.class, () -> personService.getPersonById(1L));
    }
    @Test
    public void getAllByIdTest(){
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/getAll"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        "["+
                                "{\"id\":1," +
                                "\"name\":\"Jan\"," +
                                "\"login\":\"jamjan\"," +
                                "\"email\":\"jan@gmail.com\"," +
                                "\"password\":\"jan123\"," +
                                "\"age\" : 20}" +
                                "," +
                                "{\"id\":2," +
                                "\"name\":\"Piotr\"," +
                                "\"login\":\"jampiotr\"," +
                                "\"email\":\"piotr@gmail.com\"," +
                                "\"password\":\"piotr123\"," +
                                "\"age\" : 21}" +
                                "]",
                        MediaType.APPLICATION_JSON));
        List<Person> personList = personService.getAll();
        assertAll(
                () -> assertEquals(1L, personList.get(0).getId()),
                () -> assertEquals("Jan", personList.get(0).getName()),
                () -> assertEquals(20, personList.get(0).getAge()),
                () -> assertEquals("jan@gmail.com",personList.get(0).getEmail()),
                () -> assertEquals("jamjan", personList.get(0).getLogin()),
                () -> assertEquals("jan123", personList.get(0).getPassword()),
                () -> assertEquals(2L, personList.get(1).getId()),
                () -> assertEquals("Piotr", personList.get(1).getName()),
                () -> assertEquals(21, personList.get(1).getAge()),
                () -> assertEquals("piotr@gmail.com",personList.get(1).getEmail()),
                () -> assertEquals("jampiotr", personList.get(1).getLogin()),
                () -> assertEquals("piotr123", personList.get(1).getPassword())
        );
    }
    @Test
    public void getAllByIdTestShouldThrowException(){
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/getAll"))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));
        Assertions.assertThrows(HttpClientErrorException.class, () -> personService.getAll());
    }
    @Test
    public void addPersonTest() {
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 20);
        person.setId(1L);
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/add"))
                .andRespond(withSuccess());
        personService.addPerson(person);
    }
    @Test
    public void addPersonTestShouldThrowExceptionNotFound() {
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 20);
        person.setId(1L);
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/add"))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));
        Assertions.assertThrows(HttpClientErrorException.class, () -> personService.addPerson(person));
    }
    @Test
    public void addPersonTestShouldThrowExceptionBadRequest() {
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 20);
        person.setId(1L);
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/add"))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.BAD_REQUEST));
        Assertions.assertThrows(HttpClientErrorException.class, () -> personService.addPerson(person));
    }
    @Test
    public void editPersonTest(){
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 20);
        person.setId(1L);
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/edit"))
                .andRespond(withSuccess());
        personService.editPerson(person);
    }
    @Test
    public void editPersonTestShouldThrowExceptionNotFound() {
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 20);
        person.setId(1L);
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/edit"))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));
        Assertions.assertThrows(HttpClientErrorException.class, () -> personService.editPerson(person));
    }
    @Test
    public void editPersonTestShouldThrowExceptionBadRequest() {
        Person person = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 20);
        person.setId(1L);
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/edit"))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.BAD_REQUEST));
        Assertions.assertThrows(HttpClientErrorException.class, () -> personService.editPerson(person));
    }
    @Test
    public void deletePersonTest(){
        Long id = 1L;
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/delete-by-id/" + id))
                .andRespond(withSuccess());
        personService.deletePersonById(id);
    }
    @Test
    public void deletePersonTestShouldThrowExceptionNotFound(){
        Long id = 1L;
        customizer.getServer().expect(MockRestRequestMatchers.requestTo(BASE_URL + "person/delete-by-id/" + id))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));
        Assertions.assertThrows(HttpClientErrorException.class, () -> personService.deletePersonById(id));
    }
}