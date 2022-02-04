package com.alkemy.alkemy.character.controller;

import com.alkemy.alkemy.character.dto.CharacterBasicDTO;
import com.alkemy.alkemy.character.dto.CharacterDTO;
import com.alkemy.alkemy.character.dto.CharacterDetailedDTO;
import com.alkemy.alkemy.character.entity.Character;
import com.alkemy.alkemy.character.service.CharacterService;
import com.alkemy.alkemy.general.controller.CrudControllerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController extends CrudControllerImpl<
        CharacterService,
        Character,
        CharacterDTO,
        CharacterBasicDTO,
        CharacterDetailedDTO> {

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<CharacterBasicDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Byte age,
            @RequestParam(required = false) Float weight,
            @RequestParam(required = false) List<Long> moviesId
    ) {
        try {
            return new ResponseEntity<>(service.getByFilters(name, age, weight, moviesId), HttpStatus.OK);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CharacterDetailedDTO> getDetailed(@PathVariable Long id) {
        return super.getDetailed(id);
    }

    @Override
    public ResponseEntity<CharacterDetailedDTO> create(@Valid @RequestBody CharacterDTO d) {
        return super.create(d);
    }

    @Override
    public ResponseEntity<CharacterDetailedDTO> update(@PathVariable Long id, @Valid @RequestBody CharacterDTO d) {
        return super.update(id, d);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
