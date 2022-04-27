package com.bridgelabz.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookAppApplication {

	public static void main(String[] args) {
		System.out.println("Welcome in address book program");
		SpringApplication.run(AddressBookAppApplication.class, args);
	}

}
