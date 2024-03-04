package ma.enset.ormjpahibernatespringdata.repositories;

import ma.enset.ormjpahibernatespringdata.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,Long> {
    List<Patient> findBySick(boolean sick);
}
