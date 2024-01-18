package com.example.mpr_backend.controllers;

import com.example.mpr_backend.dtos.Person;
import com.example.mpr_backend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("person/name/{name}")
    public ResponseEntity<List<Person>> findPersonByName(@PathVariable("name") String name){
        List<Person>personEntityList = this.service.getPersonByName(name);
        return ResponseEntity.ok().body(personEntityList);
    }
    @GetMapping("person/id/{id}")
    public ResponseEntity<Person> findPersonByID(@PathVariable("id") Long id){
        Person personEntity = this.service.getPersonById(id);
        return ResponseEntity.ok().body(personEntity);
    }
    @GetMapping("person/login/{login}")
    public ResponseEntity<Person> findPersonByLogin(@PathVariable("login") String login){
        Person personEntity = this.service.getPersonByLogin(login);
        return ResponseEntity.ok().body(personEntity);
    }
    @GetMapping("person/email/{email}")
    public ResponseEntity<Person> findPersonByEmail(@PathVariable("email") String email){
        Person personEntity = this.service.getPersonByEmail(email);
        return ResponseEntity.ok().body(personEntity);
    }
    @GetMapping("person/age/{age}")
    public ResponseEntity<List<Person>> findPersonByAge(@PathVariable("age") int age){
        List<Person>personEntityList = this.service.getPersonByAge(age);
        return ResponseEntity.ok().body(personEntityList);
    }
    @GetMapping("person/getAll")
    public ResponseEntity<List<Person>> getAll(){
        List<Person>personEntityList = this.service.getAll();
        return ResponseEntity.ok().body(personEntityList);
    }
    @GetMapping ("person/filter/{subName}")
    public ResponseEntity<List<Person>> findPersonBySubName(@PathVariable("subName") String subName){
        List<Person>personEntityList = this.service.getAllBySubName(subName);
        return ResponseEntity.ok().body(personEntityList);
    }
    @GetMapping("/person/check/{id}")
    public ResponseEntity<Boolean> checkIfPersonExists(@PathVariable("id") Long id){
        boolean exist = this.service.checkPersonExistsById(id);
        return ResponseEntity.ok().body(exist);
    }
    @PostMapping("person/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        Person personEntity = this.service.addPerson(person);
        return ResponseEntity.accepted().body(personEntity);
    }
    @DeleteMapping("person/delete-by-email/{email}")
    public ResponseEntity<Void> deletePersonByEmail(@PathVariable("email") String email){
        this.service.deletePersonByEmail(email);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("person/delete-by-id/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable("id") Long id){
        this.service.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("person/edit")
    public ResponseEntity<Person> editPerson(@RequestBody Person person){
        Person personEntity = this.service.editPerson(person);
        return ResponseEntity.ok().body(personEntity);
    }
}
