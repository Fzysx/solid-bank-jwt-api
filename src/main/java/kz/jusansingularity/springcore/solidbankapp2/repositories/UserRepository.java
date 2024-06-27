package kz.jusansingularity.springcore.solidbankapp2.repositories;

import kz.jusansingularity.springcore.solidbankapp2.model.secureEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
