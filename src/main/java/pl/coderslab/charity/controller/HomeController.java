package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

@Controller
public class HomeController {


    private final InstitutionService institutionService;
    private final DonationService donationService;

    @Autowired
    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;

    }

    @GetMapping("/")
    public String home(Model model) {
        //get all Institution from database
        model.addAttribute("institutions", institutionService.findAll());

        //get all Quantity & Donations from database
        model.addAttribute("totalQuantity", donationService.getTotalQuantity());
        model.addAttribute("totalDonations", donationService.getTotalDonations());
        return "index";
    }


}
