package ma.enset.backend;

import ma.enset.backend.ENTITIES.Customer;
import ma.enset.backend.REPOSITORIES.AccountRepo;
import ma.enset.backend.REPOSITORIES.CustomerRepo;
import ma.enset.backend.REPOSITORIES.OperationRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

	}
	@Bean
	CommandLineRunner commandLineRunner(AccountRepo accountRepo, CustomerRepo customerRepo, OperationRepo operationRepo) {
		return args -> {
			Stream.of("Meriem", "Zineb", "Mohamed").forEach(name -> {
				Customer customer = new Customer();? §B
				customer.setName(name);
				customer.setEmail(name + "@gmail.com");
				customerRepo.save(customer);
			});
		};
	}
}

