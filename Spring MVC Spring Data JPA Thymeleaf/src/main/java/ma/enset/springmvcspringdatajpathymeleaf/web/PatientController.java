package ma.enset.springmvcspringdatajpathymeleaf.web;

import lombok.AllArgsConstructor;
import ma.enset.springmvcspringdatajpathymeleaf.entities.Patient;
import ma.enset.springmvcspringdatajpathymeleaf.repositories.PatientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller @AllArgsConstructor
public class PatientController {
    private PatientRepo patientRepo;

    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0")int p,
                        @RequestParam(name = "size", defaultValue = "3")int s,
                        @RequestParam(name = "keyword", defaultValue = "")String k
                        )
    {
        Page<Patient> patientPage = patientRepo.findByNameContaining(k,PageRequest.of(p,s));
        model.addAttribute("patients", patientPage.getContent());
        model.addAttribute("pages",new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",k);

        return "listPatients";
    }
    @GetMapping("/admin/delete")
    public String delete(Long id,String keyword, int page){
        patientRepo.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/formAddPatients")
    public String addPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formAddPatients";
    }
    @PostMapping(path = "/admin/save")
    public String save(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @Validated Patient patient,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "formAddPatients";
        patientRepo.save(patient);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;

    }
    @GetMapping("/admin/updatePatient")
    public String updatePatient(Model model, Long id, String keyword, int page) {
        Patient patient = patientRepo.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException("unexciting patient");
        model.addAttribute("patient", patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "updatePatient";
    }
    @GetMapping(path = "/")
    public String home() {
        return "home";
    }

}
