package com.bagas.hospital_rest.entity;

import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "doctors")
@Data
public class DoctorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "doctor_type")
	private DoctorType doctorType;
	
	private String fio;
	
	@Column(name = "start_work")
	private LocalTime startWork;
	
	@Column(name = "end_work")
	private LocalTime endWork;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
	@SQLRestriction(value = "user_id IS NOT NULL")
	private List<AppointmentEntity> appointments;
}
