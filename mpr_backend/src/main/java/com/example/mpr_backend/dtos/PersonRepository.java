package com.example.mpr_backend.dtos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
     List<Person> findAllByName(String name);
     Person findByEmail(String email);
     Person findByLogin(String login);
     List<Person> findAll();
     List<Person> findAllByAge(int age);
     boolean existsByEmail(String email);
     boolean existsByLogin(String login);
     void deleteByEmail(String email);
}
