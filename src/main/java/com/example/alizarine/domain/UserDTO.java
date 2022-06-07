package com.example.alizarine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String address;
    private LocalDate birthDate;





}
