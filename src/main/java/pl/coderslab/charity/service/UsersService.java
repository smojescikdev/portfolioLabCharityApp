package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.repository.UsersRepository;
import pl.coderslab.charity.repository.UsersTypeRepository;

import java.sql.Date;
import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersTypeRepository usersTypeRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, UsersTypeRepository usersTypeRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.usersTypeRepository = usersTypeRepository;
    }


    // zapis usera do bazy
    public Users addNewUser(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        //get name & surname
        users.setName(users.getName());
        users.setSurname(users.getSurname());


        Users savedUser = usersRepository.save(users);

        return savedUser;

    }


    //zwraca aktualnie zalogowanego użytkownika
    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not found " + "user"));
            return user;
        }
        return null;
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users getOne(int id) {
        return usersRepository.getReferenceById(id);
    }


    public void editUser(Users users) {
        Users existingUser = usersRepository.findById(users.getUserId()).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("Użytkownik nie istnieje ID: " + users.getUserId());
        }

        existingUser.setName(users.getName());
        existingUser.setSurname(users.getSurname());
     //   existingUser.setEmail(users.getEmail());


        //existingUser.setPassword(passwordEncoder.encode(users.getPassword()));

        usersRepository.save(existingUser);
    }


    //dodawanie admina przez admina
    public void addNewUserByAdmin(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setEmail(users.getEmail());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setName(users.getName());
        users.setSurname(users.getSurname());

        Users savedUser = usersRepository.save(users);
    }



    //dodawanie uzytkownika przez admina
    public void addNewUserByAdmin1(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setEmail(users.getEmail());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setName(users.getName());
        users.setSurname(users.getSurname());

        Users savedUser = usersRepository.save(users);
    }


    public void deleteUserById(int userId) {
        usersRepository.deleteById(userId);
    }


//TO CHECK & DELETE
//    public Users getOne1(int userId) {
//        return usersRepository.getReferenceById(userId);
//
//    }
}

