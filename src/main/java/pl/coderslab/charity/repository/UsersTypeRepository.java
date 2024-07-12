package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.UsersType;

import java.util.List;


@Repository
public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {
    List<UsersType> findAllByUserTypeId(Integer userTypeId);
}
