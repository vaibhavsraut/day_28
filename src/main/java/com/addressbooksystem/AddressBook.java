package com.addressbooksystem;

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

    public void displayContacts() {
        System.out.println("Contacts:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}

