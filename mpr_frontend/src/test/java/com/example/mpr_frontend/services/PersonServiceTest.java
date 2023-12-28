package com.example.mpr_frontend.services;

import com.example.mpr_frontend.dtos.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private static final String BASE_URL = "http://localhost:8080/";

    @InjectMocks
    private PersonService personService;

    @Mock
    private RestClient restClient = RestClient.create();

    private AutoCloseable openMocks;
    @Captor
    private ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);

    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Order(1)
    @Test
    void getPersonByIdShouldReturnPersonWithGivenId() {
        //given
        Person expectedPerson = new Person("Jan","jamjan","jan@gmail.com","jan123",50);
        expectedPerson.setId(1L);
//        when(restTemplate.getForObject(anyString(), eq(Person.class))).thenReturn(expectedPerson);
//        when(restTemplate.getForObject(BASE_URL + "person/id/" + expectedPerson.getId(),Person.class)).thenReturn(expectedPerson);

        //when
        Person actualPerson = personService.getPersonById(expectedPerson.getId());

        //then
//        verify(restTemplate, times(1)).getForObject(anyString(), eq(Person.class));
        assertEquals(expectedPerson, actualPerson);
    }
//    nie_dziala
//    @Test
//    void testGetPersonById1() {
//        //given
//        Person expectedPerson = new Person("Gort","jamgort","gort@gmail.com","gort123",20);
//        expectedPerson.setId(10L);
//        when(restClient.get()
//                .uri(BASE_URL + "person/id/" + any())
//                .retrieve()
//                .body(Person.class)).thenReturn(expectedPerson);
//
//        //when
//        Person actualPerson = personService.getPersonById(10L);
//
//        //then
////        verify(restClient, times(1)).get().uri(anyString()).retrieve().bodyToMono(Person.class);
//        assertEquals(expectedPerson, actualPerson);
//    }
    @Order(2)
    @Test
    void getAllShouldReturnAllPersons(){
        //given
        Person person1 = new Person("Jan","jamjan","jan@gmail.com","jan123",50);
        person1.setId(1L);
        Person person2 = new Person("Piotr","jampiotr","piotr@gmail.com","piotr123",35);
        person2.setId(2L);

        //when
        List<Person> personList = personService.getAll();

        //then
        assertAll(
                () -> assertThat(hasSize(2).matches(personList)),
                () -> assertEquals(person1, personList.get(0)),
                () -> assertEquals(person2, personList.get(1))
        );
    }
    @Order(3)
    @Test
    void addPersonShouldPerformAddNewPerson(){
        //given
        Person person = new Person("Gort","jamgort","gort@gmail.com","gort123",20);
//        when(restClient.post()
//                .uri(BASE_URL + "person/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(captor.capture())
//                .retrieve()
//                .toBodilessEntity());
        //when
        personService.addPerson(person);
        //given
//        assertEquals(person,captor.getValue());
    }
    @Order(3)
    @Test
    void editPersonShouldPerformEditPerson(){
        //given
        Person person = new Person("Gort","jamgort2","gort2@gmail.com","gort123",60);
        person.setId(1L);
        //when
        personService.editPerson(person);
        //given
    }
    @Order(4)
    @Test
    void deletePersonShouldPerformDeletePerson(){
        //given
        //when
        personService.deletePersonById(1L);
        //then
    }
}