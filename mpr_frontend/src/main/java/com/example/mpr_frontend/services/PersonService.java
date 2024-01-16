package com.example.mpr_frontend.services;

import com.example.mpr_frontend.dtos.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private final RestClient restClient;
    private static final String BASE_URL = "http://localhost:8080/";
    public PersonService(RestClient restClient) {
        this.restClient = restClient;
    }
    public Person getPersonById(Long id){
        return restClient.get()
                .uri(BASE_URL + "person/id/" + id)
                .retrieve()
                .body(Person.class);
    }
    public List<Person> getAll(){
        return restClient
                .get()
                .uri(BASE_URL + "person/getAll")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
    public void addPerson(Person person){
                this.restClient.post()
                    .uri(BASE_URL + "person/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(person)
                    .retrieve()
                    .toBodilessEntity();
    }
    public void deletePersonById(Long id){
        this.restClient.delete()
                .uri(BASE_URL + "person/delete-by-id/" + id)
                .retrieve();
    }
    public void editPerson(Person person){
        this.restClient.put()
                .uri(BASE_URL + "person/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .body(person)
                .retrieve()
                .toBodilessEntity();
    }
}
