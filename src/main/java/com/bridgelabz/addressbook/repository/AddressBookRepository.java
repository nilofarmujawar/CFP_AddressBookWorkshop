package com.bridgelabz.addressbook.repository;

import java.util.List;

import com.bridgelabz.addressbook.model.AddressBookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//Created AddressBookRepository extending JpaRepository so we can perform CRUD as well as can implement custom query methods
public interface AddressBookRepository extends JpaRepository<AddressBookData, Integer>{

    @Query(value="select * from address_book where city =:city",nativeQuery=true)
    public List<AddressBookData> findByCity(String city);
}