package com.ait;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person("Jack", 18);
        Person[] people = {
                new Person("John", 22),
                new Person("Nock", 23),
                new Person("Johana", 25),
                new Person("Jessika", 21),
                new Person("Nicole", 27),
        };

        String json = mapper.writeValueAsString(person);
        mapper.writeValue(new File("person1.json"), person);
        System.out.println(json);
        mapper.writeValue(new File("person2.json"), people);
        System.out.println(json);

        ///----------------------------------------------------------------------
        Person personData = mapper.readValue(new File("person1.json"), Person.class);
        System.out.println("person data: " + personData);

        Person[] peopleData = mapper.readValue(new File("person2.json"), Person[].class);
        System.out.println("people data: " + Arrays.toString(peopleData));

    }
}