package com.bridgelabz.addressbook;
/**
 * import classes
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * create a class name as AddressBookAppApplication
 */
@SpringBootApplication
@Slf4j
@EnableSwagger2
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
	@Bean
	public Docket swaggerconfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.bridgelabz.addressbook"))
				.build()
				.apiInfo(getApiInfo());

	}
	private ApiInfo getApiInfo() {
		springfox.documentation.service.Contact contact = new Contact("Demo Project API", "http://brigelbaz.com", "nilofarmujwar1118@gmail.com");
		return new ApiInfoBuilder().title("Demo Service Swagger API")
				.description("Demo Service Swagger API for Learning Swagger").version("0.0.1.SNAPSHOT")
				.license("Apache 2.0").licenseUrl(" http://www.apache.org/licenses/LICENSE-2.0").contact(contact)
				.build();
	}
}