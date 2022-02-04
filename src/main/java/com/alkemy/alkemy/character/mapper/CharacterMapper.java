package com.alkemy.alkemy.character.mapper;

import com.alkemy.alkemy.character.dto.CharacterBasicDTO;
import com.alkemy.alkemy.character.dto.CharacterDTO;
import com.alkemy.alkemy.character.dto.CharacterDetailedDTO;
import com.alkemy.alkemy.character.entity.Character;
import com.alkemy.alkemy.general.mapper.CustomMapper;
import com.alkemy.alkemy.movie.mapper.MovieMapper;
import com.alkemy.alkemy.movie.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CharacterMapper implements CustomMapper<Character, CharacterDTO, CharacterBasicDTO, CharacterDetailedDTO> {

    @Autowired
    private MovieRepo movieRepo;

    private MovieMapper movieMapper = new MovieMapper();

    @Override
    public CharacterBasicDTO toBasicDTO(Character character) {
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setName(character.getName());
        dto.setImage(character.getImage());
        return dto;
    }

    @Override
    public CharacterDetailedDTO toDetailedDTO(Character character, boolean loadRelationship) {
        CharacterDetailedDTO dto = new CharacterDetailedDTO();
        dto.setId(character.getId());
        dto.setName(character.getName());
        dto.setAge(character.getAge());
        dto.setHistory(character.getHistory());
        dto.setWeight(character.getWeight());
        dto.setImage(character.getImage());
        if(loadRelationship && character.getMovies() != null) {
            dto.setMovies(movieMapper.toDetailedDTOList(character.getMovies(), false));
        }
        return dto;
    }

    @Override
    public List<CharacterBasicDTO> toBasicDTOList(List<Character> entity) {
        return entity.stream().map(character -> toBasicDTO(character)).collect(Collectors.toList());
    }

    @Override
    public List<CharacterDetailedDTO> toDetailedDTOList(List<Character> entity, boolean loadRelationship) {
        return entity.stream().map(character -> toDetailedDTO(character, loadRelationship)).collect(Collectors.toList());
    }

    @Override
    public Character toEntity(CharacterDTO characterDTO) {
        Character character = new Character();
        character.setName(characterDTO.getName());
        character.setImage(characterDTO.getImage());
        character.setWeight(characterDTO.getWeight());
        character.setAge(characterDTO.getAge());
        character.setHistory(characterDTO.getHistory());
        if(characterDTO.getMoviesId() != null) {
            character.setMovies(movieRepo.findAllById(characterDTO.getMoviesId()));
        }
        return character;
    }

    @Override
    public Character toEntity(CharacterDTO characterDTO, Long id) {
        Character character = new Character();
        character.setId(id);
        character.setName(characterDTO.getName());
        character.setImage(characterDTO.getImage());
        character.setWeight(characterDTO.getWeight());
        character.setAge(characterDTO.getAge());
        character.setHistory(characterDTO.getHistory());
        if(characterDTO.getMoviesId() != null) {
            character.setMovies(movieRepo.findAllById(characterDTO.getMoviesId()));
        }
        return character;
    }
}
