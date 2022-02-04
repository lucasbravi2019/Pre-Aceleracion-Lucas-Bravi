package com.alkemy.alkemy.auth.controller;

import com.alkemy.alkemy.auth.dto.LoginRequestDTO;
import com.alkemy.alkemy.auth.dto.LoginResponseDTO;
import com.alkemy.alkemy.auth.dto.UserDTO;
import com.alkemy.alkemy.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO dto) {
        try {
            return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.CREATED);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        try {
            return new ResponseEntity<>(userService.login(dto), HttpStatus.OK);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(userService.refreshToken(request), HttpStatus.OK);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
