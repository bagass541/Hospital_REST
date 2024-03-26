package com.bagas.hospital_rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bagas.hospital_rest.entity.RoleEntity;
import com.bagas.hospital_rest.models.Role;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
		
	@Query("SELECT r FROM RoleEntity r JOIN r.users u WHERE u.id = :id")
	List<RoleEntity> findAllByUserId(@Param("id") Long id);
}
