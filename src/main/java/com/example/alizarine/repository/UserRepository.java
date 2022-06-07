package com.example.alizarine.repository;

import com.example.alizarine.domain.User;
import com.example.alizarine.domain.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByEmailIgnoreCase(String email);

    @Query("select new com.example.alizarine.domain.UserDTO(firstName, lastName, email, city, address, birthDate) FROM User where id = :id")
    Optional<UserDTO> safeFindOneById(@Param("id") Long id);
}
