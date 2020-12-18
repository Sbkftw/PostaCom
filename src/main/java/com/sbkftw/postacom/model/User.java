package com.sbkftw.postacom.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Integer   id;
    @NonNull
    private String    lastName;
    @NonNull
    private String    firstName;
    @NonNull
    private String    email;
    @NonNull
    private String    gender;
    @NonNull
    private LocalDate dateOfBirth;
    @NonNull
    private LocalDate registerDate;
}
