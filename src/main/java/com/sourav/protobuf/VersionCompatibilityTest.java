package com.sourav.protobuf;

import com.sourav.protobuf.models.Television;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VersionCompatibilityTest {
    public static void main(String[] args) throws IOException {

        Television television = Television
                .newBuilder()
                .setBrand("sony")
                .setYear(2015)
                .build();

        Path pathV1 = Paths.get("tv_v1.txt");
        Files.write(pathV1, television.toByteArray());

        byte[] bytes = Files.readAllBytes(pathV1);
        System.out.println(
                Television.parseFrom(bytes)
        );
    }
}
