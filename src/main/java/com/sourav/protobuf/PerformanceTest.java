package com.sourav.protobuf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Int32Value;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sourav.protobuf.model.JPerson;
import com.sourav.protobuf.models.Person;

import java.io.IOException;


// REST vs gRPC
public class PerformanceTest {
    public static void main(String[] args) {
        JPerson person = new JPerson();
        person.setId(10);
        person.setName("Sourav");

        // json
        Runnable json = () -> {
            ObjectMapper mapper = new ObjectMapper();
            byte[] serializedJPerson;
            try {
                serializedJPerson = mapper.writeValueAsBytes(person);
                System.out.println(serializedJPerson.length);
                JPerson deserializedJPerson = mapper.readValue(serializedJPerson, JPerson.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        // protobuf
        Person serializedPerson = Person.newBuilder()
                .setName("sam")
                .setId(Int32Value.newBuilder().setValue(10).build())
                .build();
        Runnable proto = () -> {
            try {
                byte[] bytes = serializedPerson.toByteArray();
                System.out.println(bytes.length);
                Person deserializedPerson = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        runPerformanceTest(json, "JSON");
        runPerformanceTest(proto, "PROTO");
    }

    private static void runPerformanceTest(Runnable runnable, String method) {
        long time1 = System.currentTimeMillis();
        long time2 = 0L;
        for (int i = 0; i < 1_00_000; i++) {
            runnable.run();
            time2 = System.currentTimeMillis();
        }
        System.out.println(method + " : " + (time2 - time1) + " ms");
    }
}