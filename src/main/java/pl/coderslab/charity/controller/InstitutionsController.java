package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.InstitutionService;

@Controller
public class InstitutionsController {

    @Autowired
    InstitutionService institutionService;


    // wyświetla liste fundacji
    @GetMapping("/admin/institutions-list")
    public String institutionsList(Model model) {

        //get all Institution from database
        model.addAttribute("institutions", institutionService.findAll());

        return "/admin/institutions-list";
    }


    // potwierdzenie usunięcia fundacji - tylko wtedy gdy żaden dar nie jest powiązany z tą fundacją
    @GetMapping("/admin/delete/confirm/{id}")
    public String deleteInstitutionConfirmation(@PathVariable("id") int id, Model model) {
        Institution institution = institutionService.getOne(id);
        model.addAttribute("institution", institution);

        return "/admin/delete-institution-confirmation";
    }

    @PostMapping("/admin/institution/{id}")
    public String deleteInstitution(@PathVariable("id") int id) {
        institutionService.deleteInstitution(id);

        return "redirect:/admin/institutions-list";
    }


}
