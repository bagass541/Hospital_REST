package com.bagas.hospital_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_rest.entity.DoctorEntity;

public interface DoctorRepo extends JpaRepository<DoctorEntity, Long> {

}
