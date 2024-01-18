package com.example.mpr_backend.services;

import com.example.mpr_backend.dtos.Person;
import com.example.mpr_backend.dtos.PersonRepository;
import com.example.mpr_backend.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class PersonService {
    PersonRepository repository;
    private static final String EMAIL_PATTERN = "^[\\w\\d._%+-]+@[\\w\\d._%+-]+\\.[\\w\\d._%+-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    @Autowired
    public PersonService(PersonRepository repository){
        this.repository = repository;
        this.repository.save(new Person("Jan","jamjan","jan@gmail.com","jan123",50));
        this.repository.save(new Person("Piotr","jampiotr","piotr@gmail.com","piotr123",35));
    }

    private void correctPersonData(Person person) {
        person.setName(person.getName().trim());
        person.setEmail(person.getEmail().trim());
        person.setLogin(person.getLogin().trim());
        person.setPassword(person.getPassword().trim());

        person.setName(person.getName().substring(0,1).toUpperCase() + person.getName().substring(1));
    }

    private void validatePerson(Person person){
        if (person.getLogin().isBlank()){
            throw new InvalidPersonLoginException("Invalid login!");
        }
        if (person.getEmail().isBlank()){
            throw new InvalidPersonEmailException("Invalid email!");
        }
        Matcher matcher = pattern.matcher(person.getEmail());
        if (!matcher.matches()){
            throw new InvalidPersonEmailException("Invalid email!");
        }

        if (person.getName().isBlank()){
            throw new InvalidPersonNameException("Invalid name!");
        }

        if (person.getPassword().isBlank()){
            throw new InvalidPersonPasswordException("Invalid password!");
        }
    }

    public boolean checkPersonExistsById(Long id){
        return this.repository.existsById(id);
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
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person with given id not found!"));
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
    public Person addPerson(Person person){
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
            validatePerson(person);
            correctPersonData(person);
            return this.repository.save(person);
        }
    }

    @Transactional
    public void deletePersonByEmail(String email){
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
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
    public Person editPerson(Person person){
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
            validatePerson(person);
            correctPersonData(person);
            return this.repository.save(person);
        }else {
            throw new PersonNotFoundException("Person does not exist!");
        }
    }
}
