package ma.enset.ormjpahibernatespringdata.web;

import ma.enset.ormjpahibernatespringdata.entities.Patient;
import ma.enset.ormjpahibernatespringdata.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductWebService {
    @Autowired
    private PatientRepo patientRepo;

    @GetMapping("/patients")
    List<Patient> products(){
        return patientRepo.findAll();
    }
    @GetMapping("/patients/{id}")
    Patient findById(@PathVariable Long id){
        return patientRepo.findById(id).orElse(null);
    }
}
