package com.sourav.protobuf;

import com.google.protobuf.Int32Value;
import com.sourav.protobuf.models.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        Person person1 = Person.newBuilder()
                .setId(Int32Value.newBuilder().setValue(10).build())
                .setName("Sourav")
                .setAddress("New York")
                .build();

        Person person2 = Person.newBuilder()
                .setId(Int32Value.newBuilder().setValue(20).build())
                .setName("Sourav")
                .setAddress("New York")
                .build();

        Address address = Address.newBuilder()
                .setPostbox(123)
                .setStreet("main street")
                .setCity("Atlanta")
                .build();

        Car civic = Car.newBuilder()
                .setMake("Honda")
                .setModel("Civic")
                .setYear(2005)
                .setBodyStyle(BodyStyle.COUPE)
                .build();

        Car accord = Car.newBuilder()
                .setMake("Honda")
                .setModel("Civic")
                .setBodyStyle(BodyStyle.SUV)
                .setYear(2005)
                .build();


        List<Car> cars = new ArrayList<>();
        cars.add(accord);
        cars.add(civic);

        User user = User.newBuilder()
                .setName("Sourav")
                .setAge(25)
                .addCar(accord)
                .addCar(civic)
                //.addAllCar(cars)
                .setAddress(address)
                .build();

        Dealer dealer = Dealer.newBuilder()
                .putModel(2005, civic)
                .putModel(2020, accord)
                .build();

        System.out.println(dealer.getModelCount());
        System.out.println(dealer.getModelOrThrow(2005));
        System.out.println(dealer.getModelOrDefault(2001, accord));
        dealer.getModelMap(); // actual Map
        System.out.println(user.hasAddress()); // check default value


        System.out.println(dealer.getModelOrThrow(2005).getBodyStyle());

        System.out.println(person1);
        System.out.println(person2);
        System.out.println("User: " + user);

        System.out.println("Check Equal: " + person1.equals(person2));

        // Serialization & Deserialization

        Path path = Paths.get("person_byte.txt");
        Files.write(path, person1.toByteArray());
        byte[] sezPerson = Files.readAllBytes(path);
        Person desezPerson = Person.parseFrom(sezPerson);
        System.out.println(Arrays.toString(sezPerson));
        System.out.println(desezPerson);
    }
}
