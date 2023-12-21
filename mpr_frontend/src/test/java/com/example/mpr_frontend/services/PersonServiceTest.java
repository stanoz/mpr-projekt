//package com.example.mpr.services;
//
//import com.example.mpr.dtos.Person;
//import com.example.mpr.exceptions.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.not;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.hasSize;
//
//
//@ExtendWith(MockitoExtension.class)
//class PersonServiceTest {
//
//    @InjectMocks
//    private PersonService personService;
//
//    @Mock
//    private PersonRepository repository;
//    private AutoCloseable openMocks;
//    @Captor
//    private ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
//    @BeforeEach
//    public void init(){
//        openMocks = MockitoAnnotations.openMocks(this);
//        personService = new PersonService(repository);
//    }
//    @AfterEach
//    public void tearDown() throws Exception{
//        openMocks.close();
//    }
//    @Test
//    void getPersonByIdShouldReturnPersonWithGivenId() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        person.setId(1L);
//        when(repository.findById(1L)).thenReturn(Optional.of(person));
//        //when
//        Person result = personService.getPersonById(1L);
//        //then
//        assertEquals("Adam",result.getName());
//    }
//    @Test
//    void getPersonByIdShouldThrowPersonNotFoundException() {
//        //given
//        when(repository.findById(1L)).thenThrow(new PersonNotFoundException("Person not found"));
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(1L));
//    }
//
//    @Test
//    void getPersonByEmailShouldReturnPersonWithGivenEmail() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        when(repository.findByEmail(person.getEmail())).thenReturn(person);
//
//        //when
//        Person result = repository.findByEmail(person.getEmail());
//
//        //then
//        assertEquals(person,result);
//    }
//    @Test
//    void getPersonByEmailShouldThrowInvalidPersonEmailException(){
//        //given
//        //when
//        //then
//        assertThrows(InvalidPersonEmailException.class, () -> personService.getPersonByEmail(""));
//    }
//    @Test
//    void getPersonByEmailShouldThrowPersonNotFoundException(){
//        //given
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class, () -> personService.getPersonByEmail("email@email.com"));
//    }
//
//    @Test
//    void getPersonByLoginShouldReturnPersonWithGivenLogin() {
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        when(repository.findByLogin(person.getLogin())).thenReturn(person);
//
//        //when
//        Person result = repository.findByLogin(person.getLogin());
//
//        //then
//        assertEquals(person,result);
//    }
//    @Test
//    void getPersonByLoginShouldThrowInvalidPersonLoginException(){
//        //given
//        //when
//        //then
//        assertThrows(InvalidPersonLoginException.class, () -> personService.getPersonByLogin(""));
//    }
//    @Test
//    void getPersonByLoginShouldThrowPersonNotFoundException(){
//        //given
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class, () -> personService.getPersonByLogin("login"));
//    }
//    @Test
//    void getAllBySubNameShouldThrowInvalidPersonNameException(){
//        //given
//        //when
//        //then
//        assertThrows(InvalidPersonNameException.class, () -> personService.getAllBySubName(" "));
//    }
//    @Test
//    void getAllBySubNameShouldThrowPersonNotFoundExceptionWhenRepoPersonListEmpty(){
//        //given
//        List<Person> repoPersonList = new ArrayList<>();
//        when(repository.findAll()).thenReturn(repoPersonList);
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class, () -> personService.getAllBySubName("exception"));
//    }
//    @Test
//    void getAllBySubNameShouldThrowPersonNotFoundExceptionWhenResultPersonListEmpty(){
//        //given
//        Person person1 = new Person("Adam", "jamadam", "adam@gmail.com", "adam123", 40);
//        Person person2 = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 41);
//        List<Person> repoPersonList = new ArrayList<>();
//        repoPersonList.add(person1);
//        repoPersonList.add(person2);
//        when(repository.findAll()).thenReturn(repoPersonList);
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class, () -> personService.getAllBySubName("exception"));
//    }
//    @Test
//    void getAllBySubNameShouldReturnResultPersonList(){
//        Person person1 = new Person("Adam", "jamadam", "adam@gmail.com", "adam123", 40);
//        Person person2 = new Person("Jan", "jamjan", "jan@gmail.com", "jan123", 41);
//        List<Person> repoPersonList = new ArrayList<>();
//        repoPersonList.add(person1);
//        repoPersonList.add(person2);
//        when(repository.findAll()).thenReturn(repoPersonList);
//        //when
//        List<Person> resultPersonList = personService.getAllBySubName("a");
//        //then
//        assertThat(resultPersonList ,hasSize(2));
//    }
//    @Test
//    void addPersonShouldSavePerson(){
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        when(repository.save(captor.capture())).thenReturn(person);
//
//        //when
//        personService.addPerson(person);
//
//        //then
//        verify(repository).save(person);
//        assertEquals(person,captor.getValue());
//    }
//    @Test
//    void addPersonShouldThrowPersonAlreadyExistExceptionEmail(){
//        //given
//        Person person = new Person("Adam","jamadam2","adam@gmail.com","adam123",45);
//        when(repository.save(person)).thenThrow(new PersonAlreadyExistException("This email is taken!"));
//
//        //when
//        //then
//        assertThrows(PersonAlreadyExistException.class, () -> personService.addPerson(person));
//    }
//    @Test
//    void addPersonShouldThrowPersonAlreadyExistExceptionLogin(){
//        //given
//        Person person = new Person("Adam","jamadam","adam2@gmail.com","adam123",45);
//        when(repository.save(person)).thenThrow(new PersonAlreadyExistException("This login is taken!"));
//
//        //when
//        //then
//        assertThrows(PersonAlreadyExistException.class, () -> personService.addPerson(person));
//    }
//    @Test
//    void addPersonShouldThrowInvalidPersonAgeException(){
//        //given
//        Person person = new Person("Adam","jamadam","adam2@gmail.com","adam123",0);
//        //when
//        //then
//        assertThrows(InvalidPersonAgeException.class, () -> personService.addPerson(person));
//    }
//    @Test
//    void addPersonShouldThrowInvalidPersonNameException(){
//        //given
//        Person person = new Person(" ","jamadam","adam2@gmail.com","adam123",40);
//        //when
//        //then
//        assertThrows(InvalidPersonNameException.class, () -> personService.addPerson(person));
//    }
//    @Test
//    void addPersonShouldThrowInvalidPersonEmailException(){
//        //given
//        Person person = new Person("Adam","jamadam"," ","adam123",40);
//        //when
//        //then
//        assertThrows(InvalidPersonEmailException.class, () -> personService.addPerson(person));
//    }
//    @Test
//    void addPersonShouldThrowInvalidPersonLoginException(){
//        //given
//        Person person = new Person("Adam"," ","adam@gmail.com","adam123",40);
//        //when
//        //then
//        assertThrows(InvalidPersonLoginException.class, () -> personService.addPerson(person));
//    }
//    @Test
//    void addPersonShouldThrowInvalidPersonPasswordException(){
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com"," ",40);
//        //when
//        //then
//        assertThrows(InvalidPersonPasswordException.class, () -> personService.addPerson(person));
//    }
//    @Test
//    void getAllShouldReturnAllPersons(){
//        //given
//        Person person1 = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        Person person2 = new Person("Janek","jamjanek","janek@gmail.com","janek123",30);
//        List<Person> personList = List.of(person1,person2);
//        when(repository.findAll()).thenReturn(personList);
//        //when
//        List<Person> resultPersonList = personService.getAll();
//        //then
//        assertThat(resultPersonList, hasSize(2));
//    }
//    @Test
//    void getAllShouldThrowPersonNotFoundException(){
//        //given
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class, () -> personService.getAll());
//    }
//    @Test
//    void getAllByNameShouldReturnAllPersonsWithGivenName(){
//        //given
//        Person person1 = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        Person person2 = new Person("Adam","jamjanek","janek@gmail.com","janek123",30);
//        List<Person> personList = List.of(person1,person2);
//        when(repository.findAllByName("Adam")).thenReturn(personList);
//        //when
//        List<Person> resultPersonList = personService.getPersonByName("Adam");
//        //then
//        assertThat(resultPersonList, hasSize(2));
//    }
//    @Test
//    void getAllByNameShouldThrowInvalidPersonNameException(){
//        //given
//        //when
//        //then
//        assertThrows(InvalidPersonNameException.class, () -> personService.getPersonByName(""));
//    }
//    @Test
//    void getAllByNameShouldThrowPersonNotFoundException(){
//        //given
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class, () -> personService.getPersonByName("noName"));
//    }
//    @Test
//    void getAllByAgeShouldReturnAllPersonsWithGivenAge(){
//        //given
//        Person person1 = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        Person person2 = new Person("Janek","jamjanek","janek@gmail.com","janek123",45);
//        List<Person> personList = List.of(person1,person2);
//        when(repository.findAllByAge(45)).thenReturn(personList);
//        //when
//        List<Person> resultPersonList = personService.getPersonByAge(45);
//        //then
//        assertThat(resultPersonList, hasSize(2));
//    }
//    @Test
//    void getAllByAgeShouldThrowInvalidPersonAgeException(){
//        //given
//        //when
//        //then
//        assertThrows(InvalidPersonAgeException.class, () -> personService.getPersonByAge(0));
//    }
//    @Test
//    void getAllByAgeShouldThrowPersonNotFoundException(){
//        //given
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class, () -> personService.getPersonByAge(500));
//    }
//    @Test
//    void deletePersonByEmailShouldDeletePerson(){
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        when(repository.existsByEmail(person.getEmail())).thenReturn(true);
//        //when
//        personService.deletePersonByEmail(person.getEmail());
//        //then
//        verify(repository).deleteByEmail(person.getEmail());
//    }
//    @Test
//    void deletePersonByEmailShouldThrowInvalidPersonEmailException(){
//        //given
//        //when
//        //then
//        assertThrows(InvalidPersonEmailException.class,() -> personService.deletePersonByEmail(""));
//    }
//    @Test
//    void deletePersonByEmailShouldThrowPersonNotFoundException(){
//        //given
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class,() -> personService.deletePersonByEmail("email@email.com"));
//    }
//    @Test
//    void deletePersonByIdShouldDeletePerson(){
//        //given
//        when(repository.existsById(1L)).thenReturn(true);
//        //when
//        personService.deletePersonById(1L);
//        //then
//        verify(repository).deleteById(1L);
//    }
//    @Test
//    void deletePersonByIdShouldThrowPersonNotFoundException(){
//        //given
//        when(repository.existsById(1L)).thenReturn(false);
//        //when
//        //then
//        assertThrows(PersonNotFoundException.class,() -> personService.deletePersonById(1L));
//    }
//    @Test
//    void editPersonShouldEditExistingPerson(){
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        Person repoPerson = new Person("Adam","jamadam","adam@gmail.com","adam123",40);
//        when(repository.existsById(person.getId())).thenReturn(true);
//        when(repository.findByEmail(person.getEmail())).thenReturn(repoPerson);
//        //when
//        personService.editPerson(person);
//        //given
//        verify(repository).save(person);
//    }
//    @Test
//    void editPersonShouldThrowPersonNotFoundException(){
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        when(repository.existsById(person.getId())).thenReturn(false);
//        //when
//        //given
//        assertThrows(PersonNotFoundException.class, () -> personService.editPerson(person));
//    }
//    @Test
//    void editPersonShouldThrowPersonAlreadyExistsException(){
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        Person repoPerson = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        when(repository.existsById(person.getId())).thenReturn(true);
//        when(repository.findByEmail(person.getEmail())).thenReturn(repoPerson);
//        //when
//        //then
//        assertThrows(PersonAlreadyExistException.class, () -> personService.editPerson(person));
//    }
//    @Test
//    void editPersonShouldThrowInvalidPersonAgeException(){
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","adam123",45);
//        Person repoPerson = new Person("Adam","jamadam","adam@gmail.com","adam123",50);
//        when(repository.existsById(person.getId())).thenReturn(true);
//        when(repository.findByEmail(person.getEmail())).thenReturn(repoPerson);
//        //when
//        //then
//        assertThrows(InvalidPersonAgeException.class, () -> personService.editPerson(person));
//    }
//    @Test
//    void editPersonShouldThrowInvalidPersonLoginException(){
//        //given
//        Person person = new Person("Adam","","adam@gmail.com","adam123",45);
//        Person repoPerson = new Person("Adam","jamadam","adam@gmail.com","adam123",40);
//        when(repository.existsById(person.getId())).thenReturn(true);
//        when(repository.findByEmail(person.getEmail())).thenReturn(repoPerson);
//        //when
//        //then
//        assertThrows(InvalidPersonLoginException.class, () -> personService.editPerson(person));
//    }
//    @Test
//    void editPersonShouldThrowInvalidPersonEmailException(){
//        //given
//        Person person = new Person("Adam","jamadam","","adam123",45);
//        Person repoPerson = new Person("Adam","jamadam","adam@gmail.com","adam123",40);
//        when(repository.existsById(person.getId())).thenReturn(true);
//        when(repository.findByEmail(person.getEmail())).thenReturn(repoPerson);
//        //when
//        //then
//        assertThrows(InvalidPersonEmailException.class, () -> personService.editPerson(person));
//    }
//    @Test
//    void editPersonShouldThrowInvalidPersonNameException(){
//        //given
//        Person person = new Person("","jamadam","adam@gmail.com","adam123",45);
//        Person repoPerson = new Person("Adam","jamadam","adam@gmail.com","adam123",40);
//        when(repository.existsById(person.getId())).thenReturn(true);
//        when(repository.findByEmail(person.getEmail())).thenReturn(repoPerson);
//        //when
//        //then
//        assertThrows(InvalidPersonNameException.class, () -> personService.editPerson(person));
//    }
//    @Test
//    void editPersonShouldThrowInvalidPersonPasswordException(){
//        //given
//        Person person = new Person("Adam","jamadam","adam@gmail.com","",45);
//        Person repoPerson = new Person("Adam","jamadam","adam@gmail.com","adam123",40);
//        when(repository.existsById(person.getId())).thenReturn(true);
//        when(repository.findByEmail(person.getEmail())).thenReturn(repoPerson);
//        //when
//        //then
//        assertThrows(InvalidPersonPasswordException.class, () -> personService.editPerson(person));
//    }
//}