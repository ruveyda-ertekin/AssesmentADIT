package org.example;


public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int age;

    public Person(String firstName, String lastName, String address, String city, String state, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.age = age;
    }

    // Getter method for fields
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getFullAddress() {
        return address + ", " + city + ", " + state;
    }
}

