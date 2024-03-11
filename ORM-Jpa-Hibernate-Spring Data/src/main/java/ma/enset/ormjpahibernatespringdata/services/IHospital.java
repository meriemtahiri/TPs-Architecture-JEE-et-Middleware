package ma.enset.ormjpahibernatespringdata.services;

import ma.enset.ormjpahibernatespringdata.entities.Appointment;
import ma.enset.ormjpahibernatespringdata.entities.Consultation;
import ma.enset.ormjpahibernatespringdata.entities.Doctor;
import ma.enset.ormjpahibernatespringdata.entities.Patient;

public interface IHospital {
    Patient savePatient(Patient patient);

    Doctor saveDoctor(Doctor doctor);

    Appointment saveAppointment(Appointment appointment);

    Consultation saveConsultation(Consultation consultation);

    Patient findPatientById(Long id);
    Doctor findDoctorById(String id);
    Appointment findAppointmentById(Long id);
    Consultation findConsultationById(Long id);
}
