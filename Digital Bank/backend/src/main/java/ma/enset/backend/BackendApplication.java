package ma.enset.backend;

import ma.enset.backend.ENTITIES.CurrentAccount;
import ma.enset.backend.ENTITIES.Customer;
import ma.enset.backend.ENTITIES.Operation;
import ma.enset.backend.ENTITIES.SavingAccount;
import ma.enset.backend.ENUMS.AccountStatus;
import ma.enset.backend.ENUMS.OperationType;
import ma.enset.backend.REPOSITORIES.AccountRepo;
import ma.enset.backend.REPOSITORIES.CustomerRepo;
import ma.enset.backend.REPOSITORIES.OperationRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
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
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name + "@gmail.com");
				customerRepo.save(customer);
			});
			customerRepo.findAll().forEach(customer->{
				CurrentAccount currentAccount=new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(customer);
				currentAccount.setOverDraft(9000);
				accountRepo.save(currentAccount);

				SavingAccount savingAccount=new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(customer);
				savingAccount.setInterestRate(5.5);
				accountRepo.save(savingAccount);
			});
			accountRepo.findAll().forEach(acc->{
				for (int i = 0; i <3 ; i++) {
					Operation accountOperation=new Operation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*12000);
					accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
					accountOperation.setBankAccount(acc);
					operationRepo.save(accountOperation);
				}

			});
		};
	}
}

