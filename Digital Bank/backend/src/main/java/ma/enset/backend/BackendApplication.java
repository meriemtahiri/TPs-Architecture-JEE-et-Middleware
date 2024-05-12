package ma.enset.backend;

import ma.enset.backend.DTOS.AccountDTO;
import ma.enset.backend.DTOS.CurrentAccountDTO;
import ma.enset.backend.DTOS.CustomerDTO;
import ma.enset.backend.DTOS.SavingAccountDTO;
import ma.enset.backend.ENTITIES.*;
import ma.enset.backend.ENUMS.AccountStatus;
import ma.enset.backend.ENUMS.OperationType;
import ma.enset.backend.EXCEPTIONS.CustomerNotFoundException;
import ma.enset.backend.REPOSITORIES.AccountRepo;
import ma.enset.backend.REPOSITORIES.CustomerRepo;
import ma.enset.backend.REPOSITORIES.OperationRepo;
import ma.enset.backend.SERVISIES.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AccountService accountService){
		return args -> {
			Stream.of("roum","Imane","Mohamed").forEach(name->{
				CustomerDTO customer=new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				accountService.saveCustomer(customer);
			});
			accountService.listCustomers().forEach(customer->{
				try {
					accountService.saveCurrentAccount(Math.random()*90000,9000,customer.getId());
					accountService.saveSavingAccount(Math.random()*120000,5.5,customer.getId());
				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				}
			});
			List<AccountDTO> accounts = accountService.accountList();
			for (AccountDTO account:accounts){
				for (int i = 0; i <10 ; i++) {
					String accountId;
					if(account instanceof SavingAccountDTO){
						accountId=((SavingAccountDTO) account).getId();
					} else{
						accountId=((CurrentAccountDTO) account).getId();
					}
					accountService.credit(accountId,10000+Math.random()*120000,"Credit");
					accountService.debit(accountId,1000+Math.random()*9000,"Debit");
				}
			}
		};
	}

	//@Bean
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

