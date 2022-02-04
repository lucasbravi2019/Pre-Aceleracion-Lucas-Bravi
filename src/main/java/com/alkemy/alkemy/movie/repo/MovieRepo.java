package com.alkemy.alkemy.movie.repo;

import com.alkemy.alkemy.movie.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

//TODO: Specification
public interface MovieRepo extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    List<Movie> findAll(Specification<Movie> spec);

}
