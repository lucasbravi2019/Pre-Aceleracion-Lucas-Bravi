package com.alkemy.alkemy.gender.dto;

import com.alkemy.alkemy.movie.dto.MovieDetailedDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GenderDetailedDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String image;
    private List<MovieDetailedDTO> movies;


}
