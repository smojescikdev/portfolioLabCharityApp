package pl.coderslab.charity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private int userId;
    private String email;

    private boolean isActive;
    private Date registrationDate;


    private String name;
    private String surname;

    //userType tabela
    private int userTypeId;
    private String userTypeName;


}