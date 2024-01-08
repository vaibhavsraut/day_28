package com.addressbooksystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        contacts.add(newContact);
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

    public void displayContacts() {
        System.out.println("Contacts:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}

