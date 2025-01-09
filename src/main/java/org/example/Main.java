package org.example;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.example.DataProcessor.groupByHouseholds;
import static org.example.DataProcessor.readDataFromStream;

public class Main {

    // Use JOptionPane to view messages when the program is run from a JAR file
    JOptionPane jOptionPane = new JOptionPane();

    public static void main(String[] args) {

        StringBuilder messageBuilder = new StringBuilder();

        // Load data.txt
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("data.txt");

        if (inputStream == null) {
            JOptionPane.showMessageDialog(null, "Error: data.txt not found in the JAR!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Read the data
            List<Person> people = readDataFromStream(inputStream);
            // Group the people
            Map<String, List<Person>> households = groupByHouseholds(people);

            for (Map.Entry<String, List<Person>> entry : households.entrySet()) {
                String address = entry.getKey();
                List<Person> occupants = entry.getValue();

                // Sort people by last name and first name
                sortOccupantsByName(occupants);

                messageBuilder.append("\nHousehold Group: ").append(address)
                        .append(" [Total Occupants: ").append(occupants.size()).append("]");

                for (Person person : occupants) {
                    messageBuilder.append("\n    ").append(person.getFirstName()).append(" ").append(person.getLastName())
                            .append(" ").append(person.getAddress()).append(" ").append(person.getAge());
                }
            }

        } catch (IOException e) {
            messageBuilder.append("\nError reading file: ").append(e.getMessage());
        }

        // Show the message in a pop-up window
        JOptionPane.showMessageDialog(null, messageBuilder.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void sortOccupantsByName(List<Person> occupants) {
        Collections.sort(occupants, new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                int lastNameComparison = person1.getLastName().compareTo(person2.getLastName());
                if (lastNameComparison != 0) {
                    return lastNameComparison;
                }
                return person1.getFirstName().compareTo(person2.getFirstName());
            }
        });
    }

}

