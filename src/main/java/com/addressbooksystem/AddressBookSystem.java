package com.addressbooksystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public List<Contact> searchPersonByCity(String city) {
        return addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.searchPersonByCity(city).stream())
                .collect(Collectors.toList());
    }

    public List<Contact> searchPersonByState(String state) {
        return addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.searchPersonByState(state).stream())
                .collect(Collectors.toList());
    }

    public void displayAddressBooks() {
        System.out.println("Address Books:");
        for (String addressBookName : addressBooks.keySet()) {
            System.out.println(addressBookName);
        }
    }
}

