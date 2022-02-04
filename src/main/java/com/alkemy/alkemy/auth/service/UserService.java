package com.alkemy.alkemy.auth.service;

import com.alkemy.alkemy.auth.dto.LoginRequestDTO;
import com.alkemy.alkemy.auth.dto.LoginResponseDTO;
import com.alkemy.alkemy.auth.dto.UserDTO;
import com.alkemy.alkemy.auth.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

public interface UserService {

    UserEntity getUser(Long id) throws NoSuchElementException;
    UserEntity getUser(String username) throws NoSuchElementException;
    UserDTO registerUser(UserDTO dto) throws Exception;
    LoginResponseDTO login(LoginRequestDTO dto);
    LoginResponseDTO refreshToken(HttpServletRequest request) throws Exception;

}
