package com.testslotegrator.api.utils;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
    private Faker faker = new Faker();

    private String username = faker.name().username();
    private String password = faker.internet().password();
    private String email = faker.internet().emailAddress();
    private String name = faker.name().firstName();
    private String surname = faker.name().lastName();
    private int id = faker.number().numberBetween(1, 10000);

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}