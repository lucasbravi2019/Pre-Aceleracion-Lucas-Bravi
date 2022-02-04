package com.alkemy.alkemy.character.repo;

import com.alkemy.alkemy.character.entity.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

//TODO: Specification
public interface CharacterRepo extends JpaRepository<Character, Long>, JpaSpecificationExecutor<Character> {

    List<Character> findAll(Specification<Character> spec);

}
