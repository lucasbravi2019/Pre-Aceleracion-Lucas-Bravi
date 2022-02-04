package com.alkemy.alkemy.gender.repo;

import com.alkemy.alkemy.gender.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepo extends JpaRepository<Gender, Long> {
}
