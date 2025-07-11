package com.chat.service.chat_service.security;

import com.chat.service.chat_service.exception.RoleNotFoundException;
import com.chat.service.chat_service.model.entity.Role;
import com.chat.service.chat_service.model.entity.User;
import com.chat.service.chat_service.model.enums.RoleEnum;
import com.chat.service.chat_service.repository.RoleRepository;
import com.chat.service.chat_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class AdminInitializer {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository,
                                         RoleRepository roleRepository,
                                         PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.findByUsername(adminUsername).isEmpty()) {
                Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                        .orElseThrow(() -> RoleNotFoundException.roleWithNameNotFound(RoleEnum.ROLE_ADMIN.name()));
                User admin = new User();
                admin.setUsername(adminUsername);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setEmail("admin@email.com");
                admin.setFirstName(adminUsername);
                admin.setLastName(adminUsername);
                admin.setRoles(List.of(adminRole));

                userRepository.save(admin);
            }
        };
    }
}
