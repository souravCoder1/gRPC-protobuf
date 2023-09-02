package com.sourav.protobuf;

import com.sourav.protobuf.models.Person;

public class App {
    public static void main(String[] args) {
        Person person = Person.newBuilder()
                .setId(10)
                .setName("Sourav")
                .setAddress("New York")
                .build();
        System.out.println(person);
    }
}
