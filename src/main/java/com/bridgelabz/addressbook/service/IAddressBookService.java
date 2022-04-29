package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.model.AddressBookData;

import java.util.List;
import java.util.Optional;


//Created interface for all service methods so we can achieve abstraction
import java.util.List;

/**
 * Here we create an interface having some methods which we implement in AddressBookService .
 * getAddressBookData:- in this we retrieve all records from database.
 * getAddressBookDataById:-in this we retrieve records from database for particular id.
 * createAddressBookData:-in this we create data and store that data into our database.
 * updateAddressBookData:-in this method we take id of a particular contact and then passes the info which we have to update and then store in db
 * deleteAddressBookData:-take id of an contact and delete that record from the database.
 */
public interface IAddressBookService {

    //save data to repository
    String createAddressBookData(AddressBookDTO addressBookDTO);

    //get All Data from token
    List<AddressBookData> getAddressBookDataByToken(String token);


    //get records created for particular id by generating token for that id
    AddressBookData getRecordByToken(String token);


    //update records by providing token generated for particular id
    AddressBookData updateRecordByToken(String token, AddressBookDTO addressBookDTO);



    //deleted records by token
    AddressBookData deleteRecordByToken(String token);
}
