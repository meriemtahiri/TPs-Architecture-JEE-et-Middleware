package ma.enset.ormjpahibernatespringdata.repositories;

import ma.enset.ormjpahibernatespringdata.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,String> {
}
