package com.alkemy.alkemy.character.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CharacterBasicDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String name;
    private String image;

}
