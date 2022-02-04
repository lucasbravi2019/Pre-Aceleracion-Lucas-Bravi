package com.alkemy.alkemy.movie.mapper;

import com.alkemy.alkemy.character.mapper.CharacterMapper;
import com.alkemy.alkemy.character.repo.CharacterRepo;
import com.alkemy.alkemy.gender.mapper.GenderMapper;
import com.alkemy.alkemy.gender.repo.GenderRepo;
import com.alkemy.alkemy.general.mapper.CustomMapper;
import com.alkemy.alkemy.movie.dto.MovieBasicDTO;
import com.alkemy.alkemy.movie.dto.MovieDTO;
import com.alkemy.alkemy.movie.dto.MovieDetailedDTO;
import com.alkemy.alkemy.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper implements CustomMapper<Movie, MovieDTO, MovieBasicDTO, MovieDetailedDTO> {

    @Autowired
    private CharacterRepo characterRepo;

    @Autowired
    private GenderRepo genderRepo;

    @Autowired
    private CharacterMapper characterMapper;

    @Override
    public MovieBasicDTO toBasicDTO(Movie movie) {
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setTitle(movie.getTitle());
        dto.setImage(movie.getImage());
        dto.setCreatedAt(movie.getCreatedAt());
        return dto;
    }

    @Override
    public MovieDetailedDTO toDetailedDTO(Movie movie, boolean loadRelationship) {
        MovieDetailedDTO dto = new MovieDetailedDTO();
        GenderMapper genderMapper = new GenderMapper();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setImage(movie.getImage());
        dto.setCreatedAt(movie.getCreatedAt());
        dto.setGender(movie.getGender() != null ? genderMapper.toBasicDTO(movie.getGender()) : null);
        if(loadRelationship && movie.getCharacters() != null) {
            dto.setCharacters(characterMapper.toDetailedDTOList(movie.getCharacters(), false));
        }
        return dto;
    }

    @Override
    public List<MovieBasicDTO> toBasicDTOList(List<Movie> entity) {
        return entity.stream().map(movie -> toBasicDTO(movie)).collect(Collectors.toList());
    }

    @Override
    public List<MovieDetailedDTO> toDetailedDTOList(List<Movie> entity, boolean loadRelationship) {
        return entity.stream().map(movie -> toDetailedDTO(movie, loadRelationship)).collect(Collectors.toList());
    }

    @Override
    public Movie toEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setImage(movieDTO.getImage());
        if(movieDTO.getCharactersId() != null) {
            movie.setCharacters(characterRepo.findAllById(movieDTO.getCharactersId()));
        }
        if(movieDTO.getGenderId() != null) {
            movie.setGender(genderRepo.findById(movieDTO.getGenderId()).get());
        }
        return movie;
    }

    @Override
    public Movie toEntity(MovieDTO movieDTO, Long id) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(movieDTO.getTitle());
        movie.setImage(movieDTO.getImage());
        if(movieDTO.getCharactersId() != null) {
            movie.setCharacters(characterRepo.findAllById(movieDTO.getCharactersId()));
        }
        if(movieDTO.getGenderId() != null) {
            movie.setGender(genderRepo.findById(movieDTO.getGenderId()).get());
        }
        return movie;
    }
}
