package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created AddressBookRepository class extending JpaRepository for CRUD operations and for some custom query methods
 * AddressBook - model class
 * Integer - primary key
 */
public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {
}