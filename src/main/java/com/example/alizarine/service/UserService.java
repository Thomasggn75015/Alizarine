package com.example.alizarine.service;

import com.example.alizarine.domain.Role;
import com.example.alizarine.domain.User;
import com.example.alizarine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<String> createUser(User requestedUser) {
        User user = new User();
        String encryptedPassword = passwordEncoder.encode(requestedUser.getPassword());
        user.setPassword(encryptedPassword);
        user.setEmail(requestedUser.getEmail().toLowerCase());
        user.setFirstName(requestedUser.getFirstName());
        user.setLastName(requestedUser.getLastName());
        user.setCreatedAt(Instant.now());
        user.setAddress(requestedUser.getAddress());
        user.setCity(requestedUser.getCity());
        user.setBirthDate(requestedUser.getBirthDate());
        user.setRole(Role.ROLE_USER.toString());
        user.setActivated(true);
        userRepository.save(user);
        log.info("New User created: {}", user.getEmail());
        return ResponseEntity.ok("User created successfully");
    }
}
