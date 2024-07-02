package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Integer getTotalQuantity();

    @Query("SELECT COUNT(d) FROM Donation d")
    Long getTotalDonations();
}
