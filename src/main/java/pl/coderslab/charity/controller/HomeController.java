package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.service.InstitutionService;

@Controller
public class HomeController {


    private final InstitutionService institutionService;

    @Autowired
    public HomeController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/")
    public String home(Model model) {
        //get all Institution from database
        model.addAttribute("institutions", institutionService.findAll());
        return "index";
    }


}
