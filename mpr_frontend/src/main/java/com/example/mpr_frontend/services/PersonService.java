package com.example.mpr_frontend.services;

import com.example.mpr_frontend.dtos.Person;
import com.example.mpr_frontend.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final RestClient restClient;
    private static final String BASE_URL = "http://localhost:8080/";
    public PersonService() {
        this.restClient = RestClient.create();
    }
//    public List<Person> getPersonByName(String name){
//        if (name.isBlank()){
//            throw new InvalidPersonNameException("Invalid name!");
//        }
//        List<Person> repoPersonList = this.repository.findAllByName(name);
//        if (repoPersonList.isEmpty()){
//            throw new PersonNotFoundException("Person not found!");
//        }
//        return repoPersonList;
//    }
    public Person getPersonById(Long id){
        return restClient.get()
                .uri(BASE_URL + "person/id/" + id)
                .retrieve()
                .body(Person.class);
    }
//    public Person getPersonByEmail(String email){
//        if (email.isBlank()){
//            throw new InvalidPersonEmailException("Invalid email!");
//        }
//        if (!this.repository.existsByEmail(email)) {
//            throw new PersonNotFoundException("Person not found!");
//        }
//        return this.repository.findByEmail(email);
//    }
//    public Person getPersonByLogin(String login){
//        if (login.isBlank()){
//            throw new InvalidPersonLoginException("Invalid login!");
//        }
//        if (!this.repository.existsByLogin(login)) {
//            throw new PersonNotFoundException("Person not found!");
//        }
//        return this.repository.findByLogin(login);
//    }
//    public List<Person> getPersonByAge(int age){
//        if (age <= 0){
//            throw new InvalidPersonAgeException("Invalid age!");
//        }
//        List<Person> repoPersonsList = this.repository.findAllByAge(age);
//        if (repoPersonsList.isEmpty()){
//            throw new PersonNotFoundException("Person not found!");
//        }
//        return repoPersonsList;
//    }
    public List<Person> getAll(){
        List<Person> repoPersonList = restClient
                .get()
                .uri(BASE_URL + "person/getAll")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (repoPersonList.isEmpty()){
            throw new PersonNotFoundException("Person not found!");
        }
        return repoPersonList;
    }
//    public List<Person> getAllBySubName(String subName){
//        if (subName.isBlank()){
//            throw new InvalidPersonNameException("Invalid name!");
//        }
//        List<Person> repoPersonList = this.repository.findAll();
//        if (repoPersonList.isEmpty()){
//            throw new PersonNotFoundException("List of Person is empty!");
//        }
//        List<Person> resultPersonList = new ArrayList<>();
//        for (Person repoPerson : repoPersonList) {
//            if (repoPerson.getName().contains(subName)){
//                resultPersonList.add(repoPerson);
//            }
//        }
//        if (resultPersonList.isEmpty()){
//            throw new PersonNotFoundException("Not found Person with given sub name!");
//        }
//        return repoPersonList;
//    }
    public void addPerson(Person person){
            if (person.getAge() <= 0){
                throw new InvalidPersonAgeException("Invalid age!");
            }
            if (person.getName().isBlank()){
                throw new InvalidPersonNameException("Invalid name!");
            }
            if (person.getEmail().isBlank()){
                throw new InvalidPersonEmailException("Invalid email!");
            }
            if (person.getLogin().isBlank()){
                throw new InvalidPersonLoginException("Invalid login!");
            }
            if (person.getPassword().isBlank()){
                throw new InvalidPersonPasswordException("Invalid password!");
            }
            this.restClient.post()
                    .uri(BASE_URL + "person/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(person)
                    .retrieve()
                    .toBodilessEntity();
    }
//    public void deletePersonByEmail(String email){
//        if (email.isBlank()){
//            throw new InvalidPersonEmailException("Invalid email!");
//        }
//        boolean personExist = this.repository.existsByEmail(email);
//        if (!personExist) {
//            throw new PersonNotFoundException("Person does not exist!");
//        }
//        this.repository.deleteByEmail(email);
//    }
    public void deletePersonById(Long id){
        boolean personExist = Boolean.TRUE.equals(restClient
                .get()
                .uri(BASE_URL + "/person/check/" + id)
                .retrieve()
                .body(boolean.class));
        if (!personExist) {
            throw new PersonNotFoundException("Person does not exist!");
        }
        this.restClient.delete()
                .uri(BASE_URL + "person/delete-by-id/" + id)
                .retrieve();
    }
    public void editPerson(Person person){
//        boolean personExist = this.repository.existsById(person.getId());
//        if (personExist){
//            Person repoPerson = repository.findById(person.getId())
//                    .orElseThrow(() -> new PersonNotFoundException("Person with given id not found!"));
//            if (repoPerson.equals(person)){
//                throw new PersonAlreadyExistException("Person with exactly same given data already exists!");
//            }
//            if (person.getAge() <= 0 || person.getAge() < repoPerson.getAge()){
//                throw new InvalidPersonAgeException("Invalid age!");
//            }
//            if (person.getLogin().isBlank()){
//                throw new InvalidPersonLoginException("Invalid login!");
//            }
//            if (person.getEmail().isBlank()){
//                throw new InvalidPersonEmailException("Invalid email!");
//            }
//            if (person.getName().isBlank()){
//                throw new InvalidPersonNameException("Invalid name!");
//            }
//            if (person.getPassword().isBlank()){
//                throw new InvalidPersonPasswordException("Invalid password!");
//            }
        this.restClient.put()
                .uri(BASE_URL + "person/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .body(person)
                .retrieve()
                .toBodilessEntity();
//        }else {
//            throw new PersonNotFoundException("Person does not exist!");
//        }
    }
}
