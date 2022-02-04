package com.alkemy.alkemy.character.repo.specification;

import com.alkemy.alkemy.character.dto.CharacterFilterDTO;
import com.alkemy.alkemy.character.entity.Character;
import com.alkemy.alkemy.movie.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<Character> getByFilters(CharacterFilterDTO dto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasLength(dto.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("name")),
                                "%" + dto.getName() + "%"
                    )
                );
            }

            if(dto.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(root.get("age")),
                                dto.getAge()
                        )
                );
            }

            if(dto.getWeight() != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(root.get("weight")),
                                dto.getWeight()
                        )
                );
            }

            if(dto.getMoviesId() != null) {
                Join<Character, Movie> join = root.join("movies");
                Expression<String> movieId = join.get("id");
                predicates.add(movieId.in(dto.getMoviesId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
