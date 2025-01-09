package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataProcessor {

    static String message = "";
    // Use JOptionPane to view messages when the program is run from a JAR file
    JOptionPane jOptionPane = new JOptionPane();

    static List<Person> readDataFromStream(InputStream inputStream) throws IOException {
        List<Person> people = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] personData = line.split(",");
                if (personData.length == 6) {
                    String firstName = personData[0].replace("\"", "").trim();
                    String lastName = personData[1].replace("\"", "").trim();
                    String address = personData[2].replace("\"", "").trim();
                    String city = personData[3].replace("\"", "").trim();
                    String state = personData[4].replace("\"", "").trim();
                    int age = Integer.parseInt(personData[5].replace("\"", "").trim());

                    // Add person if age is 19 or older
                    if (age >= 19) {
                        address = normalizeAddress(address);
                        people.add(new Person(firstName, lastName, address, city, state, age));
                    }
                }
            }

        } catch (IOException e) {
            message = message + "\n" + "    " + "Error: Reading file: " + e.getMessage();
        }

        return people;
    }

    static Map<String, List<Person>> groupByHouseholds(List<Person> people) {
        Map<String, List<Person>> households = new HashMap<>();

        for (Person person : people) {
            String normalizedAddress = normalizeAddress(person.getAddress());

            // If the address is not contains in the map, add it and create a list for people
            if (!households.containsKey(normalizedAddress)) {
                households.put(normalizedAddress, new ArrayList<>());
            }
            households.get(normalizedAddress).add(person);
        }

        return households;
    }

    static String normalizeAddress(String address) {
        return address.toLowerCase().trim();
    }
}



