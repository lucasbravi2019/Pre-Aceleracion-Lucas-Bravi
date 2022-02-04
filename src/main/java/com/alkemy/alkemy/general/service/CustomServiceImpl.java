package com.alkemy.alkemy.general.service;

import com.alkemy.alkemy.general.mapper.CustomMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class CustomServiceImpl<
        Repo extends JpaRepository<
                Entity,
                Long>,
        Mapper extends CustomMapper<
                Entity,
                DTO,
                BasicDTO,
                DetailedDTO>,
        Entity,
        DTO,
        BasicDTO,
        DetailedDTO>
        implements CustomService<
                DTO,
                BasicDTO,
                DetailedDTO> {

    protected Repo repo;
    protected Mapper mapper;

    @Override
    public List<BasicDTO> getAll() {
        return mapper.toBasicDTOList(repo.findAll());
    }

    @Override
    public DetailedDTO getDetailed(Long id) throws NoSuchElementException {
        return mapper.toDetailedDTO(repo.findById(id).orElseThrow(), true);
    }

    @Override
    public DetailedDTO create(DTO dto) {
        return mapper.toDetailedDTO(repo.save(mapper.toEntity(dto)), true);
    }

    @Override
    public DetailedDTO update(Long id, DTO dto) throws NoSuchElementException {
        if(!repo.existsById(id)) throw new NoSuchElementException();
        return mapper.toDetailedDTO(repo.save(mapper.toEntity(dto, id)), true);
    }

    @Override
    public void delete(Long id) throws NoSuchElementException {
        if(!repo.existsById(id)) throw new NoSuchElementException();
        repo.deleteById(id);
    }

}
