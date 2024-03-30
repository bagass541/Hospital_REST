package com.bagas.hospital_rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bagas.hospital_rest.entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);

	@Query("SELECT DISTINCT u FROM UserEntity u LEFT JOIN FETCH u.userInfo ui LEFT JOIN FETCH u.appointments uapp LEFT JOIN FETCH u.authorities ua")
	@Override List<UserEntity> findAll();
	
	
}
