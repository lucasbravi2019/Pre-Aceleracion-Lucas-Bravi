package com.alkemy.alkemy.general.service;

import java.util.List;
import java.util.NoSuchElementException;

public interface CustomService<DTO, BasicDTO, DetailedDTO> {

    List<BasicDTO> getAll();
    DetailedDTO getDetailed(Long id) throws NoSuchElementException;
    DetailedDTO create(DTO dto);
    DetailedDTO update(Long id, DTO dto) throws NoSuchElementException;
    void delete(Long id) throws NoSuchElementException;

}
