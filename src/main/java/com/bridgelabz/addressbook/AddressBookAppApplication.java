package com.bridgelabz.addressbook;
/**
 * import classes
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * create a class name as AddressBookAppApplication
 */
@SpringBootApplication
public class AddressBookAppApplication {

	/**
	 * create a main method,all program execute in main method
	 * @param args - no arguments its default
	 */
	public static void main(String[] args) {
		/**
		 * display welcome msg on console
		 */
		System.out.println("Welcome to address book program ");
		SpringApplication.run(AddressBookAppApplication.class, args);
	}

}