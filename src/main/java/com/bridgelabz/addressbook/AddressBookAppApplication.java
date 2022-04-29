package com.bridgelabz.addressbook;
/**
 * import classes
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * create a class name as AddressBookAppApplication
 */
@SpringBootApplication
@Slf4j
public class AddressBookAppApplication {

	/**
	 * create a main method,all program execute in main method
	 * @param args - no arguments its default
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication
				.run(AddressBookAppApplication.class, args);

		log.info("AddressBook app started in  {} Environment", context.getEnvironment().getProperty("environment"));
	}
}