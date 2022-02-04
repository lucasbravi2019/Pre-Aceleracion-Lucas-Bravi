package com.alkemy.alkemy.movie.controller;

import com.alkemy.alkemy.general.controller.CrudControllerImpl;
import com.alkemy.alkemy.movie.dto.MovieBasicDTO;
import com.alkemy.alkemy.movie.dto.MovieDTO;
import com.alkemy.alkemy.movie.dto.MovieDetailedDTO;
import com.alkemy.alkemy.movie.entity.Movie;
import com.alkemy.alkemy.movie.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController extends CrudControllerImpl<
        MovieService,
        Movie,
        MovieDTO,
        MovieBasicDTO,
        MovieDetailedDTO> {

    public MovieController(MovieService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<MovieBasicDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genderId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        try {
            return new ResponseEntity<>(service.getByFilters(title, genderId, order), HttpStatus.OK);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<MovieDetailedDTO> getDetailed(@PathVariable Long id) {
        return super.getDetailed(id);
    }

    @Override
    public ResponseEntity<MovieDetailedDTO> create(@Valid @RequestBody MovieDTO d) {
        return super.create(d);
    }

    @Override
    public ResponseEntity<MovieDetailedDTO> update(@PathVariable Long id, @Valid @RequestBody MovieDTO d) {
        return super.update(id, d);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        return super.delete(id);
    }

}
