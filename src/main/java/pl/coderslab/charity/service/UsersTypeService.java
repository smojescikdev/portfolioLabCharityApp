package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.UsersType;
import pl.coderslab.charity.repository.UsersTypeRepository;

import java.util.List;

@Service
public class UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    public UsersTypeService(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }


    //lista wszystkich typów użytkowników
    public List<UsersType> getAll() {
        return usersTypeRepository.findAll();
    }

    //lista wszystkich typów użytkowników
    public List<UsersType> getAllUsersTypes() {
        return usersTypeRepository.findAll();
    }
}