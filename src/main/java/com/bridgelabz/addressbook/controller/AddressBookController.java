package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.dto.ResponseDTO;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.AddressBookData;
import com.bridgelabz.addressbook.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Created controller so that we can perform rest api calls
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;


    /**
     * create record
     * @apiNote accepts the  data in JSON format and stores it in DB
     * @param addressBookDTO - represents object of AddressBookDTO class
     * @return accepted address information in JSON format
     */
    @PostMapping(path = "/create")
    public ResponseEntity<String> addAddressBookData(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        String newContact = addressBookService.createAddressBookData(addressBookDTO);
        ResponseDTO respDTO = new ResponseDTO("New Contact Added in AddressBook ", newContact);
        return new ResponseEntity (respDTO, HttpStatus.CREATED);
    }


    /**
     *get all data by using token
     * @param token:-generated for id
     * @return fields with Http status
     */
    @GetMapping(value = "/retrieve/{token}")
    public ResponseEntity<ResponseDTO> getAddressBookDataById(@PathVariable String token)
    {
        List<AddressBookData> listOfContacts = addressBookService.getAddressBookDataByToken(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfContacts);
        return new ResponseEntity(dto,HttpStatus.OK);
    }


    /**
     * get data for particular id
     * Ability to get a record by token
     */
    @GetMapping("/get/{token}")
    public ResponseEntity<String> getRecordById(@PathVariable String token) throws AddressBookException {
        AddressBookData newAddressBook = addressBookService.getRecordByToken(token);
        ResponseDTO dto = new ResponseDTO("Address Book Record for particular id retrieved successfully",newAddressBook);
        return new ResponseEntity(dto,HttpStatus.OK);
    }


    /**
     * update  record data by token
     * @apiNote accepts the address book data in JSON format and updates the address having same Id from database
     * @param token - represents addressBook id
     * @param addressBookDTO - represents object of AddressBookDto class
     * @return	updated address information in JSON format
     */


    @PutMapping("/update/{token}")
    public ResponseEntity<String> updateRecordById(@PathVariable String token,@Valid @RequestBody AddressBookDTO addressBookDTO){
        AddressBookData entity = addressBookService.updateRecordByToken(token,addressBookDTO);
        ResponseDTO dto = new ResponseDTO("Address Book Record updated successfully",entity);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    /**
     * delete records from database using token
     * @apiNote accepts the Id and deletes the data of that specific from DB
     * @return Id and Acknowledgment message
     */

    @DeleteMapping("/delete/{token}")
    public ResponseEntity<String> deleteRecordById(@PathVariable String token){
        ResponseDTO dto = new ResponseDTO("Address Book Record deleted successfully",addressBookService.deleteRecordByToken(token));
        return new ResponseEntity(dto,HttpStatus.OK);
    } 
}
