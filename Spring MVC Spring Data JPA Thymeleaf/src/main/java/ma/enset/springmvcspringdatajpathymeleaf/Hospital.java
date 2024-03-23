package ma.enset.springmvcspringdatajpathymeleaf;

import ma.enset.springmvcspringdatajpathymeleaf.entities.*;
import ma.enset.springmvcspringdatajpathymeleaf.repositories.DoctorRepo;
import ma.enset.springmvcspringdatajpathymeleaf.services.HospitalImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class Hospital {
    public static void main(String[] args) {
        SpringApplication.run(Hospital.class, args);
    }
    //@Bean
    CommandLineRunner start(HospitalImpl hospital , DoctorRepo doctorRepo){
        return args -> {
            for (long i=1 ; i<=3 ; i++){
                Patient patient = new Patient();
                patient.setName("patient"+i);
                patient.setBirthday(new Date());
                patient.setSick(new Random().nextBoolean());
                patient.setScore(new Random().nextInt(101));
                hospital.savePatient(patient);

                Doctor doctor = new Doctor();
                doctor.setName("doctor"+i);
                doctor.setEmail("email"+i);
                doctor.setSpeciality("specialty"+i);
                hospital.saveDoctor(doctor);
            }

            Appointment appointment = new Appointment();
            appointment.setPatient(hospital.findPatientById(1L));
            appointment.setDoctor(doctorRepo.findAll().get(1));
            appointment.setDate(new Date());
            appointment.setStatus(Status.PENDING);
            hospital.saveAppointment(appointment);

            Consultation consultation = new Consultation();
            consultation.setAppointment(hospital.findAppointmentById(1L));
            consultation.setDateConsultation(hospital.findAppointmentById(1L).getDate());
            consultation.setRapport("Rapport1");
            hospital.saveConsultation(consultation);

        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
