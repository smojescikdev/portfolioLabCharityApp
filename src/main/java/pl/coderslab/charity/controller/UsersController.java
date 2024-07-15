package pl.coderslab.charity.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.model.UsersType;
import pl.coderslab.charity.repository.UsersRepository;
import pl.coderslab.charity.service.UsersService;
import pl.coderslab.charity.service.UsersTypeService;

import java.util.List;

@Controller
public class UsersController {

    private final UsersRepository usersRepository;
    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersRepository usersRepository, UsersTypeService usersTypeService, UsersService usersService) {
        this.usersRepository = usersRepository;
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;

    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAllUsersTypes();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }


    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users) {
        System.out.println("User:: " + users);
        usersService.addNewUser(users);
        usersRepository.save(users);
        return "redirect:/login";

    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    //**ADMIN DASHBOARD ****

    @GetMapping("/admin/admin-dashboard")
    public String adminDashboard(Model model) {
        Users currentUser = usersService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "admin/admin-dashboard";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }


    //*********************ADMIN MANAGEMENT***************


    // wyświetla liste adminow
    @GetMapping("/admin/admin-list")
    public String listAdmins(Model model) {
        Integer adminTypeId = 2;
        List<UsersType> admins = usersTypeService.getAllByUserTypeId(adminTypeId);
        List<Users> users = usersService.findAll();

        model.addAttribute("admins", admins);
        model.addAttribute("users", users);

        Users currentUser = usersService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        return "admin/admin-list";
    }


    //edycja admina
    @GetMapping("/admin/edit-admin/{id}")
    public String editAdmin(@PathVariable("id") int id, Model model) {
        Users users = usersService.getOne(id);
        model.addAttribute("user", users);
        return "admin/edit-admin";
    }

//DTO**
    @PostMapping("/admin/editAdmin")
    public String editAdminPost(@Valid @ModelAttribute("user") Users user) {
        usersService.editUser(user);
        return "redirect:/admin/admin-list";
    }


    //dodawanie admina
    @GetMapping("/admin/add-admin")
    public String addAdmin(Model model) {
        model.addAttribute("users", new Users());

        //nie wiedzialem co tu dac zamiast getcurrentUser? chcialem miec dostep do pol z modelu User
        model.addAttribute("user", usersService.getCurrentUser());


        List<UsersType> usersTypes = usersTypeService.getAllUsersTypes();
        model.addAttribute("getAllTypes", usersTypes);
        return "admin/add-admin";
    }


    @PostMapping("/admin/addNewAdmin")
    public String addNewAdmin(Model model, Users users) {
        System.out.println("User:: " + users);
        usersService.addNewUserByAdmin(users);
        usersRepository.save(users);
        return "redirect:/admin/admin-list";


    }


    // usuwanie admina
    @GetMapping("/admin/delete-admin/confirm/{userId}")
    public String deleteAdminConfirmation(@PathVariable("userId") int userId, Model model) {
        Users user = usersService.getOne(userId);

        System.out.println("-------**** POBRANE ID UZYTKOWNIKA: = " + userId + " *****");

        model.addAttribute("user", user);
        return "/admin/delete-admin-confirmation";
    }

    @PostMapping("/admin/admin-delete/{userId}")
    public String deleteAdmin(@PathVariable("userId") int userId) {

        System.out.println("-------**** USUNIETE ID UZYTKOWNIKA: = " + userId + " *******");

        usersService.deleteUserById(userId);
        return "redirect:/admin/admin-list";
    }
}



//  List<UsersType> usersTypes = usersTypeService.getAllByUserTypeId(id);
// model.addAttribute("getAllTypes", usersTypes);