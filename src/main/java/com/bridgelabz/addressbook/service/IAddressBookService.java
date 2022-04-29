package com.bridgelabz.addressbook.service;


import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.model.AddressBook;

import java.util.List;

/**
 * Created IAddressBookService interface to achieve abstraction
 */
public interface IAddressBookService {

    public String getWelcome();

    public AddressBook postDataToRepo(AddressBookDTO addressBookDTO);

    public List<AddressBook> getAllData();

    public AddressBook getDataById(Integer id);

    public AddressBook updateDataById(Integer id, AddressBookDTO addressBookDTO);

    public String deleteDataById(Integer id);
}