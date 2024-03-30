package com.bagas.hospital_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bagas.hospital_rest.entity.UserInfoEntity;
import java.util.Optional;



public interface UserInfoRepo extends JpaRepository<UserInfoEntity, Long> {

	@Query("SELECT ui FROM UserInfoEntity ui WHERE ui.user.id = :id")
	Optional<UserInfoEntity> findByUserEntityId(@Param("id") Long userId);
	
	UserInfoEntity findByNumber(String number);
}
