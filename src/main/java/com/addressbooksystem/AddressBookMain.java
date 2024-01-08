package com.addressbooksystem;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        AddressBook addressBook = new AddressBook();

        addressBook.addContact("Vaibhav", "Raut", "ABC Main St", "Pune", "MH", "444404", "9579354301", "vaibhav.raut851@gmail.com");

        addressBook.displayContacts();
    }
}
