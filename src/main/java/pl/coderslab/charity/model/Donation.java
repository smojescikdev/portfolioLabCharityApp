package pl.coderslab.charity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Donation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    //categories (lista obiektów typu Category), pamiętaj o odpowiedniej adnotacji
    @OneToMany
    private List<Category> categories;

    //institution (obiekt typu Institution), pamiętaj o odpowiedniej adnotacji.
    @ManyToOne
    private Institution institution;


    private String street;
    private String city;
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;


}
