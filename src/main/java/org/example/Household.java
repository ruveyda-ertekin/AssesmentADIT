package org.example;

import java.util.ArrayList;
import java.util.List;


public class Household {
    private String address;
    private List<Person> occupants;

    public Household(String address) {
        this.address = address;
        this.occupants = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public List<Person> getResident() {
        return occupants;
    }

    // Add a person to the household
    public void addOccupant(Person person) {
        occupants.add(person);
    }

    // Get total number of people in the household
    public int getTotalOccupants() {
        return occupants.size();
    }
}

