package ma.enset.springmvcspringdatajpathymeleaf.services;

import ma.enset.springmvcspringdatajpathymeleaf.entities.Appointment;
import ma.enset.springmvcspringdatajpathymeleaf.entities.Consultation;
import ma.enset.springmvcspringdatajpathymeleaf.entities.Doctor;
import ma.enset.springmvcspringdatajpathymeleaf.entities.Patient;

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
