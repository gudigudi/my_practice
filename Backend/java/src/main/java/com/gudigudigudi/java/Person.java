package com.gudigudigudi.java;

public class Person {

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "com.gudigudigudi.java.Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
