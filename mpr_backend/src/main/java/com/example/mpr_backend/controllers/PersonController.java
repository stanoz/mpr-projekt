package com.example.mpr_backend.controllers;

import com.example.mpr_backend.dtos.Person;
import com.example.mpr_backend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("person/name/{name}")
    public List<Person> findPersonByName(@PathVariable("name") String name){
        return this.service.getPersonByName(name);
    }
    @GetMapping("person/id/{id}")
    public Person findPersonByID(@PathVariable("id") Long id){
       return this.service.getPersonById(id);
    }
    @GetMapping("person/login/{login}")
    public Person findPersonByLogin(@PathVariable("login") String login){
        return this.service.getPersonByLogin(login);
    }
    @GetMapping("person/email/{email}")
    public Person findPersonByEmail(@PathVariable("email") String email){
        return this.service.getPersonByEmail(email);
    }
    @GetMapping("person/age/{age}")
    public List<Person> findPersonByAge(@PathVariable("age") int age){
        return this.service.getPersonByAge(age);
    }
    @GetMapping("person/getAll")
    public List<Person> getAll(){
        return this.service.getAll();
    }
    @GetMapping ("person/filter/{subName}")
    public List<Person> findPersonBySubName(@PathVariable("subName") String subName){
        return this.service.getAllBySubName(subName);
    }
    @GetMapping("/person/check/{id}")
    public boolean checkIfPersonExists(@PathVariable("id") Long id){
        return this.service.checkPersonExistsById(id);
    }
    @PostMapping("person/add")
    public void addPerson(@RequestBody Person person){
        this.service.addPerson(person);
    }
    @DeleteMapping("person/delete-by-email/{email}")
    public void deletePersonByEmail(@PathVariable("email") String email){
        this.service.deletePersonByEmail(email);
    }
    @DeleteMapping("person/delete-by-id/{id}")
    public void deletePersonById(@PathVariable("id") Long id){
        this.service.deletePersonById(id);
    }
    @PutMapping("person/edit")
    public void editPerson(@RequestBody Person person){
        this.service.editPerson(person);
    }
}
