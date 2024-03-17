package ma.enset.springmvcspringdatajpathymeleaf.repositories;

import ma.enset.springmvcspringdatajpathymeleaf.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
}
