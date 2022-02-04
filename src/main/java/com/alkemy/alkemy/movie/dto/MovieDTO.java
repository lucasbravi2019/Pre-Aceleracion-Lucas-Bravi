package com.alkemy.alkemy.movie.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MovieDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    @NotEmpty(message = "is required")
    private String title;

    @NotEmpty(message = "is required")
    private String image;

    private List<Long> charactersId;
    private Long genderId;

}
