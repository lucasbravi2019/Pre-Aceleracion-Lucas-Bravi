package com.alkemy.alkemy.general.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ApiErrorDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private HttpStatus status;
    private List<String> errors;

    public ApiErrorDTO(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

}
