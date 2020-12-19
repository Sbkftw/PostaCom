package com.sbkftw.postacom.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class PostDTO {

    @NotNull
    @Length(min = 1, max = 500)
    private String text;
    @NotNull
    @Email
    private String author;
}
