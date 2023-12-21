package com.example.mpr_backend.services;

import com.example.mpr_backend.dtos.Person;
import com.example.mpr_backend.dtos.PersonRepository;
import com.example.mpr_backend.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@Service
public class PersonService {
    PersonRepository repository;
    @Autowired
    public PersonService(PersonRepository repository){
        this.repository = repository;
        this.repository.save(new Person("jan","jamjan","jan@gmail.com","jan123",50));
        this.repository.save(new Person("piotr","jampiotr","piotr@gmail.com","piotr123",35));
    }
    public List<Person> getPersonByName(String name){
        if (name.isBlank()){
            throw new InvalidPersonNameException("Invalid name!");
        }
        List<Person> repoPersonList = this.repository.findAllByName(name);
        if (repoPersonList.isEmpty()){
            throw new PersonNotFoundException("Person not found!");
        }
        return repoPersonList;
    }
    public Person getPersonById(Long id){
        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person with given id not found!"));
        return person;
    }
    public Person getPersonByEmail(String email){
        if (email.isBlank()){
            throw new InvalidPersonEmailException("Invalid email!");
        }
        if (!this.repository.existsByEmail(email)) {
            throw new PersonNotFoundException("Person not found!");
        }
        return this.repository.findByEmail(email);
    }
    public Person getPersonByLogin(String login){
        if (login.isBlank()){
            throw new InvalidPersonLoginException("Invalid login!");
        }
        if (!this.repository.existsByLogin(login)) {
            throw new PersonNotFoundException("Person not found!");
        }
        return this.repository.findByLogin(login);
    }
    public List<Person> getPersonByAge(int age){
        if (age <= 0){
            throw new InvalidPersonAgeException("Invalid age!");
        }
        List<Person> repoPersonsList = this.repository.findAllByAge(age);
        if (repoPersonsList.isEmpty()){
            throw new PersonNotFoundException("Person not found!");
        }
        return repoPersonsList;
    }
    public List<Person> getAll(){
        List<Person> repoPersonList = this.repository.findAll();
        if (repoPersonList.isEmpty()){
            throw new PersonNotFoundException("Person not found!");
        }
        return repoPersonList;
    }
    public List<Person> getAllBySubName(String subName){
        if (subName.isBlank()){
            throw new InvalidPersonNameException("Invalid name!");
        }
        List<Person> repoPersonList = this.repository.findAll();
        if (repoPersonList.isEmpty()){
            throw new PersonNotFoundException("List of Person is empty!");
        }
        List<Person> resultPersonList = new ArrayList<>();
        for (Person repoPerson : repoPersonList) {
            if (repoPerson.getName().contains(subName)){
                resultPersonList.add(repoPerson);
            }
        }
        if (resultPersonList.isEmpty()){
            throw new PersonNotFoundException("Not found Person with given sub name!");
        }
        return repoPersonList;
    }
    public void addPerson(Person person){
        boolean givenEmailExists = this.repository.existsByEmail(person.getEmail());
        boolean givenLoginExists = this.repository.existsByLogin(person.getLogin());
        if (givenEmailExists || givenLoginExists) {
            if (givenEmailExists){
                throw new PersonAlreadyExistException("This email is taken!");
            }else {
                throw new PersonAlreadyExistException("This login is taken!");
            }
        }else {
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
            this.repository.save(person);
        }
    }
    public void deletePersonByEmail(String email){
        if (email.isBlank()){
            throw new InvalidPersonEmailException("Invalid email!");
        }
        boolean personExist = this.repository.existsByEmail(email);
        if (!personExist) {
            throw new PersonNotFoundException("Person does not exist!");
        }
        this.repository.deleteByEmail(email);
    }
    public void deletePersonById(Long id){
        boolean personExist = this.repository.existsById(id);
        if (!personExist) {
            throw new PersonNotFoundException("Person does not exist!");
        }
        this.repository.deleteById(id);
    }
    public void editPerson(Person person){
        boolean personExist = this.repository.existsById(person.getId());
        if (personExist){
            Person repoPerson = repository.findById(person.getId())
                    .orElseThrow(() -> new PersonNotFoundException("Person with given id not found!"));
            if (repoPerson.equals(person)){
                throw new PersonAlreadyExistException("Person with exactly same given data already exists!");
            }
            if (person.getAge() <= 0 || person.getAge() < repoPerson.getAge()){
                throw new InvalidPersonAgeException("Invalid age!");
            }
            if (person.getLogin().isBlank()){
                throw new InvalidPersonLoginException("Invalid login!");
            }
            if (person.getEmail().isBlank()){
                throw new InvalidPersonEmailException("Invalid email!");
            }
            if (person.getName().isBlank()){
                throw new InvalidPersonNameException("Invalid name!");
            }
            if (person.getPassword().isBlank()){
                throw new InvalidPersonPasswordException("Invalid password!");
            }
            this.repository.save(person);
        }else {
            throw new PersonNotFoundException("Person does not exist!");
        }
    }
}
