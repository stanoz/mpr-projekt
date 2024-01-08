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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private static final String BASE_URL = "http://localhost:8080/";

//    private MockMvc mockMvc;

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
//        this.mockMvc = MockMvcBuilders.standaloneSetup(new PersonExceptionHandler(), personService).build();
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

        //when
        Person actualPerson = personService.getPersonById(expectedPerson.getId());

        //then
        assertEquals(expectedPerson, actualPerson);
    }
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
    void addPersonShouldPerformAddNewPerson() throws Exception {
        //given
        Person person = new Person("Gort","jamgort","gort@gmail.com","gort123",20);

        //when
        //personService.addPerson(person);
        //given
//        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "person/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\" : \"Gort\"," +
//                        "\"login\" : \"jamgort\"," +
//                        "\"email\" : \"gort@gmail.com\"," +
//                        "\"password\" : \"gort123\"," +
//                        "\"age\" : 45}")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
    }
    @Order(4)
    @Test
    void editPersonShouldPerformEditPerson(){
        //given
        Person person = new Person("Gort","jamgort2","gort2@gmail.com","gort123",60);
        person.setId(1L);
        //when
        personService.editPerson(person);
        //given
    }
    @Order(5)
    @Test
    void deletePersonShouldPerformDeletePerson(){
        //given
        //when
        personService.deletePersonById(1L);
        //then
    }
}