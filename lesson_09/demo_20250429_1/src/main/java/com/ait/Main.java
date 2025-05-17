package com.ait;

public class Main {
    public static void main(String[] args) {
        Person p = new Person("Jack", 10, 30, 150);
        int w = p.getWeight();
        System.out.println(p);
        Person mike = Person.builder()
                .name("Mike")
                .height(178)
                .age(22)
                .build();
        System.out.println(mike);
    }
}