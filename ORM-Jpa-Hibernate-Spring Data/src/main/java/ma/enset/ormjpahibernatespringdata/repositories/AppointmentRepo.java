package ma.enset.ormjpahibernatespringdata.repositories;

import ma.enset.ormjpahibernatespringdata.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
}
