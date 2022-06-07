package com.example.alizarine.controller;


import com.example.alizarine.domain.User;
import com.example.alizarine.repository.UserRepository;
import com.example.alizarine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) throws Exception {
        log.debug("REST request to create User : {}", user);
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        log.debug("REST request to get User : {}", userId);
        return ResponseEntity.ok(userRepository.findById(userId).get());
    }
}
