package kz.jusansingularity.springcore.solidbankapp2.service.secureServices;

import kz.jusansingularity.springcore.solidbankapp2.model.secureEntities.Role;
import kz.jusansingularity.springcore.solidbankapp2.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
