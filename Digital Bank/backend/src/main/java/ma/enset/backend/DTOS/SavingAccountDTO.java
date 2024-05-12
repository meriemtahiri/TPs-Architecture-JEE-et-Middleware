package ma.enset.backend.DTOS;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.enset.backend.ENUMS.AccountStatus;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class SavingAccountDTO extends AccountDTO{
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}
