package pl.coderslab.charity.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {


    Optional<Users> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Users u WHERE u.userId = ?1")
    void deleteUserById(int userId);
}
