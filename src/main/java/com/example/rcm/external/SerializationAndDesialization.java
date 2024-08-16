package com.example.rcm.external;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SerializationAndDesialization {

    public static void main(String[] args) throws IOException {
        // Create an instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Serialize Java object to JSON
        Person person = new Person("John", 30);
        String json = objectMapper.writeValueAsString(person);
        System.out.println("Serialized JSON: " + json);

        // Deserialize JSON to Java object
        String jsonInput = "{\"name\":\"Alice\",\"age\":25}";
        Person deserializedPerson = objectMapper.readValue(jsonInput, Person.class);
        System.out.println("Deserialized Person: " + deserializedPerson);
    }

    // Sample class for demonstration
    static class Person {
        private String name;
        private int age;

        // Constructor
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Getters and setters (not shown for brevity)

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
