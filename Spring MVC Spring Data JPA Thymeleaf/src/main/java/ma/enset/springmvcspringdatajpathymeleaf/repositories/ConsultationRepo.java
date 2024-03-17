package ma.enset.springmvcspringdatajpathymeleaf.repositories;

import ma.enset.springmvcspringdatajpathymeleaf.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepo extends JpaRepository<Consultation,Long> {
}
