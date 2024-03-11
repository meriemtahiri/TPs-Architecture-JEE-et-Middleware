package ma.enset.ormjpahibernatespringdata.services;

import jakarta.transaction.Transactional;
import ma.enset.ormjpahibernatespringdata.entities.Appointment;
import ma.enset.ormjpahibernatespringdata.entities.Consultation;
import ma.enset.ormjpahibernatespringdata.entities.Doctor;
import ma.enset.ormjpahibernatespringdata.entities.Patient;
import ma.enset.ormjpahibernatespringdata.repositories.AppointmentRepo;
import ma.enset.ormjpahibernatespringdata.repositories.ConsultationRepo;
import ma.enset.ormjpahibernatespringdata.repositories.DoctorRepo;
import ma.enset.ormjpahibernatespringdata.repositories.PatientRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service @Transactional
public class HospitalImpl implements IHospital {
    PatientRepo patientRepo;
    DoctorRepo doctorRepo;
    AppointmentRepo appointmentRepo;
    ConsultationRepo consultationRepo;

    public HospitalImpl(PatientRepo patientRepo, DoctorRepo doctorRepo, AppointmentRepo appointmentRepo, ConsultationRepo consultationRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.appointmentRepo = appointmentRepo;
        this.consultationRepo = consultationRepo;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepo.save(patient);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        doctor.setId(UUID.randomUUID().toString());
        return doctorRepo.save(doctor);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepo.save(consultation);
    }

    @Override
    public Patient findPatientById(Long id) {
        return patientRepo.findById(id).orElse(null);
    }

    @Override
    public Doctor findDoctorById(String id) {
        return doctorRepo.findById(id).orElse(null);
    }

    @Override
    public Appointment findAppointmentById(Long id) {
        return appointmentRepo.findById(id).orElse(null);
    }

    @Override
    public Consultation findConsultationById(Long id) {
        return consultationRepo.findById(id).orElse(null);
    }
}
