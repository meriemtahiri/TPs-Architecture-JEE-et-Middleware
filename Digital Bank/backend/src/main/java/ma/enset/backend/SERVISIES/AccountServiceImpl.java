package ma.enset.backend.SERVISIES;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.backend.DTOS.*;
import ma.enset.backend.ENTITIES.*;
import ma.enset.backend.ENUMS.OperationType;
import ma.enset.backend.EXCEPTIONS.AccountNotFoundException;
import ma.enset.backend.EXCEPTIONS.BalanceNotSufficientException;
import ma.enset.backend.EXCEPTIONS.CustomerNotFoundException;
import ma.enset.backend.MAPPERS.AccountMapperImpl;
import ma.enset.backend.REPOSITORIES.AccountRepo;
import ma.enset.backend.REPOSITORIES.CustomerRepo;
import ma.enset.backend.REPOSITORIES.OperationRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private AccountRepo accountRepo;
    private CustomerRepo customerRepo;
    private OperationRepo operationRepo;
    private AccountMapperImpl accountMapper;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        return accountMapper.fromCustomer(customerRepo.save(accountMapper.fromCustomerDTO(customerDTO)));
    }
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        return accountMapper.fromCustomer(customerRepo.save(accountMapper.fromCustomerDTO(customerDTO)));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepo.deleteById(customerId);
    }

    @Override
    public CurrentAccountDTO saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if(customer==null) throw new CustomerNotFoundException("Customer not found!");
        CurrentAccount currentAccount=new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        return accountMapper.fromCurrentAccount(accountRepo.save(currentAccount));
    }

    @Override
    public SavingAccountDTO saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if(customer==null) throw new CustomerNotFoundException("Customer not found!");
        SavingAccount savingAccount=new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        return accountMapper.fromSavingAccount(accountRepo.save(savingAccount));
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepo.findAll()
                .stream().map(customer -> accountMapper.fromCustomer(customer))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO getAccount(String accountId) throws AccountNotFoundException {
        Account account=accountRepo.findById(accountId)
                .orElseThrow(()->new AccountNotFoundException("Account not found"));
        if(account instanceof SavingAccount savingAccount){
            return accountMapper.fromSavingAccount(savingAccount);
        } else {
            CurrentAccount currentAccount= (CurrentAccount) account;
            return accountMapper.fromCurrentAccount(currentAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException {
        Account account=accountRepo.findById(accountId)
                .orElseThrow(()->new AccountNotFoundException("Account not found"));
        if(account.getBalance()<amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
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
    public void credit(String accountId, double amount, String description) throws AccountNotFoundException {
        Account account=accountRepo.findById(accountId)
                .orElseThrow(()->new AccountNotFoundException("Account not found"));
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
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource,amount,"Transfer to "+accountIdDestination);
        credit(accountIdDestination,amount,"Transfer from "+accountIdSource);
    }

    @Override
    public List<AccountDTO> accountList() {
        return accountRepo.findAll().stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount savingAccount) {
                return accountMapper.fromSavingAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return accountMapper.fromCurrentAccount(currentAccount);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        return accountMapper.fromCustomer(customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer Not found")));
    }

    @Override
    public List<OperationDTO> accountHistory(String accountId) {
        return operationRepo.findByBankAccountId(accountId).stream().map(opr -> accountMapper.fromOperation(opr))
                .collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws AccountNotFoundException {
        Account account=accountRepo.findById(accountId).orElse(null);
        if(account==null) throw new AccountNotFoundException("Account not Found");
        Page<Operation> accountOperations = operationRepo.findByBankAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO=new AccountHistoryDTO();
        List<OperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> accountMapper.fromOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setAccountId(account.getId());
        accountHistoryDTO.setBalance(account.getBalance());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }
}
