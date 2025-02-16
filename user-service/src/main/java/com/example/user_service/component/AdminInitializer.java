package com.example.user_service.component;

import com.example.user_service.enums.Gender;
import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import com.example.user_service.repository.RoleRepository;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        Role role = roleRepository.findByName("Admin").orElseGet(
                () -> {
                    Role admin = new Role();
                    admin.setName("Admin");
                    return roleRepository.save(admin);
                }
        );
        userRepository.findByEmail("admin@gmail.com").orElseGet(
                () -> {
                    User user = new User();
                    user.setUsername("admin001");
                    user.setFirstname("Hello");
                    user.setLastname("Admin");
                    user.setDateOfBirth(LocalDate.of(1997, 9, 8));
                    user.setGender(Gender.MALE);
                    user.setEmail("admin@gmail.com");
                    user.setPhone("0987654321");
                    user.setPassword(passwordEncoder.encode("admin001"));
                    user.setRole(role);
                    return userRepository.save(user);
                }
        );
    }
}
