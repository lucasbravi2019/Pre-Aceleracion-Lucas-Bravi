package com.alkemy.alkemy.character.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CharacterFilterDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String name;
    private Byte age;
    private Float weight;
    private List<Long> moviesId;

    public CharacterFilterDTO(String name, Byte age, Float weight, List<Long> moviesId) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.moviesId = moviesId;
    }

}
