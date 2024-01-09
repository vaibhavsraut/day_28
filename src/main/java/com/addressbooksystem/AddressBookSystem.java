package com.addressbooksystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookSystem {
    private Map<String, AddressBook> addressBooks;

    public AddressBookSystem() {
        this.addressBooks = new HashMap<>();
    }

    public void addAddressBook(String addressBookName) {
        if (!addressBooks.containsKey(addressBookName)) {
            AddressBook newAddressBook = new AddressBook();
            addressBooks.put(addressBookName, newAddressBook);
            System.out.println("Address Book '" + addressBookName + "' added successfully.");
        } else {
            System.out.println("Address Book with name '" + addressBookName + "' already exists.");
        }
    }

    public void displayAddressBooks() {
        System.out.println("Address Books:");
        for (String addressBookName : addressBooks.keySet()) {
            System.out.println(addressBookName);
        }
    }
}

