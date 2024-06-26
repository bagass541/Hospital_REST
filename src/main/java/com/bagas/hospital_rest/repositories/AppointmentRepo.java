package com.bagas.hospital_rest.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bagas.hospital_rest.entity.AppointmentEntity;

public interface AppointmentRepo extends JpaRepository<AppointmentEntity, Long> {

	@Query("SELECT app FROM AppointmentEntity app WHERE app.user.id = :id")
	List<AppointmentEntity> findByUserId(@Param("id") Long id);
	
	@Query("SELECT app FROM AppointmentEntity app WHERE app.doctor.id = :id and app.user IS NOT NULL")
	List<AppointmentEntity> findByDoctorId(@Param("id") Long id);
	
	@Query("SELECT app FROM AppointmentEntity app WHERE app.doctor.id = :doctorId AND app.time = :time")
	AppointmentEntity findByDoctorByTime(@Param("doctorId") Long doctorId, @Param("time") LocalDateTime time);
}
