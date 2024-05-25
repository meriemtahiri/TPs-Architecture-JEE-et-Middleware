package ma.enset.backend.SECURITY.repositories;

import ma.enset.backend.SECURITY.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface roleRepo extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
