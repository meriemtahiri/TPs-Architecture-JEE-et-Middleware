package ma.enset.backend.SERVISIES;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.backend.ENTITIES.*;
import ma.enset.backend.ENUMS.OperationType;
import ma.enset.backend.REPOSITORIES.AccountRepo;
import ma.enset.backend.REPOSITORIES.CustomerRepo;
import ma.enset.backend.REPOSITORIES.OperationRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private AccountRepo accountRepo;
    private CustomerRepo customerRepo;
    private OperationRepo operationRepo;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public CurrentAccount saveCurrentAccount(double initialBalance, double overDraft, Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if(customer==null) System.out.println("customer not found");
        CurrentAccount currentAccount=new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        return accountRepo.save(currentAccount);
    }

    @Override
    public SavingAccount saveSavingAccount(double initialBalance, double interestRate, Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if(customer==null) System.out.println("customer not found!");
        SavingAccount savingAccount=new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        return accountRepo.save(savingAccount);    }

    @Override
    public List<Customer> listCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Account getAccount(String accountId) {
        Account account = accountRepo.findById(accountId).orElse(null);
        if(account==null) System.out.println("Account not found!");
        return account;
    }

    @Override
    public void debit(String accountId, double amount, String description) {
        Account account = getAccount(accountId);
        if(account.getBalance()<amount) System.out.println("Balance not sufficient");
        Operation accountOperation=new Operation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(account);
        operationRepo.save(accountOperation);
        account.setBalance(account.getBalance()-amount);
        accountRepo.save(account);
    }

    @Override
    public void credit(String accountId, double amount, String description) {
        Account account = getAccount(accountId);
        if(account.getBalance()<amount) System.out.println("Balance not sufficient");
        Operation operation=new Operation();
        operation.setType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setBankAccount(account);
        operationRepo.save(operation);
        account.setBalance(account.getBalance()+amount);
        accountRepo.save(account);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) {
        debit(accountIdSource,amount,"Transfer to "+accountIdDestination);
        credit(accountIdDestination,amount,"Transfer from "+accountIdSource);
    }
}
