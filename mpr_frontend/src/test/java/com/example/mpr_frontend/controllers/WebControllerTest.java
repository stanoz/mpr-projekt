package com.example.mpr_frontend.controllers;

import com.example.mpr_frontend.dtos.Person;
import com.example.mpr_frontend.services.PersonService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WebControllerTest {

    @Mock
    private PersonService personService;

    @Mock
    private Model model;

    @InjectMocks
    private WebController webController;

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
    public void getEditPersonManagerTest(){
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
    @Test
    void deletePersonShouldPerformDeletePerson(){
        //given
        Person personToDelete = new Person("Adam", "jamadam", "adam@gmail.com", "adam1234", 40);
        //when
        String viewName = webController.deletePerson(personToDelete, model, 3L);
       //then
        verify(personService).deletePersonById(personToDelete.getId());
        assertEquals("redirect:/showAll", viewName);
    }
    @Test
    void editPersonShouldPerformEditPerson(){
        //given
        Person personToEdit = new Person("Adam", "jamadam", "adam@gmail.com", "adam1234", 40);

        //when
        String viewName = webController.editPerson(personToEdit, model, 3L);

        //then
        verify(personService).editPerson(personToEdit);
        assertEquals("redirect:/showAll", viewName);
    }
    @Test
    void getAddPersonManagerTest(){
        //given
        //when
        String viewName = webController.getAddPersonManager(model);

        //then
        assertEquals("addPerson", viewName);
        verify(model).addAttribute("person", new Person());
    }
    @Test
    void addPersonShouldPerformAddPerson(){
        //given
        Person personToAdd = new Person("Adam", "jamadam", "adam@gmail.com", "adam123", 40);

        //when
        String viewName = webController.addPerson(personToAdd, model);

        //then
        verify(personService).addPerson(personToAdd);
        assertEquals("redirect:/showAll", viewName);
    }
    @Test
    void getWelcomeViewTest(){
        //given
        //when
        String viewName = webController.getWelcomeView();

        //then
        assertEquals("welcome", viewName);
    }
}
