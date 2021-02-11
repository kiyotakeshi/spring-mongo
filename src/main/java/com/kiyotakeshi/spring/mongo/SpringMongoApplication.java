package com.kiyotakeshi.spring.mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMongoApplication implements CommandLineRunner {

	private final CustomerRepository repository;

	public SpringMongoApplication(CustomerRepository customerRepository) {
		this.repository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		this.repository.deleteAll();
//		repository.save(new Customer("Mike", "Popcorn"));
//		repository.save(new Customer("Sam", "Smith"));
//		repository.save(new Customer("Alice", "Smith"));

		System.out.println("\n------------------------------");
		System.out.println("Customers found with findAll()");
		for (Customer customer : repository.findAll()){
			System.out.println(customer);
		}

		// fetch an individual customer
		System.out.println("\n--------------------------------");
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("\n--------------------------------");
		System.out.println("Customers found with findByLastName('Smith'):");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
	}
}
