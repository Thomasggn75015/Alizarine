package com.example.alizarine.controller;


import com.example.alizarine.domain.User;
import com.example.alizarine.repository.UserRepository;
import com.example.alizarine.service.UserService;
import lombok.RequiredArgsConstructor;
import io.github.jhipster.web.util.HeaderUtil;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    @Value("${spring.application.name}")
    private String applicationName;

    private final UserRepository userRepository;

    private final UserService userService;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) throws Exception {
        log.debug("REST request to save User : {}", user);
        return userService.createUser(user);
            //throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
        //} else if (userRepository.findOneByEmailIgnoreCase(user.getEmail()).isPresent()) {
            //throw new EmailAlreadyUsedException();
        /*} else {
            user.setEmail(user.getEmail());
            User newUser = userService.createUser(user);
            //mailService.sendCreationEmail(newUser);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getEmail()))
                    .headers(HeaderUtil.createAlert(applicationName,  "A user is created with identifier " + newUser.getEmail(), newUser.getEmail()))
                    .body(newUser);
        }*/
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        log.debug("REST request to get User : {}", userId);
        return ResponseEntity.ok(userRepository.findById(userId).get());
    }
}
