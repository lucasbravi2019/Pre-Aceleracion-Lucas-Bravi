package com.alkemy.alkemy.movie.service;


import com.alkemy.alkemy.general.service.CustomServiceImpl;
import com.alkemy.alkemy.movie.dto.MovieBasicDTO;
import com.alkemy.alkemy.movie.dto.MovieDTO;
import com.alkemy.alkemy.movie.dto.MovieDetailedDTO;
import com.alkemy.alkemy.movie.dto.MovieFilterDTO;
import com.alkemy.alkemy.movie.entity.Movie;
import com.alkemy.alkemy.movie.mapper.MovieMapper;
import com.alkemy.alkemy.movie.repo.MovieRepo;
import com.alkemy.alkemy.movie.repo.specification.MovieSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService extends CustomServiceImpl<
        MovieRepo,
        MovieMapper,
        Movie,
        MovieDTO,
        MovieBasicDTO,
        MovieDetailedDTO> {

    @Autowired
    public MovieService(MovieRepo movieRepo, MovieMapper mapper) {
        this.repo = movieRepo;
        this.mapper = mapper;
    }

    @Autowired
    private MovieSpecification movieSpecification;

    public List<MovieBasicDTO> getByFilters(String title, Long genderId, String order) {
        MovieFilterDTO filterDTO = new MovieFilterDTO(title, genderId, order);
        return mapper.toBasicDTOList(repo.findAll(movieSpecification.getByFilters(filterDTO)));
    }

}
