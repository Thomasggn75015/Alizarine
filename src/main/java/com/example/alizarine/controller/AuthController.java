package com.example.alizarine.controller;

import com.example.alizarine.controller.login.LoginRequest;
import com.example.alizarine.controller.login.LoginResponse;
import com.example.alizarine.domain.User;
import com.example.alizarine.repository.UserRepository;
import com.example.alizarine.security.jwt.JwtUtils;
import com.example.alizarine.service.UserDetailsImpl;
import com.example.alizarine.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getId());
        LoginResponse response = new LoginResponse(jwt);
        log.info(String.valueOf(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/authenticate")
    public Optional<User> isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository.findOneByEmailIgnoreCase(userDetails.getEmail());
    }

    /*@GetMapping("/activate")
    public ResponseEntity<?> activate(@RequestParam(value = "key") String key, HttpServletResponse response) {
        Optional<User> user = userService.activateRegistration(key);
        if (user.isPresent()) {
            return new ResponseEntity<>("Account Activated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }*/
}
