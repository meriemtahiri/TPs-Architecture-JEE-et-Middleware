package ma.enset.backend.SECURITY.repositories;

import ma.enset.backend.SECURITY.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}
