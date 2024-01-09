package com.addressbooksystem;

import java.util.*;
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

    public Map<String, List<Contact>> getAllPersonsByCity() {
        return addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getCityPersonMap().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (list1, list2) -> {
                    List<Contact> mergedList = new ArrayList<>(list1);
                    mergedList.addAll(list2);
                    return mergedList;
                }));
    }

    public Map<String, List<Contact>> getAllPersonsByState() {
        return addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getStatePersonMap().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (list1, list2) -> {
                    List<Contact> mergedList = new ArrayList<>(list1);
                    mergedList.addAll(list2);
                    return mergedList;
                }));
    }

    public Map<String, Long> getContactCountByCity() {
        return addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getCityPersonMap().keySet().stream())
                .collect(Collectors.groupingBy(city -> city, Collectors.counting()));
    }

    public Map<String, Long> getContactCountByState() {
        return addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getStatePersonMap().keySet().stream())
                .collect(Collectors.groupingBy(state -> state, Collectors.counting()));
    }

    public void displayAddressBooks() {
        System.out.println("Address Books:");
        for (String addressBookName : addressBooks.keySet()) {
            System.out.println(addressBookName);
        }
    }

}

