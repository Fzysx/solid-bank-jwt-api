package kz.jusansingularity.springcore.solidbankapp2.repositories;

import kz.jusansingularity.springcore.solidbankapp2.model.secureEntities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
