package ma.enset.ormjpahibernatespringdata;

import ma.enset.ormjpahibernatespringdata.entities.Patient;
import ma.enset.ormjpahibernatespringdata.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

//@SpringBootApplication
public class OrmJpaHibernateSpringDataApplication implements CommandLineRunner {
	//@Autowired
	private PatientRepo patientRepo;
	public static void main(String[] args) {
		SpringApplication.run(OrmJpaHibernateSpringDataApplication.class, args);
	}

	public void run (String... args){

		// Ajouter des patients
			/*for (long i=4 ; i<=6 ; i++){
				patientRepo.save(new Patient(null, "patient"+i, new Date(), new Random().nextBoolean(), new Random().nextInt(101)));
				patients.add(patientRepo.findById(i).orElse(null));
			}*/

		// Consulter tous les patients
		/*System.out.println("Tous les patients : ");
		Iterable<Patient> patients = patientRepo.findAll();
		patients.forEach(System.out::println);*/

		// Consulter un patient
		/*System.out.println("Patient avec l'ID 1 : ");
		Patient retrievedPatient = patientRepo.findById(1L).orElse(null);
		System.out.println(retrievedPatient);*/

		// Chercher des patients
		System.out.println("Patients malades : ");
		Iterable<Patient> sickPatients = patientRepo.search(true);
		sickPatients.forEach(System.out::println);

		// Mettre à jour un patient
		/*System.out.println("Mise à jour du patient avec l'ID 1 : ");
		if (retrievedPatient != null) {
			retrievedPatient.setScore(90);
			patientRepo.save(retrievedPatient);
			System.out.println(patientRepo.findById(1L).orElse(null));
		}*/

		// Supprimer un patient
		/*System.out.println("Suppression du patient avec l'ID 2 : ");
		patientRepo.deleteById(2L);
		patients = patientRepo.findAll();
		patients.forEach(System.out::println);*/
	}
}