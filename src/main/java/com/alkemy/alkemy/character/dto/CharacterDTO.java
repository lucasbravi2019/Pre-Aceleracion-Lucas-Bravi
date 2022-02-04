package com.alkemy.alkemy.character.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CharacterDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    @NotEmpty(message = "is required")
    private String name;

    @NotEmpty(message = "is required")
    private String image;

    @NotNull(message = "is required")
    @Min(value = 0, message = "minimum value must be 0 or above")
    private Byte age;

    @NotNull(message = "is required")
    @Min(value = 0, message = "minimum value must be 0 or above")
    private Float weight;

    @NotEmpty(message = "is required")
    private String history;
    List<Long> moviesId;

}
