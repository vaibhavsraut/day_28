package com.addressbooksystem;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        contacts.add(newContact);
    }

    public void displayContacts() {
        System.out.println("Contacts:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}

