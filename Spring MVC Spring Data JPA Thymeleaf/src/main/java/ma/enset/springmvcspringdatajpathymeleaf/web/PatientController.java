package ma.enset.springmvcspringdatajpathymeleaf.web;

import lombok.AllArgsConstructor;
import ma.enset.springmvcspringdatajpathymeleaf.entities.Patient;
import ma.enset.springmvcspringdatajpathymeleaf.repositories.PatientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller @AllArgsConstructor
public class PatientController {
    private PatientRepo patientRepo;
    @GetMapping("/index")
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
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id,String keyword, int page){
        patientRepo.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

}
