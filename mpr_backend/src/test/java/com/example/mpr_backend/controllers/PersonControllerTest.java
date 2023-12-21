//package com.example.mpr_backend.controllers;
//
//import com.example.mpr.dtos.Person;
//import com.example.mpr.dtos.PersonRepository;
//import com.example.mpr.services.PersonService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.hamcrest.MatcherAssert.assertThat;
//@ExtendWith(MockitoExtension.class)
//class PersonControllerTest {
//
//    @InjectMocks
//    private PersonController personController;
//
//    @Mock
//    private PersonService personService;
//    private AutoCloseable openMocks;
//    @BeforeEach
//    void setUp() {
//        openMocks = MockitoAnnotations.openMocks(this);
//        personController = new PersonController(personService);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        openMocks.close();
//    }
//
//    @Test
//    void findPersonByNameShouldReturnListOfPersonsWithGivenName() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        Person person2 = new Person("Adam","jamadam2","adam@gmail.com","adam123",45);
//        List<Person> personList = List.of(person, person2);
//        when(personService.getPersonByName("Adam")).thenReturn(personList);
//        //when
//        List<Person> resultPersonList = personController.findPersonByName("Adam");
//        //then
//        assertThat(resultPersonList, hasSize(2));
//    }
//
//    @Test
//    void findPersonByIDShouldReturnPersonWithGivenId() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        person.setId(1L);
//        when(personService.getPersonById(1L)).thenReturn(person);
//        //when
//        Person personResult = personController.findPersonByID(1L);
//        //then
//        assertEquals(person, personResult);
//    }
//
//    @Test
//    void findPersonByLoginShouldReturnPersonWithGivenLogin() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        when(personService.getPersonByLogin("jamadam")).thenReturn(person);
//        //when
//        Person personResult = personController.findPersonByLogin("jamadam");
//        //then
//        assertEquals(person, personResult);
//    }
//
//    @Test
//    void findPersonByEmailShouldReturnPersonWithGivenEmail() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        when(personService.getPersonByEmail("adam@gmail.com")).thenReturn(person);
//        //when
//        Person personResult = personController.findPersonByEmail("adam@gmail.com");
//        //then
//        assertEquals(person, personResult);
//    }
//
//    @Test
//    void findPersonByAgeShouldReturnListOfPersonsWithGivenAge() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        Person person2 = new Person("Adam","jamadam2","adam@gmail.com","adam123",45);
//        List<Person> personList = List.of(person, person2);
//        when(personService.getPersonByAge(45)).thenReturn(personList);
//        //when
//        List<Person> resultpersonList = personController.findPersonByAge(45);
//        //then
//        assertThat(resultpersonList, hasSize(2));
//    }
//
//    @Test
//    void getAllShouldReturnListOfAllPersons() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        Person person2 = new Person("Adam","jamadam2","adam@gmail.com","adam123",45);
//        List<Person> personList = List.of(person, person2);
//        when(personService.getAll()).thenReturn(personList);
//        //when
//        List<Person> personResultList = personController.getAll();
//        //then
//        assertAll(
//                () -> assertThat(personResultList, hasSize(2)),
//                () -> assertEquals(person, personResultList.get(0)),
//                () -> assertEquals(person2, personResultList.get(1))
//        );
//    }
//
//    @Test
//    void addPersonShouldAddPerson() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",40);
//        //when
//        personController.addPerson(person);
//        //then
//        verify(personService).addPerson(person);
//    }
//
//    @Test
//    void deletePersonByEmailShouldDeletePersonWithGivenEmail() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        //when
//        personController.deletePersonByEmail("adam@gmail.com");
//        //then
//        verify(personService).deletePersonByEmail("adam@gmail.com");
//    }
//
//    @Test
//    void deletePersonByIdShouldDeletePersonWithGivenId() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        person.setId(1L);
//        //when
//        personController.deletePersonById(1L);
//        //then
//        verify(personService).deletePersonById(1L);
//    }
//
//    @Test
//    void editPersonShouldEditPerson() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        //when
//        personController.editPerson(person);
//        //then
//        verify(personService).editPerson(person);
//    }
//}