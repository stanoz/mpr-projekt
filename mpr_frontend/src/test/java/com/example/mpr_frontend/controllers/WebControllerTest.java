package com.example.mpr_frontend.controllers;

import com.example.mpr_frontend.dtos.Person;
import com.example.mpr_frontend.services.PersonService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import org.mockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class WebControllerTest {

    @Mock
    private PersonService personService;

    @Mock
    private Model model;

    @InjectMocks
    private WebController webController;

    @Order(1)
    @Test
    void getIndexViewShouldReturnIndex() {
        //given
        Person person1 = new Person("Adam", "jamadam", "adam@gmail.com", "adam123", 40);
        Person person2 = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 41);
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        when(personService.getAll()).thenReturn(personList);

        //when
        String viewName = webController.getIndexView(model);
        //then
        verify(personService).getAll();
        assertEquals("index", viewName);
        verify(model).addAttribute("persons", personList);
    }
    @Test
    public void getDeletePersonManagerTest(){
        //given
        Person person = new Person("Adam", "jamadam", "adam@gmail.com", "adam123", 40);
        person.setId(1L);
        when(personService.getPersonById(1L)).thenReturn(person);

        //when
        String viewName = webController.getDeletePersonManager(model,1L);
        //then
        verify(personService).getPersonById(1L);
        assertEquals("deletePerson",viewName);
        verify(model).addAttribute("person",person);
    }
    @Test
    public void getEditPersonManager(){
        //given
        Person person = new Person("Adam", "jamadam", "adam@gmail.com", "adam123", 40);
        person.setId(1L);
        when(personService.getPersonById(1L)).thenReturn(person);

        //when
        String viewName = webController.getEditPersonManager(model,1L);

        //given
        verify(personService).getPersonById(1L);
        assertEquals("editPerson",viewName);
        verify(model).addAttribute("person",person);
    }
    @Order(2)
    @Test
    void addPersonShouldPerformAddPerson(){
        Person personToAdd = new Person("Adam", "jamadam", "adam@gmail.com", "adam123", 40);
        webController.addPerson(personToAdd, model);

        verify(personService).addPerson(personToAdd);

    }
    @Order(3)
    @Test
    void editPersonShouldPerformEditPerson(){
        Person personToEdit = new Person("Adam", "jamadam", "adam@gmail.com", "adam1234", 40);
        webController.editPerson(personToEdit, model, 3L);
        verify(personService).editPerson(personToEdit);
    }
    @Order(4)
    @Test
    void deletePersonShouldPerformDeletePerson(){
        Person personToDelete = new Person("Adam", "jamadam", "adam@gmail.com", "adam1234", 40);
        webController.deletePerson(personToDelete, model, 3L);
        verify(personService).deletePersonById(personToDelete.getId());
    }
}
