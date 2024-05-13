package ma.enset.backend.SERVISIES;

import ma.enset.backend.DTOS.*;
import ma.enset.backend.EXCEPTIONS.AccountNotFoundException;
import ma.enset.backend.EXCEPTIONS.BalanceNotSufficientException;
import ma.enset.backend.EXCEPTIONS.CustomerNotFoundException;

import java.util.List;

public interface AccountService {
    List<CustomerDTO> searchCustomers(String keyword);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);
    CurrentAccountDTO saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccountDTO saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    AccountDTO getAccount(String accountId) throws AccountNotFoundException;
    void debit(String accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException;

    List<AccountDTO> accountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    List<OperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws AccountNotFoundException;
}
