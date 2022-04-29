package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.AddressBookData;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import com.bridgelabz.addressbook.util.EmailSenderService;
import com.bridgelabz.addressbook.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    EmailSenderService sender;


    /**
     * accepts the contact data in the form of AddressBookDTO and stores it in DB
     * @param addressBookDTO - represents object of AddressBookDto class
     * @return accepted contact details information in JSON format
     * @token :-represent id
     */
    public String createAddressBookData(AddressBookDTO addressBookDTO) {
        AddressBookData newAddress = new AddressBookData(addressBookDTO);
        addressBookRepository.save(newAddress);
        String token = tokenUtility.createToken(newAddress.getId());
        sender.sendEmail(newAddress.getEmail(), "Test Email", "Registered SuccessFully, hii: "
                +newAddress.getFirstName()+"Please Click here to get data-> "
                +"http://localhost:8080/addressBook/retrieve/"+token);
        return token;
    }


    /**
     * accepts the contact data in the form of AddressBookDTO and
     * updates the contact details having same Id from database
     * @param token - represents contact details for same id
     * @param addressBookDTO- represents object of AddressBookDTO class
     * @return	updated Address Book information in JSON format
     *Created service method which serves controller api to update record by token
     */
    public AddressBookData updateRecordByToken(String token, AddressBookDTO addressBookDTO) {
        Integer id= tokenUtility.decodeToken(token);
        Optional<AddressBookData> addressBook = addressBookRepository.findById(id);
        if(addressBook.isPresent()) {
            throw new AddressBookException("Address Book Details for id not found");
        }
        AddressBookData newBook = new AddressBookData(id,addressBookDTO);
        addressBookRepository.save(newBook);
        sender.sendEmail(newBook.getEmail(), "Test Email", "Updated SuccessFully, hii: "
                +newBook.getFirstName()+"Please Click here to get data of updated id-> "
                +"http://localhost:8081/addressBook/retrieve/"+token);
        return newBook;
    }

    /**accepts the contact Id and deletes the data of that contact from DB
     * @param token - represents contact id
     * @return Id and Acknowledgment message
     */

    public AddressBookData deleteRecordByToken(String token) {
        Integer id = tokenUtility.decodeToken(token);
        Optional<AddressBookData> addressBook = addressBookRepository.findById(id);
        if(addressBook.isPresent())
        {
            addressBookRepository.deleteById(id);
            sender.sendEmail("nilofarmujawar1118@gmail.com", "Test Email", "Deleted SuccessFully, hii: "
                    +addressBook.get()+"Please Click here to get data-> "
                    +"http://localhost:8081/addressBook/retrieve/"+token);
        }
        else {
            log.warn("Unable to find address book details for given id: "+id);
            throw new AddressBookException("Address Book Details not found");
        }
        return null;
    }
    /**
     * getAll AddressBook list by token
     * @return list of contact information from DB by validating token first
     * @token :-represent id
     */

    public List<AddressBookData> getAddressBookDataByToken(String token)
    {
        int id=tokenUtility.decodeToken(token);
        Optional<AddressBookData> isContactPresent=addressBookRepository.findById(id);
        if(isContactPresent.isPresent()) {
            List<AddressBookData> listAddressBook=addressBookRepository.findAll();
            sender.sendEmail("nilofarmujawar1118@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +isContactPresent.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8081/addressBook/retrieve/"+token);
            return listAddressBook;
        }else {
            System.out.println("Exception ...Token not found!");
            return null;	}
    }

    /**
     *
     * @param token is generated for all Address Book Data and it's unique for all contact.
     * @return AddressBookData for particular token id
     * @token :-represent id
     */
    public AddressBookData getRecordByToken(String token){
        Integer id = tokenUtility.decodeToken(token);
        Optional<AddressBookData> newAddressBook = addressBookRepository.findById(id);
        if(newAddressBook.isPresent()) {
            sender.sendEmail("nilofarmujawar1118@gmail.com", "Test Email", "Deleted SuccessFully, hii: "
                    +newAddressBook.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8081/addressBook/retrieve/"+token);

            return newAddressBook.get();
        }
        else {

            log.warn("Unable to find address book details for given id: "+id);
            throw new AddressBookException("Address Book Details not found for that particular id");

        }

    }

}