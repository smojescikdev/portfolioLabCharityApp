package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.InstitutionService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class DonationController {


    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private InstitutionService institutionService;

    // wyświetla formularz
    @GetMapping("/form")
    public String donateForm(Model model) {

        //wyswietla kategorie
        model.addAttribute("category", categoryService.findAll());

        //get all Institution from database
        model.addAttribute("institutions", institutionService.findAll());

        return "form";
    }


    @PostMapping("/form-confirmation")
    public String processForm(@RequestParam(name = "categories", required = false) List<Category> categories,
                              @RequestParam(name = "bags", required = false) Integer quantity,
                              @RequestParam(name = "institutions", required = false) List<Institution> institutions,
                              @RequestParam(name = "address", required = false) String street,
                              @RequestParam(name = "city", required = false) String city,
                              @RequestParam(name = "postcode", required = false) String zipcode,
                              @RequestParam(name = "phone", required = false) String phone,
                              @RequestParam(name = "data", required = false) LocalDate pickUpDate,
                              @RequestParam(name = "time", required = false) LocalTime pickUpTime,
                              @RequestParam(name = "more_info", required = false) String pickUpComment,
                              Model model) {

        // Przykładowe zapisywanie do bazy danych
        Donation donation = new Donation();
        donation.setCategories(categories);
        donation.setQuantity(quantity);
        donation.setInstitution(institutions.get(0));
        donation.setStreet(street);
        donation.setCity(city);
        donation.setZipCode(zipcode);
        donation.setPhone(phone);
        donation.setPickUpDate(pickUpDate);
        donation.setPickUpTime(pickUpTime);
        donation.setPickUpComment(pickUpComment);

        donationRepository.save(donation); // Zapisuje do bazy danych

        // Dodanie danych do modelu
        model.addAttribute("donation", donation);

        // Przekierowanie do widoku potwierdzenia
        return "form-confirmation";
    }
}
