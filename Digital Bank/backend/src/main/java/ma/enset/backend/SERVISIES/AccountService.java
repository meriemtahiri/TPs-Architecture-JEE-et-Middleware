package ma.enset.backend.SERVISIES;

import ma.enset.backend.ENTITIES.Account;
import ma.enset.backend.ENTITIES.CurrentAccount;
import ma.enset.backend.ENTITIES.Customer;
import ma.enset.backend.ENTITIES.SavingAccount;

import java.util.List;

public interface AccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentAccount(double initialBalance, double overDraft, Long customerId) ;
    SavingAccount saveSavingAccount(double initialBalance, double interestRate, Long customerId) ;
    List<Customer> listCustomers();
    Account getAccount(String accountId) ;
    void debit(String accountId, double amount, String description) ;
    void credit(String accountId, double amount, String description) ;
    void transfer(String accountIdSource, String accountIdDestination, double amount) ;

}
