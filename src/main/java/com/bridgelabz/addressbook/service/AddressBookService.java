package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.model.AddressBook;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Mark class as a service provider
 */
@Service
public class AddressBookService implements IAddressBookService {

    /**
     * Autowired  AddressBookRepository class to inject its dependency
     */
    @Autowired
    AddressBookRepository repository;


    /**
     * Created method name as saveAddrss which serve controllers api call to save record to repo
     * @param addressBookDTO - all data
     * @return -accepts the address book data in JSON format and stores it in DB
     */
    public AddressBook saveAddress(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook(addressBookDTO);
        repository.save(addressBook);
        return addressBook;
    }

    /**
     * Created method name aas getListOfAddresses which serves controllers api call to retrieve all records
     * @return - return all address book data in list format
     */
    public List<AddressBook> getListOfAddresses() {
        List<AddressBook> list = repository.findAll();
        return list;
    }

    /**
     * Created method name as getDataById which serves controllers api call to retrieve record by id
     * @param id - person id
     * @return - get person data by id in json format
     */
    public Optional<AddressBook> getDataById(Integer id) {
        Optional<AddressBook> newAddressBook = repository.findById(id);
        return newAddressBook;
    }

    /**
     * Created method name as updateDataById which serves controllers api call to update record by id
     * @param id - person id
     * @param addressBookDTO - all data
     * @return - update address book person data for particular id in json format
     */
    public AddressBook updateDateById(Integer id, AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook(id, addressBookDTO);
        repository.save(addressBook);
        return addressBook;
    }

    /**
     * Created method name as deleteContact which serves controllers api call to delete record by id
     * @param id - person id
     */
    public void deleteContact(Integer id) {
        repository.deleteById(id);
    }

}