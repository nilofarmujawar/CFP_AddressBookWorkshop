package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.AddressBook;
import com.bridgelabz.addressbook.repository.AddressBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

/**
 * Created AddressBookService class to serve api calls done by controller layer
 */
public class AddressBookService implements IAddressBookService {

    /**
     * Autowired AddressBookRepository interface to inject its dependency here
     */
    @Autowired
    AddressBookRepository repository;

    /**
     * create a getWelcome method
     * @return - welcome msg
     */
    public String getWelcome() {
        return "Welcome to Employee Payroll !!!";
    }

    /**
     * Created method name as postDataToRepo which serve controllers api call to save record to repo
     * @param addressBookDTO - all data
     * @return -accepts the address book data in JSON format and stores it in DB
     */
    public AddressBook postDataToRepo(AddressBookDTO addressBookDTO) {
        AddressBook  newAddressBook  = new AddressBook (addressBookDTO);
        repository.save(newAddressBook );
        return newAddressBook ;
    }

    /**
     * Created method name aas getAllData which serves controllers api call to retrieve all records
     * @return - return all address book data in list format
     */
    public List<AddressBook > getAllData() {
        List<AddressBook > list = repository.findAll();
        return list;
    }

    /**
     * Created method name as getDataById which serves controllers api call to retrieve record by id
     * @param id - person id
     * @return - get person data by id in json format
     */
    public AddressBook getDataById(Integer id) {
        Optional<AddressBook> newEmployee = repository.findById(id);
        if (newEmployee.isPresent()) {
            return newEmployee.get();
        } else throw new AddressBookException("Employee id not found");
    }

    /**
     * Created method name as updateDataById which serves controllers api call to update record by id
     * @param id - person id
     * @param addressBookDTO - all data
     * @return - update address book person data for particular id in json format
     */
    public AddressBook updateDataById(Integer id, AddressBookDTO addressBookDTO) {
        Optional<AddressBook> newEmployee = repository.findById(id);
        if (newEmployee.isPresent()) {
            AddressBook addressBook = new AddressBook(id, addressBookDTO);
            repository.save(addressBook);
            return addressBook;
        } else {
            throw new AddressBookException("Employee Not found");
        }
    }


    /**
     * Created method name as deleteDataById which serves controllers api call to delete record by id
     * @param id - person id
     */
    public String deleteDataById(Integer id) {
        Optional<AddressBook> newEmployee = repository.findById(id);
        if (newEmployee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new AddressBookException("Employee Details not found");
        }
        return null;
    }
}




