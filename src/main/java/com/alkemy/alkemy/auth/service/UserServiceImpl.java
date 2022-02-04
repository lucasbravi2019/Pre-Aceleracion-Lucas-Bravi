package com.alkemy.alkemy.auth.service;

import com.alkemy.alkemy.auth.dto.LoginRequestDTO;
import com.alkemy.alkemy.auth.dto.LoginResponseDTO;
import com.alkemy.alkemy.auth.dto.UserDTO;
import com.alkemy.alkemy.auth.entity.UserEntity;
import com.alkemy.alkemy.auth.repo.UserRepo;
import com.alkemy.alkemy.auth.util.JWTUtils;
import com.alkemy.alkemy.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserEntity getUser(Long id) throws NoSuchElementException {
        return userRepo.findById(id).orElseThrow();
    }

    @Override
    public UserEntity getUser(String username) throws NoSuchElementException {
        return userRepo.findByUsername(username).orElseThrow();
    }

    @Override
    public UserDTO registerUser(UserDTO dto) throws Exception {
        if(userRepo.findByUsername(dto.getUsername()).isPresent()) throw new Exception("User already exists");
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepo.save(user);
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        if(user != null) {
            emailService.sendEmail(user.getUsername());
        }
        return dto;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        UserDetails userDetails;
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        userDetails = (UserDetails) auth.getPrincipal();
        String accessToken = JWTUtils.createAccessToken(userDetails);
        String refreshToken = JWTUtils.createRefreshToken(userDetails);
        return new LoginResponseDTO(accessToken, refreshToken);
    }

    @Override
    public LoginResponseDTO refreshToken(HttpServletRequest request) throws Exception {
        String authenticationHeader = request.getHeader("Authorization");
        if(authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
            String refreshToken = authenticationHeader.substring("Bearer ".length());
            String username = JWTUtils.decodeToken(refreshToken);
            UserEntity user = getUser(username);
            if(user == null) throw new Exception();
            String accessToken = JWTUtils.createAccessToken(user);
            return new LoginResponseDTO(accessToken, refreshToken);
        } else {
            throw new Exception();
        }
    }

}
