package com.example.alizarine.service;

import com.example.alizarine.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String firstName;
    private String email;
    @JsonIgnore
    private String password;
    private String lastName;
    private Instant createdAt;
    private String address;
    private String city;
    private LocalDate birthDate;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String firstName, String email, String password, String lastName,
                           Instant createdAt, String address, String city, LocalDate birthDate,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.address = address;
        this.city = city;
        this.birthDate = birthDate;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
        return new UserDetailsImpl(
                user.getId(),
                user.getFirstName(),
                user.getEmail(),
                user.getPassword(),
                user.getLastName(),
                user.getCreatedAt(),
                user.getAddress(),
                user.getCity(),
                user.getBirthDate(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
    public String getLastName() {
        return lastName;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
}
