package com.bridgelabz.addressbook.controller;

/**
 * import classes
 */
import com.bridgelabz.addressbook.model.AddressBook;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created controller class to make api calls
 */
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    /**
     * Autowired AddressBookRepository so we can inject its dependency here
     */
    @Autowired
    AddressBookRepository repository;

    /**
     * - Ability to display welcome message
     * @return- welcome msg
     */
    @GetMapping("")
    public String getMessage() {
        return "Welcome to Address Book App";
    }

    /**
     * Create api call to save data to repository
     * @param addressBook - person all data
     * @return- accepts the address book data in JSON format and stores it in DB
     */
    @PostMapping("/post")
    public AddressBook postAddress(@RequestBody AddressBook addressBook) {
        AddressBook newAddressBook = new AddressBook(addressBook);
        repository.save(newAddressBook);
        return newAddressBook;
    }

    /**
     * - Ability to get all address book data by findAll() method
     * @return :- showing all data
     */
    @GetMapping("/get")
    public List<AddressBook> getAddress() {
        List<AddressBook> addressBook = repository.findAll();
        return addressBook;
    }

    /**
     * Ability to get person data by id
     * @param id - person id
     * @return - person information with same Id in JSON format
     */
    @GetMapping("/get/{id}")
    public AddressBook getAddressById(@PathVariable Integer id) {
        Optional<AddressBook> addressBook = repository.findById(id);
        return addressBook.get();
    }

    /**
     * Ability to update address book person data for particular id
     * @param id - person id
     * @param addressBook - person data
     * @return - accepts the address book data in JSON format and updates the address book having same id from database
     */
    @PutMapping("/updateData/{id}")
    public AddressBook updateById(@PathVariable Integer id, @RequestBody AddressBook addressBook) {
        AddressBook newAddressBook = new AddressBook(addressBook, id);
        repository.save(newAddressBook);
        return newAddressBook;
    }

    /**
     * - Ability to delete person data for particular id
     * @param id - person id in address book
     * @return -person Id and Acknowledgment message
     */
    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Integer id) {
        repository.deleteById(id);
        return "Address of id: " + id + " has been deleted";
    }
}
