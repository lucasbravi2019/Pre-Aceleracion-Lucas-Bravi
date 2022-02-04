package com.alkemy.alkemy.auth.repo;

import com.alkemy.alkemy.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
