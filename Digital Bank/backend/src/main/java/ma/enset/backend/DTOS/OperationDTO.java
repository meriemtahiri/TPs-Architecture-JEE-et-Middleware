package ma.enset.backend.DTOS;

import lombok.Data;
import ma.enset.backend.ENUMS.OperationType;

import java.util.Date;

@Data
public class OperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
