package ma.enset.ormjpahibernatespringdata.repositories;

import ma.enset.ormjpahibernatespringdata.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepo extends JpaRepository<Consultation,Long> {
}
