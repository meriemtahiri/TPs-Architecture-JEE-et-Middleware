package ma.enset.backend.REPOSITORIES;


import ma.enset.backend.ENTITIES.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account,String> {
}
