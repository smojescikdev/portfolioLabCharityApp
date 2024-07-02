package pl.coderslab.charity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Category {


    @Id
    private Long id;

    private String name;


}
