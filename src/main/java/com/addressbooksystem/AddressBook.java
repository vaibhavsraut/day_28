package com.addressbooksystem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    private List<Contact> contacts;
    private Map<String, List<Contact>> cityPersonMap;
    private Map<String, List<Contact>> statePersonMap;

    public AddressBook() {
        this.contacts = new ArrayList<>();
        this.cityPersonMap = new HashMap<>();
        this.statePersonMap = new HashMap<>();
    }

    public boolean isDuplicate(Contact newContact) {
        List<Contact> duplicateContacts = contacts.stream()
                .filter(contact -> contact.equals(newContact))
                .collect(Collectors.toList());

        return !duplicateContacts.isEmpty();
    }

    public void addContact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);

        if (!isDuplicate(newContact)) {
            contacts.add(newContact);
            System.out.println("Contact added successfully.");
        } else {
            System.out.println("Duplicate entry. Contact with the same name already exists.");
        }

        cityPersonMap.computeIfAbsent(city, k -> new ArrayList<>()).add(newContact);
        statePersonMap.computeIfAbsent(state, k -> new ArrayList<>()).add(newContact);
    }

    public Map<String, List<Contact>> getCityPersonMap() {
        return cityPersonMap;
    }

    public Map<String, List<Contact>> getStatePersonMap() {
        return statePersonMap;
    }

    public List<Contact> getPersonsByCity(String city) {
        return cityPersonMap.getOrDefault(city, Collections.emptyList());
    }

    public List<Contact> getPersonsByState(String state) {
        return statePersonMap.getOrDefault(state, Collections.emptyList());
    }

    public void addMultipleContacts() {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter City: ");
            String city = scanner.nextLine();
            System.out.print("Enter State: ");
            String state = scanner.nextLine();
            System.out.print("Enter Zip: ");
            String zip = scanner.nextLine();
            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            addContact(firstName, lastName, address, city, state, zip, phoneNumber, email);

            System.out.print("Do you want to add another person? (y/n): ");
            choice = scanner.nextLine().charAt(0);

        } while (choice == 'y' || choice == 'Y');

        scanner.close();
    }

    public void editContact(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter new Address: ");
                String address = scanner.nextLine();
                System.out.print("Enter new City: ");
                String city = scanner.nextLine();
                System.out.print("Enter new State: ");
                String state = scanner.nextLine();
                System.out.print("Enter new Zip: ");
                String zip = scanner.nextLine();
                System.out.print("Enter new Phone Number: ");
                String phoneNumber = scanner.nextLine();
                System.out.print("Enter new Email: ");
                String email = scanner.nextLine();

                contact.setAddress(address);
                contact.setCity(city);
                contact.setState(state);
                contact.setZip(zip);
                contact.setPhoneNumber(phoneNumber);
                contact.setEmail(email);

                System.out.println("Contact updated successfully.");

                scanner.close();

                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void deleteContact(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                contacts.remove(contact);
                System.out.println("Contact deleted successfully.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public List<Contact> searchPersonByCity(String city) {
        return contacts.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Contact> searchPersonByState(String state) {
        return contacts.stream()
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    public long getContactCountByCity(String city) {
        return cityPersonMap.getOrDefault(city, Collections.emptyList()).size();
    }

    public long getContactCountByState(String state) {
        return statePersonMap.getOrDefault(state, Collections.emptyList()).size();
    }

    public void sortContactsByName() {
        contacts = contacts.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public void sortContactsByCity() {
        contacts = contacts.stream()
                .sorted(Comparator.comparing(Contact::getCity))
                .collect(Collectors.toList());
    }

    public void sortContactsByState() {
        contacts = contacts.stream()
                .sorted(Comparator.comparing(Contact::getState))
                .collect(Collectors.toList());
    }

    public void sortContactsByZip() {
        contacts = contacts.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .collect(Collectors.toList());
    }

    public void writeToFile(String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(contacts);
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String fileName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (List<Contact>) objectInputStream.readObject();
            System.out.println("Data read from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSV(String fileName) {
        try (Writer writer = new FileWriter(fileName);
             CSVWriter csvWriter = (CSVWriter) new CSVWriterBuilder(writer)
                     .withSeparator(',')
                     .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                     .build()) {

            List<String[]> data = new ArrayList<>();
            for (Contact contact : contacts) {
                data.add(new String[]{contact.getFirstName(), contact.getLastName(), contact.getAddress(),
                        contact.getCity(), contact.getState(), contact.getZip(), contact.getPhoneNumber(), contact.getEmail()});
            }

            csvWriter.writeAll(data);
            System.out.println("Data written to CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromCSV(String fileName) {
        try (Reader reader = new FileReader(fileName);
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {

            List<String[]> data = csvReader.readAll();
            for (String[] row : data) {
                String firstName = row[0];
                String lastName = row[1];
                String address = row[2];
                String city = row[3];
                String state = row[4];
                String zip = row[5];
                String phoneNumber = row[6];
                String email = row[7];

                Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
                contacts.add(contact);
            }

            System.out.println("Data read from CSV file successfully.");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public void writeToJson(String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new Gson();
            gson.toJson(contacts, writer);
            System.out.println("Data written to JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromJson(String fileName) {
        try (Reader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            Type contactListType = new TypeToken<List<Contact>>() {}.getType();
            contacts = gson.fromJson(reader, contactListType);
            System.out.println("Data read from JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayContacts() {
        System.out.println("Contacts:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}

