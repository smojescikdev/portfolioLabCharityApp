package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.repository.UsersRepository;
import pl.coderslab.charity.util.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;


    @Autowired
    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //wczytuje user na podstawie email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not found user " + username));
        return new CustomUserDetails(user);
    }
}
