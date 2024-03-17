package ma.enset.springmvcspringdatajpathymeleaf.repositories;

import ma.enset.springmvcspringdatajpathymeleaf.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,String> {
}
