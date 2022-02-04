package com.alkemy.alkemy.character.service;

import com.alkemy.alkemy.character.dto.CharacterBasicDTO;
import com.alkemy.alkemy.character.dto.CharacterDTO;
import com.alkemy.alkemy.character.dto.CharacterDetailedDTO;
import com.alkemy.alkemy.character.dto.CharacterFilterDTO;
import com.alkemy.alkemy.character.entity.Character;
import com.alkemy.alkemy.character.mapper.CharacterMapper;
import com.alkemy.alkemy.character.repo.CharacterRepo;
import com.alkemy.alkemy.character.repo.specification.CharacterSpecification;
import com.alkemy.alkemy.general.service.CustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService extends CustomServiceImpl<
        CharacterRepo,
        CharacterMapper,
        Character,
        CharacterDTO,
        CharacterBasicDTO,
        CharacterDetailedDTO> {

    @Autowired
    public CharacterService(CharacterRepo characterRepo, CharacterMapper mapper) {
        this.repo = characterRepo;
        this.mapper = mapper;
    }

    @Autowired
    private CharacterSpecification characterSpecification;

    public List<CharacterBasicDTO> getByFilters(String name, Byte age, Float weight, List<Long> moviesId) {
        CharacterFilterDTO dto = new CharacterFilterDTO(name, age, weight, moviesId);
        return mapper.toBasicDTOList(repo.findAll(characterSpecification.getByFilters(dto)));
    }

}
