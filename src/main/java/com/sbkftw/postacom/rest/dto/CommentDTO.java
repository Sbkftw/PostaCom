package com.sbkftw.postacom.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class CommentDTO {

    @NotNull
    @Length(min = 1, max = 500)
    private String  message;
    @NotNull
    @Email
    private String  author;
    private Integer post;
}
