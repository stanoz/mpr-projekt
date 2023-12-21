package com.example.mpr_backend.controllers;

import com.example.mpr.dtos.Person;
import com.example.mpr.exception_handlers.PersonExceptionHandler;
import com.example.mpr.exceptions.*;
import com.example.mpr.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @BeforeEach
    public void setUp(){
        personController = new PersonController(personService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PersonExceptionHandler(), personController).build();
    }
    @Test
     void findPersonByNameShouldReturnCheck200() throws Exception {
        //given
        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
        person.setId(1L);
        Person person2 = new Person("Adam","jamadam2","adam2@gmail.com","adam123",40);
        person2.setId(2L);
        List<Person> personList = List.of(person, person2);
        when(personService.getPersonByName("Adam")).thenReturn(personList);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/name/Adam"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Adam"))
                .andExpect(jsonPath("$[0].login").value("jamadam"))
                .andExpect(jsonPath("$[0].email").value("adam@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("adam123"))
                .andExpect(jsonPath("$[0].age").value(45))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Adam"))
                .andExpect(jsonPath("$[1].login").value("jamadam2"))
                .andExpect(jsonPath("$[1].email").value("adam2@gmail.com"))
                .andExpect(jsonPath("$[1].password").value("adam123"))
                .andExpect(jsonPath("$[1].age").value(40))
                .andExpect(status().isOk());
    }
    @Test
    void findPersonByNameShouldReturnCheck400() throws Exception {
        //given
        when(personService.getPersonByName(" ")).thenThrow(new InvalidPersonNameException("Invalid name!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/name/ "))
                .andExpect(status().isBadRequest())
                .andExpect(exception -> assertTrue(exception.getResolvedException() instanceof InvalidPersonNameException))
                .andExpect(exception -> assertEquals("Invalid name!",
                        Objects.requireNonNull(exception.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonByNameShouldReturnCheck404() throws Exception {
        //given
        when(personService.getPersonByName("Adam")).thenThrow(new PersonNotFoundException("Person not found!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/name/Adam"))
                .andExpect(status().isNotFound())
                .andExpect(exception -> assertTrue(exception.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(exception -> assertEquals("Person not found!",
                        Objects.requireNonNull(exception.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonByIdShouldReturnCheck200() throws Exception {
        //given
        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
        person.setId(1L);
        when(personService.getPersonById(1L)).thenReturn(person);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/id/1"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Adam"))
                .andExpect(jsonPath("$.login").value("jamadam"))
                .andExpect(jsonPath("$.email").value("adam@gmail.com"))
                .andExpect(jsonPath("$.password").value("adam123"))
                .andExpect(jsonPath("$.age").value(45))
                .andExpect(status().isOk());
    }
    @Test
    void findPersonByIdShouldReturnCheck404() throws Exception {
        //given
        when(personService.getPersonById(1L)).thenThrow(new PersonNotFoundException("Person not found!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/id/1"))
                .andExpect(status().isNotFound())
                .andExpect(exception -> assertTrue(exception.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(exception -> assertEquals("Person not found!",
                        Objects.requireNonNull(exception.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonByLoginShouldReturnCheck200() throws Exception {
        //given
        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
        when(personService.getPersonByLogin("jamadam")).thenReturn(person);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/login/jamadam"))
                .andExpect(jsonPath("$.name").value("Adam"))
                .andExpect(jsonPath("$.login").value("jamadam"))
                .andExpect(jsonPath("$.email").value("adam@gmail.com"))
                .andExpect(jsonPath("$.password").value("adam123"))
                .andExpect(jsonPath("$.age").value(45))
                .andExpect(status().isOk());
    }
    @Test
    void findPersonByLoginShouldReturnCheck400WhenLoginIsBlank() throws Exception{
        //given
        when(personService.getPersonByLogin(" ")).thenThrow(new InvalidPersonLoginException("Invalid login!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/login/ "))
                .andExpect(status().isBadRequest())
                .andExpect(exception -> assertTrue(exception.getResolvedException() instanceof InvalidPersonLoginException))
                .andExpect(exception -> assertEquals("Invalid login!",
                        Objects.requireNonNull(exception.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonByLoginShouldReturnCheck404() throws Exception{
        //given
        when(personService.getPersonByLogin("jamadam")).thenThrow(new PersonNotFoundException("Person not found!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/login/jamadam"))
                .andExpect(status().isNotFound())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(e -> assertEquals("Person not found!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonByEmailShouldReturnCheck200() throws Exception {
        //given
        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
        when(personService.getPersonByEmail("adam@gmail.com")).thenReturn(person);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/email/adam@gmail.com"))
                .andExpect(jsonPath("$.name").value("Adam"))
                .andExpect(jsonPath("$.login").value("jamadam"))
                .andExpect(jsonPath("$.email").value("adam@gmail.com"))
                .andExpect(jsonPath("$.password").value("adam123"))
                .andExpect(jsonPath("$.age").value(45))
                .andExpect(status().isOk());
    }
    @Test
    void findPersonByEmailShouldReturnCheck400WhenEmailIsBlank() throws Exception{
        //given
        when(personService.getPersonByEmail(" ")).thenThrow(new InvalidPersonEmailException("Invalid email!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/email/ "))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonEmailException))
                .andExpect(e -> assertEquals("Invalid email!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonByEmailShouldReturnCheck404() throws Exception{
        //given
        when(personService.getPersonByEmail("adam@gmail.com")).thenThrow(new PersonNotFoundException("Person not found!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/email/adam@gmail.com"))
                .andExpect(status().isNotFound())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(e -> assertEquals("Person not found!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonByAgeShouldReturnCheck200() throws Exception{
        //given
        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
        person.setId(1L);
        Person person2 = new Person("Jan","jamjan","jan@gmail.com","jan123",45);
        person2.setId(2L);
        List<Person> personList = List.of(person, person2);
        when(personService.getPersonByAge(45)).thenReturn(personList);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/age/45"))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Adam"))
                .andExpect(jsonPath("$[0].login").value("jamadam"))
                .andExpect(jsonPath("$[0].email").value("adam@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("adam123"))
                .andExpect(jsonPath("$[0].age").value(45))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Jan"))
                .andExpect(jsonPath("$[1].login").value("jamjan"))
                .andExpect(jsonPath("$[1].email").value("jan@gmail.com"))
                .andExpect(jsonPath("$[1].password").value("jan123"))
                .andExpect(jsonPath("$[1].age").value(45))
                .andExpect(status().isOk());
    }
    @Test
    void findPersonByAgeShouldReturnCheck400WhenAgeIsInvalid() throws Exception{
        //given
        when(personService.getPersonByAge(0)).thenThrow(new InvalidPersonAgeException("Invalid age!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/age/0"))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonAgeException))
                .andExpect(e -> assertEquals("Invalid age!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonByAgeShouldReturnCheck404() throws Exception{
        //given
        when(personService.getPersonByAge(50)).thenThrow(new PersonNotFoundException("Person not found!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/age/50"))
                .andExpect(status().isNotFound())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(e -> assertEquals("Person not found!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonBySubNameShouldReturnCheck400WhenSubNameIsEmpty() throws Exception {
        //given
        when(personService.getAllBySubName(" ")).thenThrow(new InvalidPersonNameException("Invalid name!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/filter/ "))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonNameException))
                .andExpect(e -> assertEquals("Invalid name!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void findPersonBySubNameShouldReturnCheck404WhenNoPersonInDatabaseFound() throws Exception {
        //given
        when(personService.getAllBySubName("exception")).thenThrow(new PersonNotFoundException("List of Person is empty!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/filter/exception"))
                .andExpect(status().isNotFound())
                .andExpect(exception -> assertTrue(exception.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(exception -> assertEquals("List of Person is empty!", exception.getResolvedException().getMessage()));
    }
    @Test
    void findPersonBySubNameShouldReturnCheck404WhenNoPersonMatchesSubName() throws Exception {
        //given
        when(personService.getAllBySubName("exception")).thenThrow(new PersonNotFoundException("Not found Person with given sub name!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/filter/exception"))
                .andExpect(status().isNotFound())
                .andExpect(exception -> assertTrue(exception.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(exception -> assertEquals("Not found Person with given sub name!",
                        Objects.requireNonNull(exception.getResolvedException()).getMessage()));
    }
    @Test
    void getAllBySubNameShouldReturnCheck200() throws Exception{
        //given
        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
        person.setId(1L);
        Person person2 = new Person("Jan","jamjan","jan@gmail.com","jan123",45);
        person2.setId(2L);
        List<Person> personList = List.of(person, person2);
        when(personService.getAllBySubName("a")).thenReturn(personList);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/filter/a"))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Adam"))
                .andExpect(jsonPath("$[0].login").value("jamadam"))
                .andExpect(jsonPath("$[0].email").value("adam@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("adam123"))
                .andExpect(jsonPath("$[0].age").value(45))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Jan"))
                .andExpect(jsonPath("$[1].login").value("jamjan"))
                .andExpect(jsonPath("$[1].email").value("jan@gmail.com"))
                .andExpect(jsonPath("$[1].password").value("jan123"))
                .andExpect(jsonPath("$[1].age").value(45))
                .andExpect(status().isOk());
    }
    @Test
    void getAllShouldReturnCheck200() throws Exception{
        //given
        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
        person.setId(1L);
        Person person2 = new Person("Jan","jamjan","jan@gmail.com","jan123",45);
        person2.setId(2L);
        List<Person> personList = List.of(person, person2);
        when(personService.getAll()).thenReturn(personList);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/getAll"))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Adam"))
                .andExpect(jsonPath("$[0].login").value("jamadam"))
                .andExpect(jsonPath("$[0].email").value("adam@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("adam123"))
                .andExpect(jsonPath("$[0].age").value(45))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Jan"))
                .andExpect(jsonPath("$[1].login").value("jamjan"))
                .andExpect(jsonPath("$[1].email").value("jan@gmail.com"))
                .andExpect(jsonPath("$[1].password").value("jan123"))
                .andExpect(jsonPath("$[1].age").value(45))
                .andExpect(status().isOk());
    }
    @Test
    void getAllShouldReturnCheck404() throws Exception{
        //given
        when(personService.getAll()).thenThrow(new PersonNotFoundException("Person not found!"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/getAll"))
                .andExpect(status().isNotFound())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(e -> assertEquals("Person not found!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void addPersonShouldReturnCheck400WhenGivenEmailAlreadyExist() throws Exception {
        //given
        doThrow(new PersonAlreadyExistException("This email is taken!")).when(personService).addPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(("/person/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\" : 45}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonAlreadyExistException))
                .andExpect(e -> assertEquals("This email is taken!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void addPersonShouldReturnCheck400WhenGivenLoginAlreadyExist() throws Exception {
        //given
        doThrow(new PersonAlreadyExistException("This login is taken!")).when(personService).addPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(("/person/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\" : 45}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonAlreadyExistException))
                .andExpect(e -> assertEquals("This login is taken!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void addPersonShouldReturnCheck400WhenGivenAgeIsInvalid() throws Exception {
        //given
        doThrow(new InvalidPersonAgeException("Invalid age!")).when(personService).addPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(("/person/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\" : 0}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonAgeException))
                .andExpect(e -> assertEquals("Invalid age!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void addPersonShouldReturnCheck400WhenGivenNameIsInvalid() throws Exception {
        //given
        doThrow(new InvalidPersonNameException("Invalid name!")).when(personService).addPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(("/person/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \" \"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\" : 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonNameException))
                .andExpect(e -> assertEquals("Invalid name!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void addPersonShouldReturnCheck400WhenGivenEmailIsInvalid() throws Exception {
        //given
        doThrow(new InvalidPersonEmailException("Invalid email!")).when(personService).addPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(("/person/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \" \"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\" : 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void addPersonShouldReturnCheck400WhenGivenLoginIsInvalid() throws Exception {
        //given
        doThrow(new InvalidPersonLoginException("Invalid login!")).when(personService).addPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(("/person/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"Adam\"," +
                        "\"login\" : \" \"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\" : 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonLoginException))
                .andExpect(e -> assertEquals("Invalid login!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void addPersonShouldReturnCheck400WhenPasswordLoginIsInvalid() throws Exception {
        //given
        doThrow(new InvalidPersonPasswordException("Invalid password!")).when(personService).addPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(("/person/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \" \"," +
                        "\"age\" : 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonPasswordException))
                .andExpect(e -> assertEquals("Invalid password!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void addPersonShouldReturnCheck200() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(("/person/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\" : 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void deletePersonByEmailShouldReturnCheck404WhenPersonNotFound() throws Exception{
        //given
        doThrow(new PersonNotFoundException("Person not found!")).when(personService).deletePersonByEmail("email@notexist.com");
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete-by-email/email@notexist.com"))
                .andExpect(status().isNotFound())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(e -> assertEquals("Person not found!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void deletePersonByEmailShouldReturnCheck400WhenGivenEmailIsInvalid() throws Exception{
        //given
        doThrow(new InvalidPersonEmailException("Invalid email!")).when(personService).deletePersonByEmail(" ");
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete-by-email/ "))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonEmailException))
                .andExpect(e -> assertEquals("Invalid email!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void deletePersonByEmailShouldReturnCheck200() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete-by-email/email@exist.com"))
                .andExpect(status().isOk());
    }
    @Test
    void deletePersonByIdShouldReturnCheck404WhenPersonNotFound() throws Exception{
        //given
        doThrow(new PersonNotFoundException("Person not found!")).when(personService).deletePersonById(1L);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete-by-id/1"))
                .andExpect(status().isNotFound())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(e -> assertEquals("Person not found!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void deletePersonByIdShouldReturnCheck200() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete-by-id/1"))
                .andExpect(status().isOk());
    }
    @Test
    void editPersonShouldReturnCheck404WhenPersonNotFound() throws Exception{
        //given
        doThrow(new PersonNotFoundException("Person not found!")).when(personService).editPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put(("/person/edit"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1," +
                        "\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\": \"adam123\"," +
                        "\"age\" :  40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(e -> assertEquals("Person not found!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void editPersonShouldReturnCheck400WhenPersonAlreadyExist() throws Exception{
        //given
        doThrow(new PersonAlreadyExistException("Person with exactly same given data already exists!")).when(personService).editPerson(any(Person.class));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/person/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1," +
                        "\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\": \"adam123\"," +
                        "\"age\" :  40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof PersonAlreadyExistException))
                .andExpect(e -> assertEquals("Person with exactly same given data already exists!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void editPersonShouldReturnCheck400WhenGivenAgeIsInvalid() throws Exception{
        //given
        doThrow(new InvalidPersonAgeException("Invalid age!")).when(personService).editPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put(("/person/edit"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1," +
                        "\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\": 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonAgeException))
                .andExpect(e -> assertEquals("Invalid age!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void editPersonShouldReturnCheck400WhenGivenLoginIsInvalid() throws Exception{
        //given
        doThrow(new InvalidPersonLoginException("Invalid login!")).when(personService).editPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put(("/person/edit"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1," +
                        "\"name\" : \"Adam\"," +
                        "\"login\" : \" \"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\": 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonLoginException))
                .andExpect(e -> assertEquals("Invalid login!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void editPersonShouldReturnCheck400WhenGivenEmailIsInvalid() throws Exception{
        //given
        doThrow(new InvalidPersonEmailException("Invalid email!")).when(personService).editPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put(("/person/edit"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1," +
                        "\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \" \"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\": 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonEmailException))
                .andExpect(e -> assertEquals("Invalid email!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void editPersonShouldReturnCheck400WhenGivenNameIsInvalid() throws Exception{
        //given
        doThrow(new InvalidPersonNameException("Invalid name!")).when(personService).editPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put(("/person/edit"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1," +
                        "\"name\" : \" \"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\": 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonNameException))
                .andExpect(e -> assertEquals("Invalid name!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void editPersonShouldReturnCheck400WhenPasswordNameIsInvalid() throws Exception{
        //given
        doThrow(new InvalidPersonPasswordException("Invalid password!")).when(personService).editPerson(any());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put(("/person/edit"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1," +
                        "\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \" \"," +
                        "\"age\": 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(e -> assertTrue(e.getResolvedException() instanceof InvalidPersonPasswordException))
                .andExpect(e -> assertEquals("Invalid password!",
                        Objects.requireNonNull(e.getResolvedException()).getMessage()));
    }
    @Test
    void editPersonShouldReturnCheck200() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put(("/person/edit"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1," +
                        "\"name\" : \"Adam\"," +
                        "\"login\" : \"jamadam\"," +
                        "\"email\" : \"adam@gmail.com\"," +
                        "\"password\" : \"adam123\"," +
                        "\"age\" : 40}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
