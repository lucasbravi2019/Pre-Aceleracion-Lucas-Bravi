package com.alkemy.alkemy.gender.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class GenderDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    @NotEmpty(message = "is required")
    private String name;

    @NotEmpty(message = "is required")
    private String image;

}
