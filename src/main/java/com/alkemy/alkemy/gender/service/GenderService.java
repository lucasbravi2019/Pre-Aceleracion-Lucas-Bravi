package com.alkemy.alkemy.gender.service;

import com.alkemy.alkemy.gender.dto.GenderBasicDTO;
import com.alkemy.alkemy.gender.dto.GenderDTO;
import com.alkemy.alkemy.gender.dto.GenderDetailedDTO;
import com.alkemy.alkemy.gender.entity.Gender;
import com.alkemy.alkemy.gender.mapper.GenderMapper;
import com.alkemy.alkemy.gender.repo.GenderRepo;
import com.alkemy.alkemy.general.service.CustomServiceImpl;
import com.alkemy.alkemy.movie.entity.Movie;
import com.alkemy.alkemy.movie.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class GenderService extends CustomServiceImpl<
        GenderRepo,
        GenderMapper,
        Gender,
        GenderDTO,
        GenderBasicDTO,
        GenderDetailedDTO> {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    public GenderService(GenderRepo genderRepo, GenderMapper mapper) {
        this.repo = genderRepo;
        this.mapper = mapper;
    }

    @Override
    public void delete(Long id) throws NoSuchElementException {
        List<Movie> movies = movieRepo.findAll();
        movies = movies.stream().filter(movie -> movie.getGender() != null && movie.getGender().getId() == id).collect(Collectors.toList());
        movies = movies.stream().map(movie -> {
            movie.setGender(null);
            return movieRepo.save(movie);
        }).collect(Collectors.toList());
        repo.deleteById(id);
    }
}
