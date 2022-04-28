package com.bridgelabz.addressbook.controller;

/**
 * import classes
 */
import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.dto.ResponseDTO;
import com.bridgelabz.addressbook.model.AddressBook;
import com.bridgelabz.addressbook.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * Autowired AddressBookService so we can inject its dependency here
     */
    @Autowired
    IAddressBookService service;

    /**
     * - Ability to display welcome message
     * @return- welcome msg
     */
    @GetMapping("")
    public String welcomeUser() {
        return "Welcome to  address book app development";
    }
    /**
     * - Ability to get all address book data by findAll() method
     * @return :- showing all data
     */
    @GetMapping("/get")
    public ResponseEntity<String> getAllData() {
        List<AddressBook> listOfContacts = service.getListOfAddresses();
        ResponseDTO response = new ResponseDTO("Address book :", listOfContacts);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Create api call to save data to repository
     * @param addressBookDTO - person all data
     * @return- accepts the address book data in JSON format and stores it in DB
     */
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> postData(@RequestBody AddressBookDTO addressBookDTO) {
        AddressBook newContact = service.saveAddress(addressBookDTO);
        ResponseDTO response = new ResponseDTO("New Contact Added in Address book : ", newContact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    /**
     * Ability to get person data by id
     * @param id - person id
     * @return - get person information with same Id in JSON format
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<AddressBook> getDataFromRepoById(@PathVariable Integer id) {
        Optional<AddressBook> addressBook = service.getDataById(id);
        ResponseDTO dto = new ResponseDTO("Data",addressBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * Ability to update address book person data for particular id
     * @param id - person id
     * @param addressBookDTO - person data
     * @return - accepts the address book data in JSON format and updates the address book having same id from database
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable Integer id, @RequestBody AddressBookDTO addressBookDTO) {
        AddressBook newContact = service.updateDateById(id, addressBookDTO);
        ResponseDTO response = new ResponseDTO("Addressbook updated : ", newContact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    /**
     * - Ability to delete person data for particular id
     * @param id - person id in address book
     * @return -person Id and Acknowledgment message
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDataById(@PathVariable Integer id) {
        service.deleteContact(id);
        return new ResponseEntity<String>("Contact deleted succesfully", HttpStatus.OK);
    }
}