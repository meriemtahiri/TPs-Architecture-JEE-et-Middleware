package ma.enset.springmvcspringdatajpathymeleaf.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller @AllArgsConstructor
public class SecurityController {

    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
