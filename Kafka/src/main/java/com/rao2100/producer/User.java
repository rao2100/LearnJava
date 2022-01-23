package com.rao2100.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class User {

    @JsonProperty
    public String firstName;

    @JsonProperty
    public String lastName;

    @JsonProperty
    public short age;

    public User() {}

//    public User(String firstName, String lastName, short age) {
//        this(firstName, lastName, age, null);
//    }

}
