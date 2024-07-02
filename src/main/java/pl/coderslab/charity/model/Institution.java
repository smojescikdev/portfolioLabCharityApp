package pl.coderslab.charity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Institution {


    @Id
    private Long id;

    private String name;

    private String description;


}
