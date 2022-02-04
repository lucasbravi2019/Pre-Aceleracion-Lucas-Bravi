package com.alkemy.alkemy.general.controller;

import com.alkemy.alkemy.general.mapper.CustomMapper;
import com.alkemy.alkemy.general.service.CustomServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

public class CrudControllerImpl<
        Service extends CustomServiceImpl<
                        ? extends JpaRepository<Entity, Long>,
                        ? extends CustomMapper,
                        Entity,
                        DTO,
                        BasicDTO,
                        DetailedDTO>,
        Entity,
        DTO,
        BasicDTO,
        DetailedDTO
        >
        implements CrudController<
        DTO,
        BasicDTO,
        DetailedDTO>{

    protected Service service;

    @GetMapping("/all")
    @Override
    public ResponseEntity<List<BasicDTO>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<DetailedDTO> getDetailed(Long id) {
        try {
            return new ResponseEntity<>(service.getDetailed(id), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<DetailedDTO> create(DTO d) {
        try {
            return new ResponseEntity<>(service.create(d), HttpStatus.CREATED);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}/update")
    @Override
    public ResponseEntity<DetailedDTO> update(Long id, DTO d) {
        try {
            return new ResponseEntity<>(service.update(id, d), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    @Override
    public ResponseEntity<HttpStatus> delete(Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
