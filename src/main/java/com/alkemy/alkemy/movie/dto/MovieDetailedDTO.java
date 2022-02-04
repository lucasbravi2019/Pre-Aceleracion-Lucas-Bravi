package com.alkemy.alkemy.movie.dto;

import com.alkemy.alkemy.character.dto.CharacterDetailedDTO;
import com.alkemy.alkemy.gender.dto.GenderBasicDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MovieDetailedDTO implements Serializable {

    private Long id;
    private String title;
    private String image;
    private Date createdAt;
    private List<CharacterDetailedDTO> characters;
    private GenderBasicDTO gender;

}
