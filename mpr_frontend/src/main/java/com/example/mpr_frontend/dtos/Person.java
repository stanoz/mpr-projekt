package com.example.mpr_frontend.dtos;

import java.util.Objects;


public class Person {

    private Long id;
    private String name;
    private String login;
    private String email;
    private String password;
    private int age;

    public Person(String name, String login, String email, String password, int age) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public Person() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(login, person.login) &&
                Objects.equals(email, person.email) &&
                Objects.equals(password, person.password);
    }

}
