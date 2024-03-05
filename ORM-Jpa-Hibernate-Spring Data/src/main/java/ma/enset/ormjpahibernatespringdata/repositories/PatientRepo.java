package ma.enset.ormjpahibernatespringdata.repositories;

import ma.enset.ormjpahibernatespringdata.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,Long> {
            List<Patient> findBySick(boolean sick);
            @Query("select p from Patient p where p.sick= :x") // findBySick <==> search
            List<Patient> search(@Param("x") boolean b);
}
