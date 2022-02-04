package com.alkemy.alkemy.gender.mapper;

import com.alkemy.alkemy.gender.dto.GenderBasicDTO;
import com.alkemy.alkemy.gender.dto.GenderDTO;
import com.alkemy.alkemy.gender.dto.GenderDetailedDTO;
import com.alkemy.alkemy.gender.entity.Gender;
import com.alkemy.alkemy.general.mapper.CustomMapper;
import com.alkemy.alkemy.movie.mapper.MovieMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenderMapper implements CustomMapper<Gender, GenderDTO, GenderBasicDTO, GenderDetailedDTO> {

    private MovieMapper movieMapper = new MovieMapper();

    @Override
    public GenderBasicDTO toBasicDTO(Gender gender) {
        GenderBasicDTO dto = new GenderBasicDTO();
        dto.setId(gender.getId());
        dto.setName(gender.getName());
        dto.setImage(gender.getImage());
        return dto;
    }

    @Override
    public GenderDetailedDTO toDetailedDTO(Gender gender, boolean loadRelationship) {
        GenderDetailedDTO dto = new GenderDetailedDTO();
        dto.setId(gender.getId());
        dto.setName(gender.getName());
        dto.setImage(gender.getImage());
        if(loadRelationship && gender.getMovies() != null) {
            dto.setMovies(gender.getMovies().stream().map(movie -> movieMapper.toDetailedDTO(movie, false)).collect(Collectors.toList()));
        }
        return dto;
    }

    @Override
    public List<GenderBasicDTO> toBasicDTOList(List<Gender> entity) {
        return entity.stream().map(gender -> toBasicDTO(gender)).collect(Collectors.toList());
    }

    @Override
    public List<GenderDetailedDTO> toDetailedDTOList(List<Gender> entity, boolean loadRelationship) {
        return entity.stream().map(gender -> toDetailedDTO(gender, true)).collect(Collectors.toList());
    }

    @Override
    public Gender toEntity(GenderDTO genderDTO) {
        Gender gender = new Gender();
        gender.setName(genderDTO.getName());
        gender.setImage(genderDTO.getImage());
        return gender;
    }

    @Override
    public Gender toEntity(GenderDTO genderDTO, Long id) {
        Gender gender = new Gender();
        gender.setId(id);
        gender.setName(genderDTO.getName());
        gender.setImage(genderDTO.getImage());
        return gender;
    }
}
