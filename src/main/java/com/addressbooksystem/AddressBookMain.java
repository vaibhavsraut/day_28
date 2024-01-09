package com.addressbooksystem;

import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        AddressBookSystem addressBookSystem = new AddressBookSystem();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the new Address Book: ");
        String newAddressBookName = scanner.nextLine();

        addressBookSystem.addAddressBook(newAddressBookName);

        addressBookSystem.displayAddressBooks();

        AddressBook addressBook = new AddressBook();

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

        addressBook.addContact(firstName, lastName, address, city, state, zip, phoneNumber, email);

        System.out.print("Enter First Name of the contact to edit: ");
        String editFirstName = scanner.nextLine();
        System.out.print("Enter Last Name of the contact to edit: ");
        String editLastName = scanner.nextLine();

        addressBook.editContact(editFirstName, editLastName);

        System.out.print("Enter First Name of the contact to delete: ");
        String deleteFirstName = scanner.nextLine();
        System.out.print("Enter Last Name of the contact to delete: ");
        String deleteLastName = scanner.nextLine();

        addressBook.deleteContact(deleteFirstName, deleteLastName);

        addressBook.addMultipleContacts();

        addressBook.sortContactsByName();

        addressBook.displayContacts();


        scanner.close();
    }
}
