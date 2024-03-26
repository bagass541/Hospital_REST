package com.bagas.hospital_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_rest.entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);
}
