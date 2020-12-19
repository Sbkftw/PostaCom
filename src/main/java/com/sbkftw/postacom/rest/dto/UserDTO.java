package com.sbkftw.postacom.rest.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull
    private String    lastName;
    @NotNull
    private String    firstName;
    @NotNull
    @Email
    private String    email;
    @NotNull
    private String    gender;
    @NotNull
    @PastOrPresent
    private LocalDate dateOfBirth;
    @NotNull
    @PastOrPresent
    private LocalDate registerDate;
}
