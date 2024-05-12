package ma.enset.backend.WEB;

import ma.enset.backend.DTOS.AccountDTO;
import ma.enset.backend.DTOS.AccountHistoryDTO;
import ma.enset.backend.DTOS.OperationDTO;
import ma.enset.backend.EXCEPTIONS.AccountNotFoundException;
import ma.enset.backend.SERVISIES.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AccountRestAPI {
    private AccountService accountService;

    public AccountRestAPI(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{accountId}")
    public AccountDTO getAccount(@PathVariable String accountId) throws AccountNotFoundException {
        return accountService.getAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<AccountDTO> listAccounts(){
        return accountService.accountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<OperationDTO> getHistory(@PathVariable String accountId){
        return accountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "5")int size) throws AccountNotFoundException {
        return accountService.getAccountHistory(accountId,page,size);
    }
}
