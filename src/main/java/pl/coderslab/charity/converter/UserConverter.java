package pl.coderslab.charity.converter;

import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.model.UsersType;

public class UserConverter {
    public static UserDTO convertToDTO(Users user, UsersType userType) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());

        userDTO.setActive(user.isActive());
        userDTO.setRegistrationDate(user.getRegistrationDate());

        //userType Tabela
        userDTO.setUserTypeId(userType.getUserTypeId());
        userDTO.setUserTypeName(userType.getUserTypeName());

        return userDTO;
    }
}
