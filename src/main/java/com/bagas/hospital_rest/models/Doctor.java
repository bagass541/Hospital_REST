package com.bagas.hospital_rest.models;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.bagas.hospital_rest.entity.DoctorEntity;
import com.bagas.hospital_rest.entity.DoctorType;

import lombok.Data;

@Data
public class Doctor {
	
	private long id;
	
	private DoctorType doctorType;
	
	private String fio;
	
	private LocalTime startWork;
	
	private LocalTime endWork;
	
	private List<DoctorAppointment> appointments;
	
	public static Doctor toModel(DoctorEntity entity) {
		Doctor doctor = new Doctor();
		doctor.setId(entity.getId());
		doctor.setDoctorType(entity.getDoctorType());
		doctor.setFio(entity.getFio());
		doctor.setStartWork(entity.getStartWork());
		doctor.setEndWork(entity.getEndWork());
		doctor.setAppointments(entity.getAppointments().stream()
				.map(DoctorAppointment::toModel).collect(Collectors.toList()));
		
		return doctor;
	}
}
