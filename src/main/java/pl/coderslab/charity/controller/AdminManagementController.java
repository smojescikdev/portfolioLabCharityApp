package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.model.UsersType;
import pl.coderslab.charity.service.UsersService;
import pl.coderslab.charity.service.UsersTypeService;

import java.util.List;

@Controller
public class AdminManagementController {


    private final UsersService usersService;
    private final UsersTypeService usersTypeService;

    public AdminManagementController(UsersService usersService, UsersTypeService usersTypeService) {
        this.usersService = usersService;
        this.usersTypeService = usersTypeService;
    }

    // wyświetla liste adminow
    @GetMapping("/admin/list")
    public String listAdmins(Model model) {
        Integer adminTypeId = 2;
        List<UsersType> admins = usersTypeService.getAllByUserTypeId(adminTypeId);
        model.addAttribute("admins", admins);
       // model.addAttribute("user", usersService.findAll());
        return "admin/admin-list";
    }
}
