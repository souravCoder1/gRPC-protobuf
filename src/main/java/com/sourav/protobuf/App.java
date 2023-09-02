package com.sourav.protobuf;

import com.sourav.protobuf.models.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws IOException {
        Person person1 = Person.newBuilder()
                .setId(10)
                .setName("Sourav")
                .setAddress("New York")
                .build();

        Person person2 = Person.newBuilder()
                .setId(10)
                .setName("Sourav")
                .setAddress("New York")
                .build();

        System.out.println(person1);
        System.out.println(person2);

        System.out.println("Check Equal: "+ person1.equals(person2));

        // Serialization & Deserialization

        Path path = Paths.get("person_byte.txt");
        Files.write(path, person1.toByteArray());
        byte[] sezPerson = Files.readAllBytes(path);
        Person desezPerson = Person.parseFrom(sezPerson) ;
        System.out.println(Arrays.toString(sezPerson));
        System.out.println(desezPerson);
    }
}
