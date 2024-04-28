package ma.enset.backend.ENTITIES;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.ENUMS.OperationType;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    private Account bankAccount;
    private String description;
}
