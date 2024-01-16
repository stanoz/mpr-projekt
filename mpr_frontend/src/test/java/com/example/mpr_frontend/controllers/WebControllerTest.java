package com.example.mpr_frontend.controllers;

import com.example.mpr_frontend.dtos.Person;
import com.example.mpr_frontend.services.PersonService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
//        Person person1 = new Person("Adam", "jamadam", "adam@gmail.com", "adam123", 40);
//        Person person2 = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 41);
//        List<Person> persons = new ArrayList<>();
//        persons.add(person1);
//        persons.add(person2);
//
//        when(personService.getAll()).thenReturn(persons);

        //when
        String viewName = webController.getIndexView(model);

        //then
//        verify(personService, times(1)).getAll();
//        verify(model, times(1)).addAttribute("persons", persons);
        assertEquals("index", viewName);
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
    }
    @Order(4)
    @Test
    void deletePersonShouldPerformDeletePerson(){
        Person personToDelete = new Person("Adam", "jamadam", "adam@gmail.com", "adam1234", 40);
        webController.deletePerson(personToDelete, model, 3L);
    }
}
