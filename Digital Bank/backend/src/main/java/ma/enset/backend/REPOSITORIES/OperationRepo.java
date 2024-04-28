package ma.enset.backend.REPOSITORIES;

import ma.enset.backend.ENTITIES.Operation;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepo extends JpaRepository<Operation,Long> {
    List<Operation> findByBankAccountId(String accountId);
    //Page<Operation> findByBankAccountIdOrderByOperationDateDesc(String accountId, Pageable pageable);
}
