package com.example.user_service.component;

import com.example.user_service.model.Role;
import com.example.user_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.findByName("Admin").orElseGet(
                () -> {
                    Role role = new Role();
                    role.setName("Admin");
                    return roleRepository.save(role);
                }
        );
        roleRepository.findByName("Customer").orElseGet(
                () -> {
                    Role role = new Role();
                    role.setName("Customer");
                    return roleRepository.save(role);
                }
        );
    }
}
