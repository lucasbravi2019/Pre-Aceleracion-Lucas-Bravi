package com.alkemy.alkemy.gender.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GenderBasicDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String image;

}
