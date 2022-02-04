package com.alkemy.alkemy.movie.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class MovieBasicDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String title;
    private String image;
    private Date createdAt;

}
