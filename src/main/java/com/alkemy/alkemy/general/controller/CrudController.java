package com.alkemy.alkemy.general.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<DTO, BasicDTO, DetailedDTO> {

    ResponseEntity<List<BasicDTO>> getAll();
    ResponseEntity<DetailedDTO> getDetailed(Long id);
    ResponseEntity<DetailedDTO> create(DTO d);
    ResponseEntity<DetailedDTO> update(Long id, DTO d);
    ResponseEntity<HttpStatus> delete(Long id);


}
