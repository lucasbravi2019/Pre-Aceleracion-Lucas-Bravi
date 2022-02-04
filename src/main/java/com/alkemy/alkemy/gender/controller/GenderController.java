package com.alkemy.alkemy.gender.controller;

import com.alkemy.alkemy.gender.dto.GenderBasicDTO;
import com.alkemy.alkemy.gender.dto.GenderDTO;
import com.alkemy.alkemy.gender.dto.GenderDetailedDTO;
import com.alkemy.alkemy.gender.entity.Gender;
import com.alkemy.alkemy.gender.service.GenderService;
import com.alkemy.alkemy.general.controller.CrudControllerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/genders")
public class GenderController extends CrudControllerImpl<
        GenderService,
        Gender,
        GenderDTO,
        GenderBasicDTO,
        GenderDetailedDTO> {

    public GenderController(GenderService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<GenderBasicDTO>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<GenderDetailedDTO> getDetailed(@PathVariable Long id) {
        return super.getDetailed(id);
    }

    @Override
    public ResponseEntity<GenderDetailedDTO> create(@Valid @RequestBody GenderDTO d) {
        return super.create(d);
    }

    @Override
    public ResponseEntity<GenderDetailedDTO> update(@PathVariable Long id, @Valid @RequestBody GenderDTO d) {
        return super.update(id, d);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
