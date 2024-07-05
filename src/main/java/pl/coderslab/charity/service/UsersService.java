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

import java.sql.Date;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // zapis usera do bazy + utworzenie profilu
    public Users addNewUser(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUser = usersRepository.save(users);
        int userTypeId = users.getUserTypeId().getUserTypeId();
//        if (userTypeId == 1) {
//            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
//
//        } else {
//
//            JobSeekerProfile jobSeekerProfile = new JobSeekerProfile(savedUser);
//
//
//            JobSeekerBasicInformation jobSeekerBasicInformation = new JobSeekerBasicInformation();
//            jobSeekerBasicInformation.setJobSeekerProfile(jobSeekerProfile);
//
//            jobSeekerProfile.setJobSeekerBasicInformation(jobSeekerBasicInformation);
//
//            jobSeekerProfileRepository.save(jobSeekerProfile);
//        }
        return savedUser;

    }

    // zwraca profil aktualnie zalogowanego użytkownika
//    public Object getCurrentUserProfile() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication instanceof AnonymousAuthenticationToken) {
//            String username = authentication.getName();
//            Users users = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not found " + "user"));
//
//            int userId = users.getUserId();
//            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
//                RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
//                return recruiterProfile;
//            } else {
//                JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
//                return jobSeekerProfile;
//            }
//        }
//        return null;
//
//    }

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
}

